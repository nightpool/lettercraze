package edu.wpi.zirconium.lettercraze.entities;

public class ThemeLevelStats extends LevelStats{

    private final int wordsFound;

    ThemeLevelStats(ThemeLevel l, int wordsFound) {
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
