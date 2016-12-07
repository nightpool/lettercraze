package edu.wpi.zirconium.lettercraze.entities;

public class ThemeLevelStats extends LevelStats{

	int wordsFound;
	String[][] startingLetters = new String[6][6];
	
	ThemeLevelStats(Level l, int words, String[][] layout) {
		super(l);
		this.wordsFound = words;
		this.startingLetters = layout;
	}
}
