package edu.wpi.zirconium.lettercraze.entities;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

public class TestBoard {

    /**
     * Test method for {@link Board#floatAllUp()}.
     */
    @Test
    public void testFloatAllUp() {
        Board b = new Board(new LevelShape(2));                     //   _ _      _ _
        Point p10 = new Point(1, 0);                                //  |_|X|    |A|X|
        Point p00 = new Point(0, 0);                                //  |A|B|    |*|B|
        Point p11 = new Point(1, 1);                                //  start    finish
        Point p01 = new Point(0, 1);
        b.getShape().setTile(p10, true);
        b.getShape().setTile(p00, true);
        b.getShape().setTile(p11, true);
        assertTrue(b.addTile(new Tile(p10, new Letter("A", 0))));   //This should float up to 0,0
        assertTrue(b.addTile(new Tile(p11, new Letter("B", 0))));   //This should stay where it is
        b.floatAllUp();
        assertTrue(b.getTile(p00).isPresent());
        assertTrue(b.getTile(p10).isPresent());
        assertTrue(b.getTile(p11).isPresent());
        assertFalse(b.getTile(p01).isPresent());
    }
}
