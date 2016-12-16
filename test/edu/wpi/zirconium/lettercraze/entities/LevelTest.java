package edu.wpi.zirconium.lettercraze.entities;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * @author Joe/Zirconium
 *
 */
public class LevelTest {

	@Test
	public void testParseFromFile() throws Exception {
		ThemeLevel testDummy = ThemeLevel.dummy();

		ThemeLevel thisTestThemeLevel = (ThemeLevel) Level.fromPath(Paths.get(getClass().getResource("testT3.txt").toURI()));
		assertEquals(testDummy.getTitle(), thisTestThemeLevel.getTitle());
		assertEquals(testDummy.getShape().getTiles(), thisTestThemeLevel.getShape().getTiles());
		assertEquals(testDummy.getLetters(), thisTestThemeLevel.getLetters());
		assertThat(thisTestThemeLevel.getWords().stream().collect(Collectors.joining("\n")),
			Matchers.stringContainsInOrder(testDummy.getWords()));
		assertEquals(testDummy.getThreshold(0), thisTestThemeLevel.getThreshold(0));
		assertEquals(testDummy.getThreshold(1), thisTestThemeLevel.getThreshold(1));
		assertEquals(testDummy.getThreshold(2), thisTestThemeLevel.getThreshold(2));
	}

	@Test
	public void testGoodPuzzleParse() throws Exception {
		Level thisTestGoodPuzzle = Level.fromPath(Paths.get(getClass().getResource("testGoodPuzzle.txt").toURI()));
		assertTrue(thisTestGoodPuzzle.getTitle().equals("Hourglass"));
	}

	@Test
	public void testGoodLightningParse() throws Exception {
		Level thisTestGoodLightning = Level.fromPath(Paths.get(getClass().getResource("testGoodLightning.txt").toURI()));
		assertTrue(thisTestGoodLightning.getTitle().equals("Hourglass"));
	}
	
	@Test
	public void testWriteLightning() throws Exception {
		Path path = Paths.get(getClass().getResource("testGoodLightning.txt").toURI());
		Level lightning = Level.fromPath(path);

		String levelString = lightning.toFileFormat();

		List<String> expected = Files.readAllLines(path);
		assertEquals(expected, Arrays.asList(levelString.split("\n")));
	}

	@Test
	public void testWritePuzzle() throws Exception {
		Path path = Paths.get(getClass().getResource("testGoodPuzzle.txt").toURI());

		String levelString = Level.fromPath(path).toFileFormat();

		List<String> expected = Files.readAllLines(path);
		assertEquals(expected, Arrays.asList(levelString.split("\n")));
	}

	@Test
	public void testWriteTheme() throws Exception {
		Path path = Paths.get(getClass().getResource("testT3.txt").toURI());

		String levelString = Level.fromPath(path).toFileFormat();

		List<String> expected = Files.readAllLines(path);
		assertEquals(expected, Arrays.asList(levelString.split("\n")));
	}

	@Test
	public void testSave() throws Exception {
		Path path = Paths.get(getClass().getResource("testT3.txt").toURI());
		Path to = Files.createTempFile("lc", "LevelTestTestSave");

		Level level = Level.fromPath(path);
		level.setPath(to);
		level.save();

		assertEquals(Files.readAllLines(path), Files.readAllLines(to));
	}

	@Test(expected = LevelFileMalformationException.class)
	public void testIncorrectLevelTypeParse() throws Exception {
		Level.fromPath(Paths.get(getClass().getResource("testIncorrectLevelType.txt").toURI()));
	}

	@Test(expected = LevelFileMalformationException.class)
	public void testIncorrectThemeCharParse() throws Exception {
		Level.fromPath(Paths.get(getClass().getResource("testIncorrectThemeChar.txt").toURI()));
	}

	@Test(expected = LevelFileMalformationException.class)
	public void testIncorrectThemeWordParse() throws Exception {
		Level.fromPath(Paths.get(getClass().getResource("testIncorrectThemeWord.txt").toURI()));
	}

	@Test(expected = LevelFileMalformationException.class)
	public void testIncorrectNumWordsParse() throws Exception {
		Level.fromPath(Paths.get(getClass().getResource("testIncorrectNumWords.txt").toURI()));
	}

	@Test(expected = LevelFileMalformationException.class)
	public void testBadPuzzleParse() throws Exception {
		Level.fromPath(Paths.get(getClass().getResource("testBadPuzzle.txt").toURI()));
	}

	@Test(expected = LevelFileMalformationException.class)
	public void testBadLightningParse() throws Exception {
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
