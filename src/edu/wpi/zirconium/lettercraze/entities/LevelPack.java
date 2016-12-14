package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class LevelPack {
    private List<Level> levels = new ArrayList<>();
    private List<LevelStats> levelStats = new ArrayList<>();

    protected LevelPackData data;
    private final String title;

    private LevelPack(String title) {
        this.title = title;
    }

    public void saveStats(){

    }

    public boolean isUnlocked(LevelStats level){
        int index = levelStats.indexOf(level) - 1;
        return index < 0 || levelStats.get(index).numAchievedStars() > 0;
    }

    public Optional<LevelStats> statsForLevel(Level level) {
        return levelStats.stream().filter(ls -> ls.getLevel().equals(level)).findFirst();
    }

    public static LevelPack dummyPuzzle() {
        LevelPack lp = new LevelPack("Puzzle Levels");
        lp.levelStats = Arrays.asList(
            new PuzzleLevelStats(Level.dummy(6), 1),
            new PuzzleLevelStats(Level.dummy(6), 2),
            new PuzzleLevelStats(Level.dummy(6), 3),
            new PuzzleLevelStats(Level.dummy(6), 0),
            new PuzzleLevelStats(Level.dummy(6), 0));
        return lp;
    }

    public static LevelPack dummyLightning() {
        LevelPack lp = new LevelPack("Lightning Levels");
        lp.levelStats = Arrays.asList(
            new LightningLevelStats(LightningLevel.dummy(), 15),
            new LightningLevelStats(LightningLevel.dummy(), 0),
            new LightningLevelStats(LightningLevel.dummy(), 0));
        return lp;
    }

    public static LevelPack dummyTheme() {
        LevelPack lp = new LevelPack("Theme Levels");
        lp.levelStats = Arrays.asList(
            new ThemeLevelStats(ThemeLevel.dummy(), 8),
            new ThemeLevelStats(ThemeLevel.dummy(), 0));
        return lp;
    }



    public List<Level> getLevels() {
        return levels;
    }

    public List<LevelStats> getLevelStats() {
        return levelStats;
    }

    public String getTitle() {
        return title;
    }
}
