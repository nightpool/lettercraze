package edu.wpi.zirconium.lettercraze.entities;

public class ThemeLevelStats extends LevelStats{

	private int wordsFound;
	
	ThemeLevelStats(Level l) {
		super(l);
		this.wordsFound = 0;
	}

	public int getWordsFound() {
		return wordsFound;
	}

	public void setWordsFound(int wordsFound) {
		this.wordsFound = wordsFound;
	}
}
