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
public class TestPuzzleLevel {

	private PuzzleLevel level, dummyLevel;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		level = new PuzzleLevel(6);
		dummyLevel = PuzzleLevel.dummy();
		
		level.setTitle("Level 1");
		dummyLevel.setTitle("Dummy 1");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		level = null;
		assertNull(level);
		dummyLevel = null;
		assertNull(dummyLevel);
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.PuzzleLevel#isOver(edu.wpi.zirconium.lettercraze.entities.Round)}.
	 */
	@Test
	public void testIsOver() {
		Round r = new Round(level);
		assertTrue(level.isOver(r));
		
		Round r2 = new Round(dummyLevel);
		assertFalse(dummyLevel.isOver(r2));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.PuzzleLevel#statsFor(edu.wpi.zirconium.lettercraze.entities.Round)}.
	 */
	@Test
	public void testStatsFor() {
		Round r = new Round(dummyLevel);
		assertEquals(0,level.statsFor(r).thresholdValue());
	}

}
