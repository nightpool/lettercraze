package edu.wpi.zirconium.lettercraze.entities;

public class PuzzleLevelStats extends LevelStats {

    private final int mostPoints;

    PuzzleLevelStats(Level l, int mostPoints) {
        super(l);
        this.mostPoints = mostPoints;
    }

    @Override
    public int thresholdValue() {
        return mostPoints;
    }

    @Override
    public String thresholdLabel() {
        return mostPoints + " points";
    }
}
