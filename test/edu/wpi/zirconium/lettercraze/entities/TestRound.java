/**
 * 
 */
package edu.wpi.zirconium.lettercraze.entities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Chris B
 *
 */
public class TestRound {

	/**
	 * Test method for {@link Round#Round(Level)}.
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
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link Round#submitMove()}}.
	 */
	@Test
	public void testDoMove() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#undoMove()}.
	 */
	@Test
	public void testUndoMove() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#getScore()}.
	 */
	@Test
	public void testGetScore() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#getTime()}.
	 */
	@Test
	public void testGetTime() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#getNumWordsFound()}.
	 */
	@Test
	public void testGetNumWordsFound() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#isOver()}.
	 */
	@Test
	public void testIsOver() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#incrementTime()}.
	 */
	@Test
	public void testIncrementTime() {
		fail("Not yet implemented");
	}

}
