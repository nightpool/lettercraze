package edu.wpi.zirconium.lettercraze.entities;

public abstract class LevelStats {

    private final Level level;

    LevelStats(Level l){
        this.level = l;
    }

    public int numAchievedStars() {
        return getLevel().numAchievedStars(thresholdValue());
    }

    public abstract int thresholdValue();
    public abstract String thresholdLabel();

    public Level getLevel() {
        return level;
    }
}
