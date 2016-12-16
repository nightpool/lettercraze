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
		assertTrue(testDummy.getTitle().toString().equals(" Digits of Pi"));
	
		Level thisTestThemeLevel = Level.fromPath(Paths.get(getClass().getResource("testT3.txt").toURI()));
		assertTrue(thisTestThemeLevel.getTitle().toString().equals(" Digits of Pi"));
	}
	
	@Test(expected = LevelFileMalformationException.class)
	public void testIncorrectLevelTypeParse() throws Exception{
		try{
			Level thisTestIncorrectLevelType = Level.fromPath(Paths.get(getClass().getResource("testIncorrectLevelType.txt").toURI()));
		}
		catch(LevelFileMalformationException ex){
			System.out.println(ex.getMessage());
		}
	}
		
	@Test(expected = LevelFileMalformationException.class)
	public void testIncorrectThemeCharParse() throws Exception{
		try{
			Level thisTestIncorrectThemeChar = Level.fromPath(Paths.get(getClass().getResource("testIncorrectThemeChar.txt").toURI()));
		}
		catch(LevelFileMalformationException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	@Test(expected = LevelFileMalformationException.class)
	public void testIncorrectThemeWordParse() throws Exception{
		try{
			Level thisTestIncorrectThemeWord = Level.fromPath(Paths.get(getClass().getResource("testIncorrectThemeWord.txt").toURI()));
		}
		catch(LevelFileMalformationException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	@Test(expected = LevelFileMalformationException.class)
	public void testIncorrectNumWordsParse() throws Exception{
		try{
			Level thisTestIncorrectNumWords = Level.fromPath(Paths.get(getClass().getResource("testIncorrectNumWords.txt").toURI()));
		}
		catch(LevelFileMalformationException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	@Test
	public void testGoodPuzzleParse() throws Exception{
		Level thisTestGoodPuzzle = Level.fromPath(Paths.get(getClass().getResource("testGoodPuzzle.txt").toURI()));
		assertTrue(thisTestGoodPuzzle.getTitle().toString().equals(" Hourglass"));
	}
	
	@Test
	public void testGoodLightningParse() throws Exception{
		Level thisTestGoodLightning = Level.fromPath(Paths.get(getClass().getResource("testGoodLightning.txt").toURI()));
		assertTrue(thisTestGoodLightning.getTitle().toString().equals(" Hourglass"));
	}
	
	@Test(expected = LevelFileMalformationException.class)
	public void testBadPuzzleParse() throws Exception{
		try{
			Level thisTestBadPuzzle = Level.fromPath(Paths.get(getClass().getResource("testBadPuzzle.txt").toURI()));
		}
		catch(LevelFileMalformationException ex){
			System.out.println(ex.getMessage());
		}	
	}
	
	@Test(expected = LevelFileMalformationException.class)
	public void testBadLightningParse() throws Exception{
		try{
			Level thisTestBadLightning = Level.fromPath(Paths.get(getClass().getResource("testBadLightning.txt").toURI()));
		}
		catch(LevelFileMalformationException ex){
			System.out.println(ex.getMessage());
		}
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
		assertTrue(testSetters.getTitle().toString().equals("Test"));
		assertTrue(testSetters.titleProperty().get().toString().equals("Test"));
	}

}
