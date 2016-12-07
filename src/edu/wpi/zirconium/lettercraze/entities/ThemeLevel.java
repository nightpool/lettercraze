package edu.wpi.zirconium.lettercraze.entities;

public class ThemeLevel extends Level{

	Word[] words = new Word[0];//TODO Change this 0 to something else!
	String[][] letters = new String[6][6];
	
	ThemeLevel(String key) {
		super(key);
	}

}
