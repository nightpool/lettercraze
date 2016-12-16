package edu.wpi.zirconium.lettercraze.entities;

import java.util.function.Function;

public abstract class LevelStats {

    private final Level level;

    LevelStats(Level l){
        this.level = l;
    }

    /**
     * Returns the maximum number of stars achieved.
     * @return the number of starts achieved (1, 2, or 3)
     */
    public int numAchievedStars() {
        return getLevel().numAchievedStars(thresholdValue());
    }

    public abstract int thresholdValue();
    public abstract String thresholdLabel();
    /**
     * Gets the Level
     * @return the Level
     */
    public Level getLevel() {
        return level;
    }
    
    /**
     * Gets the LevelStats from the given String in file format
     * @param s the file formatted String
     * @param levelFactory
     * @return the LevelStats from the given String in file format
     */
    public static LevelStats fromString(String s, Function< String, Level> levelFactory) {
        String[] parts = s.split(" ");
        if (parts.length < 3) return null;

        String type = parts[0];
        String path = parts[1];
        String arg = parts[2];

        if (type.equalsIgnoreCase("PuzzleLevel")) {
            return new PuzzleLevelStats(
                levelFactory.apply(path),
                Integer.valueOf(arg)
            );
        } else if (type.equalsIgnoreCase("LightningLevel")) {
            return new LightningLevelStats(
                levelFactory.apply(path),
                Integer.valueOf(arg)
            );
        } else if (type.equalsIgnoreCase("ThemeLevel")) {
            return new ThemeLevelStats(
                levelFactory.apply(path),
                Integer.valueOf(arg)
            );
        }
        return null;
    }
    
    /**
     * Returns the String to put into a save file
     * @return the String
     */
    public String saveString() {
        String className = this.getClass().getSimpleName().replace("Stats", "");
        return className + " " +
            this.getLevel().getPath().orElseThrow(() ->
                new IllegalStateException("Can't save a level without a path")).getFileName()
            + " " + thresholdValue();
    }
}
