package edu.wpi.zirconium.lettercraze.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of a word map to lookup words in a dictionary.
 * <p>
 * This package was imported from Professor Heineman's Lecture package.
 * @author Professor Heineman
 *
 */
public class WordTable {
	static Set<String> words;

    /**
	 * Load up word map. Note that there may be superfluous spaces throughout for formatting
	 * reasons, and these are excised before being added to the map.
	 * 
	 * @throws IOException   if unable to find file 
	 */
	public static void loadWordTable() throws IOException {
		words = new HashSet<>(160_232);

		InputStream resourceAsStream = WordTable.class.getResourceAsStream("/WordTable.sort");
		BufferedReader b = new BufferedReader(new InputStreamReader(resourceAsStream));
		String line = b.readLine();
		while (line != null) {
			words.add(line.trim().toLowerCase());
			line = b.readLine();
		}
	}
	
	/**
	 * Converts word to lowercase and checks whether exists within map.
	 * 
	 * @param s String to check if word.
	 * @return     <code>true</code> if a word in the map; <code>false</code> otherwise.
	 */
	public static boolean isWord(String s) {
		if (words == null) {
			try {
				loadWordTable();
			} catch (IOException ioe) {
				System.err.println("Word Table Not Yet Initialized!");
				return false;
			}
		}
		return words.contains(s.toLowerCase());
	}
}

