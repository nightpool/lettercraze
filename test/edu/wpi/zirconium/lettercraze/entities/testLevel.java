/**
 * 
 */
package edu.wpi.zirconium.lettercraze.entities;

import static org.junit.Assert.*;
import java.nio.file.Paths;

import org.junit.Test;

/**
 * @author Joe/Zirconium
 *
 */
public class testLevel {

	@Test
	public void testParseFromFile() throws Exception{
		Level testDummy = ThemeLevel.dummy();
		
		Level thisTestThemeLevel = Level.fromPath(Paths.get(getClass().getResource("testT3.txt").toURI()));
		Level thisTestIncorrectLevelType = Level.fromPath(Paths.get(getClass().getResource("testIncorrectLevelType.txt").toURI()));
		Level thisTestIncorrectThemeChar = Level.fromPath(Paths.get(getClass().getResource("testIncorrectThemeChar.txt").toURI()));
		Level thisTestIncorrectThemeWord = Level.fromPath(Paths.get(getClass().getResource("testIncorrectThemeWord.txt").toURI()));
		Level thisTestIncorrectNumWords = Level.fromPath(Paths.get(getClass().getResource("testIncorrectNumWords.txt").toURI()));
		Level thisTestGoodPuzzle = Level.fromPath(Paths.get(getClass().getResource("testGoodPuzzle.txt").toURI()));
		Level thisTestBadPuzzle = Level.fromPath(Paths.get(getClass().getResource("testBadPuzzle.txt").toURI()));
		Level thisTestGoodLightning = Level.fromPath(Paths.get(getClass().getResource("testGoodLightning.txt").toURI()));
		Level thisTestBadLightning = Level.fromPath(Paths.get(getClass().getResource("testBadLightning.txt").toURI()));
		
		
		
		assertTrue(thisTestThemeLevel.getTitle().toString().equals(" Digits of Pi"));
		assertTrue(testDummy.getTitle().toString().equals(" Digits of Pi"));
		assertNull(thisTestIncorrectLevelType);
		assertNull(thisTestIncorrectThemeChar);
		assertNull(thisTestIncorrectThemeWord);
		assertNull(thisTestIncorrectNumWords);
		assertTrue(thisTestGoodPuzzle.getTitle().toString().equals(" Hourglass"));
		assertTrue(thisTestGoodLightning.getTitle().toString().equals(" Hourglass"));
		assertNull(thisTestBadPuzzle);
		assertNull(thisTestBadLightning);
		
	}

}
