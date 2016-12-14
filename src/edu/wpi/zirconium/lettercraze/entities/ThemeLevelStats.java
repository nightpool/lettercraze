package edu.wpi.zirconium.lettercraze.entities;

public class ThemeLevelStats extends LevelStats{

    private final int wordsFound;
    private final ThemeLevel level;

    ThemeLevelStats(ThemeLevel l, int wordsFound) {
        super(l);
        level = l;
        this.wordsFound = wordsFound;
    }

    @Override
    public int thresholdValue() {
        return wordsFound;
    }

    @Override
    public String thresholdLabel() {
        return String.format("%d/%d words", wordsFound, level.getWords().size());
    }
}
