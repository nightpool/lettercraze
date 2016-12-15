package edu.wpi.zirconium.lettercraze.entities;

public class LightningLevelStats extends LevelStats{

    private final int mostWords;

    public LightningLevelStats(Level l, int mostWords) {
        super(l);
        this.mostWords = mostWords;
    }

    @Override
    public int thresholdValue() {
        return mostWords;
    }

    @Override
    public String thresholdLabel() {
        return "words";
    }
}
