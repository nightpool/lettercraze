package edu.wpi.zirconium.lettercraze.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBoard {

    /**
     * Test method for {@link Board#floatAllUp()}.
     */
    @Test
    public void testFloatAllUp() {
        Board b = new Board(new LevelShape(1));
        Point p = new Point(1, 0);
        Point pAbove = new Point(0,0);
        b.getShape().setTile(p, true);
        b.getShape().setTile(pAbove, true);
        assertTrue(b.addTile(new Tile(p, new Letter("A", 0))));
        b.floatAllUp();
        assertEquals(b.getTile(new Point(1, 1)).get().getLetter().getCharacter(),"A");
    }
}
