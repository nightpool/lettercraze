package edu.wpi.zirconium.lettercraze.entities;

import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Joe/Zirconium
 *
 */
public class LevelTest {

	@Test
	public void testParseFromFile() throws Exception{
		Level testDummy = ThemeLevel.dummy();
		assertTrue(testDummy.getTitle().equals("Digits of Pi"));

		Level thisTestThemeLevel = Level.fromPath(Paths.get(getClass().getResource("testT3.txt").toURI()));
		assertTrue(thisTestThemeLevel.getTitle().equals("Digits of Pi"));
	}

	@Test(expected = LevelFileMalformationException.class)
	public void testIncorrectLevelTypeParse() throws Exception{
		Level.fromPath(Paths.get(getClass().getResource("testIncorrectLevelType.txt").toURI()));
	}

	@Test(expected = LevelFileMalformationException.class)
	public void testIncorrectThemeCharParse() throws Exception{
		Level.fromPath(Paths.get(getClass().getResource("testIncorrectThemeChar.txt").toURI()));
	}

	@Test(expected = LevelFileMalformationException.class)
	public void testIncorrectThemeWordParse() throws Exception{
		Level.fromPath(Paths.get(getClass().getResource("testIncorrectThemeWord.txt").toURI()));
	}

	@Test(expected = LevelFileMalformationException.class)
	public void testIncorrectNumWordsParse() throws Exception{
		Level.fromPath(Paths.get(getClass().getResource("testIncorrectNumWords.txt").toURI()));
	}

	@Test
	public void testGoodPuzzleParse() throws Exception{
		Level thisTestGoodPuzzle = Level.fromPath(Paths.get(getClass().getResource("testGoodPuzzle.txt").toURI()));
		assertTrue(thisTestGoodPuzzle.getTitle().equals("Hourglass"));
	}

	@Test
	public void testGoodLightningParse() throws Exception{
		Level thisTestGoodLightning = Level.fromPath(Paths.get(getClass().getResource("testGoodLightning.txt").toURI()));
		assertTrue(thisTestGoodLightning.getTitle().equals("Hourglass"));
	}

	@Test(expected = LevelFileMalformationException.class)
	public void testBadPuzzleParse() throws Exception{
		Level.fromPath(Paths.get(getClass().getResource("testBadPuzzle.txt").toURI()));
	}

	@Test(expected = LevelFileMalformationException.class)
	public void testBadLightningParse() throws Exception{
		Level.fromPath(Paths.get(getClass().getResource("testBadLightning.txt").toURI()));
	}

	@Test
	public void testSettersAndGetters(){
		Level testSetters = new ThemeLevel(6);

		testSetters.setShape(LevelShape.all(6));
		Point p = new Point(3,4);
		assertTrue(testSetters.getShape().isTile(p));

		testSetters.setThreshold(2, 800);
		assertEquals(testSetters.getThreshold(2), 800);

		testSetters.setTitle("Test");
		assertTrue(testSetters.getTitle().equals("Test"));
		assertTrue(testSetters.titleProperty().get().equals("Test"));
	}

}
