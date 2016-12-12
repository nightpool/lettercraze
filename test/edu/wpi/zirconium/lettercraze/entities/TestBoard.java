package edu.wpi.zirconium.lettercraze.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestBoard {

    /**
     * Test method for {@link Board#floatAllUp()}.
     */
    @Test
    public void testFloatAllUp() throws Exception {
        LevelShape shape = LevelShape.all(2);
        shape.setTile(0, 1, false);
        Board b = new Board(shape);

        //   - -      - -
        //  |_|X|    |A|X|
        //  |A|B|    |*|B|
        //   - -      - -
        //  start    finish
        b.addTile(new Tile(new Point(1,0), Letter.A));
        b.addTile(new Tile(new Point(1,1), Letter.B));

        b.floatAllUp();

        Tile tileA = b.getTile(new Point(0,0)).orElseThrow(() -> new RuntimeException("Test Failure"));
        Tile tileB = b.getTile(new Point(1,1)).orElseThrow(() -> new RuntimeException("Test Failure"));
        assertEquals(Letter.A, tileA.getLetter());
        assertEquals(Letter.B, tileB.getLetter());
        assertFalse(b.getTile(new Point(1,0)).isPresent());
    }
}
