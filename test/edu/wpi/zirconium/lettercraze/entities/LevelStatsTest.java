package edu.wpi.zirconium.lettercraze.entities;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LevelStatsTest {
    private Level level;
    private LevelStats stats1;
    private LevelStats stats2;
    private LevelStats stats3;

    @Before
    public void setUp() throws Exception {
        level = PuzzleLevel.dummy();
        stats1 = new PuzzleLevelStats(level, 6);
        stats2 = new LightningLevelStats(level, 6);
        stats3 = new ThemeLevelStats(level, 6);
    }

    @Test
    public void numAchievedStars() throws Exception {
        assertEquals(stats1.numAchievedStars(), level.numAchievedStars(6));
        assertEquals(stats2.numAchievedStars(), level.numAchievedStars(6));
        assertEquals(stats3.numAchievedStars(), level.numAchievedStars(6));
    }

    @Test
    public void thresholdValue() throws Exception {
        assertEquals(stats1.thresholdValue(), 6);
        assertEquals(stats2.thresholdValue(), 6);
        assertEquals(stats3.thresholdValue(), 6);
    }

    @Test
    public void thresholdLabel() throws Exception {
        assertNotNull(stats1.thresholdLabel());
        assertNotNull(stats2.thresholdLabel());
        assertNotNull(stats3.thresholdLabel());
    }

    @Test
    public void getLevel() throws Exception {
        assertEquals(stats1.getLevel(), level);
    }

    private static Level mockLevelFactory(String pathName) {
        Level l = Level.dummy(6);
        l.setPath(Paths.get(pathName));
        return l;
    }

    @Test
    public void testGoodLevels() throws Exception {
        LevelStats p = LevelStats.fromString("PuzzleLevel P1.txt 0", LevelStatsTest::mockLevelFactory);
        assertNotNull(p);
        assertEquals(PuzzleLevelStats.class, p.getClass());
        assertEquals(p.thresholdValue(), 0);

        LevelStats t = LevelStats.fromString("ThemeLevel T1.txt 5", LevelStatsTest::mockLevelFactory);
        assertNotNull(t);
        assertEquals(ThemeLevelStats.class, t.getClass());
        assertEquals(t.thresholdValue(), 5);

        LevelStats l = LevelStats.fromString("LightningLevel L1.txt 100", LevelStatsTest::mockLevelFactory);
        assertNotNull(l);
        assertEquals(LightningLevelStats.class, l.getClass());
        assertEquals(l.thresholdValue(), 100);
    }

    @Test
    public void testBadLevels() throws Exception {
        LevelStats n1 = LevelStats.fromString("BlahBlah L1.txt 100", LevelStatsTest::mockLevelFactory);
        assertNull(n1);

        LevelStats n2 = LevelStats.fromString("LightningLevel L1.txt", LevelStatsTest::mockLevelFactory);
        assertNull(n2);
    }

    @Test
    public void saveString() throws Exception {
        LevelStats p = LevelStats.fromString("PuzzleLevel P1.txt 0", LevelStatsTest::mockLevelFactory);
        assertNotNull(p);
        assertEquals("PuzzleLevel P1.txt 0", p.saveString());
    }
}