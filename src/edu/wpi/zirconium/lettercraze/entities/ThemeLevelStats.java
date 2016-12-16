package edu.wpi.zirconium.lettercraze.entities;

public class ThemeLevelStats extends LevelStats {

    private final int wordsFound;

    /**
     * Creates the ThemeLevelStats with a given Level and given words found.
     * @param l the Level
     * @param wordsFound the number of words to be found
     */
    public ThemeLevelStats(Level l, int wordsFound) {
        super(l);
        this.wordsFound = wordsFound;
    }

    @Override
    public int thresholdValue() {
        return wordsFound;
    }

    @Override
    public String thresholdLabel() {
        return "words found";
    }
}
