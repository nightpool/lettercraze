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
public class TestLightningLevel {

	private LightningLevel level, dummyLevel;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		level = new LightningLevel(6);
		dummyLevel = LightningLevel.dummy();
		
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
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.LightningLevel#isOver(edu.wpi.zirconium.lettercraze.entities.Round)}.
	 */
	@Test
	public void testIsOver() {
		Round r = new Round(level);
		assertTrue(level.isOver(r));
		
		Round r2 = new Round(dummyLevel);
		assertFalse(dummyLevel.isOver(r2));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.LightningLevel#thresholdValue(edu.wpi.zirconium.lettercraze.entities.Round)}.
	 */
	@Test
	public void testThresholdValue() {
		Round r = new Round(dummyLevel);
		assertEquals(0,level.thresholdValue(r));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.LightningLevel#statsFor(edu.wpi.zirconium.lettercraze.entities.Round)}.
	 */
	@Test
	public void testStatsFor() {
		Round r = new Round(dummyLevel);
		assertEquals(0,level.statsFor(r).thresholdValue());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.LightningLevel#dummy()}.
	 */
	@Test
	public void testDummy() {
		Round r = new Round(dummyLevel);
		assertEquals(0, dummyLevel.thresholdValue(r));
	}

}
