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
public class TestLetter {

	private Letter letter;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		letter = Letter.B;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		letter = null;
		assertNull(letter);
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Letter#Letter(java.lang.String, int)}.
	 */
	@Test
	public void testLetter() {
		assertEquals("B", letter.getCharacter());
		assertEquals(4, letter.getScore());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Letter#getScore()}.
	 */
	@Test
	public void testGetScore() {
		assertEquals(4, letter.getScore());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Letter#getCharacter()}.
	 */
	@Test
	public void testGetCharacter() {
		char test1 = 2;
		
		assertEquals("B", letter.getCharacter());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Letter#random()}.
	 */
	@Test
	public void testRandom() {
		Letter testLetter;
				for(int i = 0; i < 26; i++){
					String testString = Character.toString((char)(65 + i));
					if (testString.equalsIgnoreCase("Q"))
						testString = "QU";
					do{
						testLetter = Letter.random();
					}while (!testLetter.getCharacter().equalsIgnoreCase(testString));

					assertEquals(testString,testLetter.getCharacter());
				}
		//try over and over until the letter is the correct one
	}

}
