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
public class TestLevelPackData {

	private ThemeLevel dummyLevel1, dummyLevel2;
	private LevelPackData levelPackData;
	private String saveFile;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dummyLevel1 = new ThemeLevel(6, "1");
		dummyLevel2 = ThemeLevel.dummy();
		
		dummyLevel1.setTitle("Level 1");
		dummyLevel2.setTitle("Dummy 1");
		
		saveFile = "save.txt";
		levelPackData = new LevelPackData(saveFile);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		dummyLevel1 = null;
		assertNull(dummyLevel1);
		dummyLevel2 = null;
		assertNull(dummyLevel2);
		
		levelPackData = null;
		assertNull(levelPackData);
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.LevelPackData#LevelPackData(java.lang.String)}.
	 */
	@Test
	public void testLevelPackData() {
		fail("Not yet implemented"); //TODO, requires a save file
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.LevelPackData#saveLevels()}.
	 */
	@Test
	public void testSaveLevels() {
		fail("Not yet implemented"); //TODO, requires a save file
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.LevelPackData#addLevel(edu.wpi.zirconium.lettercraze.entities.Level)}.
	 */
	@Test
	public void testAddLevel() {
		assertTrue(levelPackData.addLevel(dummyLevel1)); //Add levels, make sure they can't be added twice
		assertTrue(levelPackData.addLevel(dummyLevel2));
		assertFalse(levelPackData.addLevel(dummyLevel2));
		assertFalse(levelPackData.addLevel(dummyLevel1));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.LevelPackData#moveLevel(edu.wpi.zirconium.lettercraze.entities.Level, int)}.
	 */
	@Test
	public void testMoveLevel() {
		levelPackData.addLevel(dummyLevel1); //Add levels, move them around, remove them to make sure they are still there and not duplicates
		levelPackData.addLevel(dummyLevel2);
		assertTrue(levelPackData.moveLevel(dummyLevel1, 1));
		assertTrue(levelPackData.moveLevel(dummyLevel2, 1));
		assertTrue(levelPackData.moveLevel(dummyLevel1, 3));
		assertTrue(levelPackData.removeLevel(dummyLevel2));
		assertTrue(levelPackData.removeLevel(dummyLevel1));
		assertFalse(levelPackData.removeLevel(dummyLevel2));
		assertFalse(levelPackData.removeLevel(dummyLevel1));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.LevelPackData#removeLevel(edu.wpi.zirconium.lettercraze.entities.Level)}.
	 */
	@Test
	public void testRemoveLevel() {
		levelPackData.addLevel(dummyLevel1); //Add Levels, remove levels, make sure there are no dupicates
		levelPackData.addLevel(dummyLevel2);
		assertTrue(levelPackData.removeLevel(dummyLevel2));
		assertTrue(levelPackData.removeLevel(dummyLevel1));
		assertFalse(levelPackData.removeLevel(dummyLevel2));
		assertFalse(levelPackData.removeLevel(dummyLevel1));
	}

}
