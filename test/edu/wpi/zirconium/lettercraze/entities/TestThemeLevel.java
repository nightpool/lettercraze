/**
 * 
 */
package edu.wpi.zirconium.lettercraze.entities;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Chris B
 *
 */
public class TestThemeLevel {
	ThemeLevel level, dummyLevel;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		level = new ThemeLevel(6, "1");
		dummyLevel = ThemeLevel.dummy();
		
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
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.ThemeLevel#isOver(edu.wpi.zirconium.lettercraze.entities.Round)}.
	 */
	@Test
	public void testIsOver() {
		Round r = new Round(level);
		assertTrue(level.isOver(r));
		
		Round r2 = new Round(dummyLevel);
		assertFalse(dummyLevel.isOver(r2));
		
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.ThemeLevel#isWordValid(java.lang.String)}.
	 */
	@Test
	public void testIsWordValid() {
		Word w = new Word(Letter.A, Letter.C, Letter.T);
		level.addWord(w);
		assertTrue(level.isWordValid("Act"));
		assertFalse(level.isWordValid("TEST"));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.ThemeLevel#ThemeLevel(int, java.lang.String)}.
	 */
	@Test
	public void testThemeLevel() {
		assertEquals(0, level.getWords().size());
		assertEquals(0, level.getLetters().size());
		assertEquals("1", level.getKey());
		assertEquals("Level 1", level.getTitle());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.ThemeLevel#addWord(edu.wpi.zirconium.lettercraze.entities.Word)}.
	 */
	@Test
	public void testAddWord() {
		Word w = new Word(Letter.A, Letter.C, Letter.T);
		level.addWord(w);
		assertEquals(1, level.getWords().size());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.ThemeLevel#addLetter(edu.wpi.zirconium.lettercraze.entities.Letter)}.
	 */
	@Test
	public void testAddLetter() {
		level.addLetter(Letter.A);
		assertEquals(1, level.getLetters().size());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.ThemeLevel#dummy()}.
	 */
	@Test
	public void testDummy() {
		assertEquals(8, dummyLevel.getWords().size());
		assertEquals(31, dummyLevel.getLetters().size());
		assertEquals("DigitsOfPi", dummyLevel.getKey());
		assertEquals("Dummy 1", dummyLevel.getTitle());
	}

}
