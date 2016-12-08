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
		Level level = new Level("1");
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
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Round#doMove()}.
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
