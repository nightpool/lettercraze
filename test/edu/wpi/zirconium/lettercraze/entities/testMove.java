/**
 * 
 */
package edu.wpi.zirconium.lettercraze.entities;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mt Landis
 *
 */
public class testMove {
	Level level;
	Round round;
	Tile t1,t2, t3;
	Move move;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		level = Level.dummy(6);
		round = new Round(level);
		t1 = new Tile(new Point(0, 0),Letter.A);
		t2 = new Tile(new Point(1, 0),Letter.C);
		t3 = new Tile(new Point(2, 0),Letter.T);
		move = round.getMoveInProgress();
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
		move = null;
		assertNull(move);
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Move#Move()}.
	 */
	@Test
	public void testMove() {
		move.scoreBinding();
		assertFalse(round.undoMove());
		assertFalse(round.submitMove());
		round.selectTile(t1);
		round.selectTile(t2);
		round.selectTile(t3);
		assertTrue(move.isMoveValid(round));
		assertTrue(round.submitMove());
		move.scoreBinding();
		assertEquals(1, round.getNumWordsFound());
		assertEquals(18, round.getScore());
		assertTrue(round.undoMove());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Move#isMoveValid(edu.wpi.zirconium.lettercraze.entities.Round)}.
	 */
	@Test
	public void testIsMoveValid() {
		round.selectTile(t1);
		round.selectTile(t2);
		round.selectTile(t3);
		assertTrue(move.isMoveValid(round));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Move#canAdd(edu.wpi.zirconium.lettercraze.entities.Tile)}.
	 */
	@Test
	public void testCanAdd() {
		round.selectTile(t1);
		round.selectTile(t2);
		assertTrue(move.canAdd(t3));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Move#addTile(edu.wpi.zirconium.lettercraze.entities.Tile)}.
	 */
	@Test
	public void testAddTile() {
		round.selectTile(t1);
		round.selectTile(t2);
		assertTrue(move.addTile(t3));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Move#canRemove(edu.wpi.zirconium.lettercraze.entities.Tile)}.
	 */
	@Test
	public void testCanRemove() {
		round.selectTile(t1);
		round.selectTile(t2);
		assertTrue(move.canRemove(t2));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Move#removeTile(edu.wpi.zirconium.lettercraze.entities.Tile)}.
	 */
	@Test
	public void testRemoveTile() {
		round.selectTile(t1);
		round.selectTile(t2);
		assertTrue(move.removeTile(t2));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Move#doMove(edu.wpi.zirconium.lettercraze.entities.Round)}.
	 */
	@Test
	public void testDoMove() {
		assertFalse(move.doMove(round)); //basic test
		round.selectTile(t1);
		round.selectTile(t2);
		round.selectTile(t3);
		assertTrue(move.doMove(round));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Move#getWord()}.
	 */
	@Test
	public void testGetWord() {
		round.selectTile(t1);
		round.selectTile(t2);
		round.selectTile(t3);
		assertEquals(new Word(Letter.A, Letter.C, Letter.T).asString() , move.getWord().asString());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Move#getScore()}.
	 */
	@Test
	public void testGetScore() {
		round.selectTile(t1);
		round.selectTile(t2);
		round.selectTile(t3);
		assertEquals(18, move.getScore());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Move#getNumberSelectedTiles()}.
	 */
	@Test
	public void testGetNumberSelectedTiles() {
		round.selectTile(t1);
		round.selectTile(t2);
		round.selectTile(t3);
		assertEquals(3, move.getNumberSelectedTiles());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Move#asString()}.
	 */
	@Test
	public void testAsString() {
		round.selectTile(t1);
		round.selectTile(t2);
		round.selectTile(t3);
		assertEquals("ACT (+18 points)", move.asString());
	}

}
