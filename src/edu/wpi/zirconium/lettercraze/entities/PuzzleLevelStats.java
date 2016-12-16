package edu.wpi.zirconium.lettercraze.entities;

public class PuzzleLevelStats extends LevelStats {

    private final int wordsLimit;
    /**
     * Creates PuzzleLevelStats with the given Level and maximum Points.
     * @param l the Level
     * @param wordLimit the word limit for the level
     */
    public PuzzleLevelStats(Level l, int wordLimit) {
        super(l);
        this.wordsLimit = wordLimit;
    }

    @Override
    public int thresholdValue() {
        return wordsLimit;
    }

    @Override
    public String thresholdLabel() {
        return "points";
    }
}
