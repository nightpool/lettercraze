/**
 * 
 */
package edu.wpi.zirconium.lettercraze.entities;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mt Landis
 *
 */
public class TestWord {

	private Word testWord;
	private Word validWord;
	private Word inValidWord;
	
	private Letter letterA;
	private Letter letterC;
	private Letter letterT;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		letterA = Letter.A;
		letterC = Letter.C;
		letterT = Letter.T;
		
		validWord = new Word(letterC, letterA, letterT);
		inValidWord = new Word(letterT, letterC, letterA);	
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		letterA = letterC = letterT = null;
		validWord = inValidWord = null;
		assertNull(letterA);
		assertNull(letterC);
		assertNull(letterT);
		assertNull(validWord);
		assertNull(inValidWord);
		
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Word#Word(edu.wpi.zirconium.lettercraze.entities.Letter[])}.
	 */
	@Test
	public void testWord() {
		testWord = new Word(letterA, letterC, letterT);
		assertEquals("ACT", testWord.asString());
		assertEquals(18, testWord.getScore());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Word#asString()}.
	 */
	@Test
	public void testAsString() {
		assertEquals("CAT", validWord.asString());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Word#getScore()}.
	 */
	@Test
	public void testGetScore() {
		assertEquals(18, inValidWord.getScore());
		assertEquals(18, validWord.getScore());
	}

}
