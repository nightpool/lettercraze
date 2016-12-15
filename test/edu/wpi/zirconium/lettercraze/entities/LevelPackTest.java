package edu.wpi.zirconium.lettercraze.entities;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class LevelPackTest {
    private LevelPack mockPack;

    @Before
    public void setUp() throws Exception {
        mockPack = new LevelPack("mock_levels"){
            {
                levelStats = Arrays.asList(
                    new PuzzleLevelStats(Level.dummy(6), 1),
                    new PuzzleLevelStats(Level.dummy(6), 2),
                    new PuzzleLevelStats(Level.dummy(6), 3),
                    new PuzzleLevelStats(Level.dummy(6), 0),
                    new PuzzleLevelStats(Level.dummy(6), 0));
                levelStats.forEach(ls -> ls.getLevel().setPack(this));
            }
        };
    }

    @Test
    public void isUnlocked() throws Exception {
        assertTrue(mockPack.isUnlocked(mockPack.getLevelStats().get(0)));
        assertTrue(mockPack.isUnlocked(mockPack.getLevelStats().get(3)));
        assertFalse(mockPack.isUnlocked(mockPack.getLevelStats().get(4)));
    }

    @Test
    public void statsForLevel() throws Exception {
        Level l = mockPack.getLevels().findFirst().get();
        assertTrue(mockPack.statsForLevel(l).isPresent());
        assertTrue(mockPack.statsForLevel(l).get().equals(mockPack.getLevelStats().get(0)));
    }

    @Test
    public void getLevels() throws Exception {
        assertEquals(5, mockPack.getLevels().count());
    }

    @Test
    public void getLevelStats() throws Exception {
        assertEquals(5, mockPack.getLevelStats().size());
    }

    @Test
    public void getTitle() throws Exception {
        assertEquals("Mock Levels", mockPack.getTitle());
    }

    @Test
    public void getKey() throws Exception {
        assertEquals("mock_levels", mockPack.getKey());
    }

    @Test
    public void replaceIfBetter() throws Exception {
        Level l = mockPack.getLevels().findFirst().get();
        LevelStats newls = new PuzzleLevelStats(l, 2);
        assertTrue(mockPack.replaceIfBetter(newls));
        assertEquals(mockPack.getLevelStats().get(0), newls);
    }

    @Test
    public void dontReplaceIfNotBetter() throws Exception {
        Level l = mockPack.getLevels().skip(2).findFirst().get();
        LevelStats newls = new PuzzleLevelStats(l, 2);
        assertFalse(mockPack.replaceIfBetter(newls));
        assertNotEquals(mockPack.getLevelStats().get(0), newls);
    }

}