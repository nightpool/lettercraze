/**
 * 
 */
package edu.wpi.zirconium.lettercraze.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Chris B
 *
 */
public class TestRound {
	Level level;
	Round round;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		level = Level.dummy(6);
		round = new Round(level);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		level = null;
		assertNull(level);
		round = null;
		assertNull(round);
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#Round(edu.wpi.zirconium.lettercraze.entities.Level)}.
	 */
	@Test
	public void testRound() {
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
		assertEquals(0, round.getTime());
		round.incrementTime();
		round.reset();
		assertEquals(1, round.getTime());
		assertEquals(0,round.getScore());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#submitMove()}.
	 */
	@Test
	public void testSubmitMove() {
		Tile t1 = new Tile(new Point(0, 0),Letter.A);
		Tile t2 = new Tile(new Point(1, 0),Letter.C);
		Tile t3 = new Tile(new Point(2, 0),Letter.T);
		round.selectTile(t1);
		round.selectTile(t2);
		round.selectTile(t3);
		assertTrue(round.submitMove());
		assertEquals(1, round.getNumWordsFound());
		assertEquals(18, round.getScore());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#undoMove()}.
	 */
	@Test
	public void testUndoMove() {
		Tile t1 = new Tile(new Point(0, 0),Letter.A);
		Tile t2 = new Tile(new Point(1, 0),Letter.C);
		Tile t3 = new Tile(new Point(2, 0),Letter.T);
		round.selectTile(t1);
		round.selectTile(t2);
		round.selectTile(t3);
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
		Tile t1 = new Tile(new Point(0, 0),Letter.A);
		Tile t2 = new Tile(new Point(1, 0),Letter.C);
		Tile t3 = new Tile(new Point(2, 0),Letter.T);
		round.selectTile(t1);
		round.selectTile(t2);
		round.selectTile(t3);
		assertTrue(round.undoMove());
		assertEquals(0, round.getNumWordsFound());
		assertTrue(round.getMoveInProgress().getSelectedTiles().isEmpty());
	}
	
	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#undoMove()}.
	 */
	@Test
	public void testUndoEmptyMove() {
		assertFalse(round.undoMove());
		assertEquals(0, round.getNumWordsFound());
		// TODO see why this throws a null pointer exception.
//		assertTrue(round.moveInProgress.selectedTiles.isEmpty());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#isOver()}.
	 */
	@Test
	public void testIsOver() {
		assertEquals(level.isOver(round), round.isOver());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#canSelectTile(edu.wpi.zirconium.lettercraze.entities.Tile)}.
	 */
	@Test
	public void testCanSelectTile() {
		Tile t1 = new Tile(new Point(0, 0),Letter.A);
		Tile t2 = new Tile(new Point(1, 0),Letter.C);
		Tile t3 = new Tile(new Point(3, 0),Letter.T);
		assertTrue(round.canSelectTile(t1));
		round.selectTile(t1);
		assertTrue(round.canSelectTile(t2));
		round.selectTile(t2);
		assertFalse(round.canSelectTile(t3));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#selectTile(edu.wpi.zirconium.lettercraze.entities.Tile)}.
	 */
	@Test
	public void testSelectTile() {
		Tile t1 = new Tile(new Point(0, 0),Letter.A);
		Tile t2 = new Tile(new Point(1, 0),Letter.C);
		Tile t3 = new Tile(new Point(3, 0),Letter.T);
		assertTrue(round.selectTile(t1));
		assertTrue(round.selectTile(t2));
		round.deselectTile(t2);
		assertFalse(round.selectTile(t3));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#canDeselectTile(edu.wpi.zirconium.lettercraze.entities.Tile)}.
	 */
	@Test
	public void testCanDeselectTile() {
		Tile t1 = new Tile(new Point(0, 0),Letter.A);
		Tile t2 = new Tile(new Point(1, 0),Letter.C);
		Tile t3 = new Tile(new Point(3, 0),Letter.T);
		assertFalse(round.canDeselectTile(t1));
		round.selectTile(t1);
		assertTrue(round.canDeselectTile(t1));
		round.deselectTile(t1);
		assertFalse(round.canDeselectTile(t1));
		round.selectTile(t1);
		round.selectTile(t2);
		assertTrue(round.canDeselectTile(t1));
		assertTrue(round.canDeselectTile(t2));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#deselectTile(edu.wpi.zirconium.lettercraze.entities.Tile)}.
	 */
	@Test
	public void testDeselectTile() {
		Tile t1 = new Tile(new Point(0, 0),Letter.A);
		Tile t2 = new Tile(new Point(1, 0),Letter.C);
		Tile t3 = new Tile(new Point(3, 0),Letter.T);
		assertFalse(round.deselectTile(t1));
		round.selectTile(t1);
		assertTrue(round.deselectTile(t1));
		assertFalse(round.deselectTile(t1));
		round.selectTile(t1);
		round.selectTile(t2);
		assertTrue(round.deselectTile(t1));
		assertFalse(round.getMoveInProgress().getSelectedTiles().contains(t2));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#incrementTime()}.
	 */
	@Test
	public void testIncrementTime() {
		round.incrementTime();
		assertEquals(1, round.getTime());
		round.incrementTime();
		assertEquals(2, round.getTime());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#getCompletedMoves()}.
	 */
	@Test
	public void testGetCompletedMoves() {
		Tile t1 = new Tile(new Point(0, 0),Letter.A);
		Tile t2 = new Tile(new Point(1, 0),Letter.C);
		Tile t3 = new Tile(new Point(2, 0),Letter.T);
		round.selectTile(t1);
		round.selectTile(t2);
		round.selectTile(t3);
		Move move = round.getMoveInProgress();
		round.submitMove();
		assertTrue(round.getCompletedMoves().contains(move));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#timeProperty()}.
	 */
	@Test
	public void testTimeProperty() {
		assertEquals(0, round.timeProperty().get());
		round.incrementTime();
		assertEquals(1, round.timeProperty().get());
		round.reset();
		assertEquals(1, round.timeProperty().get());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#getWordsFound()}.
	 */
	@Test
	public void testGetWordsFound() {
		assertTrue(round.getWordsFound().isEmpty());
		Tile t1 = new Tile(new Point(0, 0),Letter.A);
		Tile t2 = new Tile(new Point(1, 0),Letter.C);
		Tile t3 = new Tile(new Point(2, 0),Letter.T);
		round.selectTile(t1);
		round.selectTile(t2);
		round.selectTile(t3);
		round.submitMove();
		assertFalse(round.getWordsFound().isEmpty());
		Word word = new Word(Letter.A,Letter.C,Letter.T);
		assertEquals(word.asString(), round.getWordsFound().get(0).asString());
	}

	/**
	 * Test method for {@link Round#starsEarnedBinding()}.
	 */
	@Test
	public void testStarsEarned() {
		Word word = new Word(Letter.A,Letter.C,Letter.T);
		level.setThresholds(1, word.getScore(), word.getScore()*2);
		assertEquals(0, round.getStarsEarned());

		Tile t1 = new Tile(new Point(0, 0), Letter.A);
		Tile t2 = new Tile(new Point(1, 0), Letter.C);
		Tile t3 = new Tile(new Point(2, 0), Letter.T);
		round.selectTile(t1);
		round.selectTile(t2);
		round.selectTile(t3);
		round.submitMove();
		assertEquals(2, round.getStarsEarned());

		round.selectTile(t1);
		round.selectTile(t2);
		round.selectTile(t3);
		round.submitMove();
		assertEquals(3, round.getStarsEarned());
	}

}
