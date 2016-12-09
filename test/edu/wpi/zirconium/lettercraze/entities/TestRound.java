/**
 * 
 */
package edu.wpi.zirconium.lettercraze.entities;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.wpi.zirconium.lettercraze.entities.Round;
import edu.wpi.zirconium.lettercraze.entities.Level;

/**
 * @author Chris B
 *
 */
public class TestRound {

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#Round(edu.wpi.zirconium.lettercraze.entities.Level)}.
	 */
	@Test
	public void testRound() {
		Level level = Level.dummy(6);
		Round round = new Round(level);
		assertEquals(0, round.getScore());
		assertEquals(0, round.getTime());
		assertEquals(0, round.getNumWordsFound());
		assertFalse(round.isOver());
		assertEquals(level, round.getLevel());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#reset()}.
	 */
	@Test
	public void testReset() {
		Level level = new Level(6, "1");
		Round round = new Round(level);
		assertEquals(0, round.getTime());
		round.incrementTime();
		round.score++;
		round.reset();
		assertEquals(1, round.getTime());
		assertEquals(0,round.getScore());
		
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#doMove()}.
	 */
	@Test
	public void testDoMove() {
		Level level = new Level(6, "1");
		Round round = new Round(level);
		Tile t1 = new Tile(new Point(0, 0),new Letter("a",2));
		Tile t2 = new Tile(new Point(1, 0),new Letter("c",3));
		Tile t3 = new Tile(new Point(2, 0),new Letter("t",4));
		round.moveInProgress.get().addTile(t1);
		round.moveInProgress.get().addTile(t2);
		round.moveInProgress.get().addTile(t3);
		assertTrue(round.submitMove());
		assertEquals(1, round.getNumWordsFound());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#undoMove()}.
	 */
	@Test
	public void testUndoMove() {
		Level level = new Level(6, "1");
		Round round = new Round(level);
		Tile t1 = new Tile(new Point(0, 0),new Letter("a",2));
		Tile t2 = new Tile(new Point(1, 0),new Letter("c",3));
		Tile t3 = new Tile(new Point(2, 0),new Letter("t",4));
		round.moveInProgress.get().addTile(t1);
		round.moveInProgress.get().addTile(t2);
		round.moveInProgress.get().addTile(t3);
		assertTrue(round.submitMove());
		assertEquals(1, round.getNumWordsFound());
		assertTrue(round.undoMove());
		assertEquals(0, round.getNumWordsFound());
	}
	
	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#undoMove()}.
	 */
	@Test
	public void testUndoUncompletedMove() {
		Level level = new Level(6, "1");
		Round round = new Round(level);
		Tile t1 = new Tile(new Point(0, 0),new Letter("a",2));
		Tile t2 = new Tile(new Point(1, 0),new Letter("c",3));
		Tile t3 = new Tile(new Point(2, 0),new Letter("t",4));
		round.moveInProgress.get().addTile(t1);
		round.moveInProgress.get().addTile(t2);
		round.moveInProgress.get().addTile(t3);
		assertTrue(round.undoMove());
		assertEquals(0, round.getNumWordsFound());
		assertTrue(round.moveInProgress.get().selectedTiles.isEmpty());
	}
	
	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#undoMove()}.
	 */
	@Test
	public void testUndoEmptyMove() {
		Level level = new Level(6, "1");
		Round round = new Round(level);
		assertTrue(round.undoMove());
		assertEquals(0, round.getNumWordsFound());
		// TODO see why this throws a null pointer exception.
//		assertTrue(round.moveInProgress.selectedTiles.isEmpty());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#isOver()}.
	 */
	@Test
	public void testIsOver() {
		Level level = new Level(6, "1");
		Round round = new Round(level);
		assertEquals(level.isOver(round), round.isOver());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#incrementTime()}.
	 */
	@Test
	public void testIncrementTime() {
		Level level = new Level(6, "1");
		Round round = new Round(level);
		round.incrementTime();
		assertEquals(1, round.getTime());
		round.incrementTime();
		assertEquals(2, round.getTime());
	}

}
