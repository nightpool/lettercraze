package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;

public class Word {
	
	protected ArrayList<Letter> letters;
	
	/**
	 * creates Word object with a collection of Letters
	 * @param l the ArrayList of Letter objects that goes into to Word
	 */
	public Word(ArrayList<Letter> l) {
		this.letters = l;
	}
	
	/**
	 * gets the score of the word from all the letters in each word
	 * @return the score of the Word
	 */
	public int getScore() {
		int score = 0;
		for (Letter l : letters) score += l.getScore();
		return score;
	}
	
	/**
	 * gets the score of the word from all the letters in each word
	 * @return the validity of the word from the dictionary
	 */
	public boolean isValid() {
		return false;	//TODO check the dictionary for this word
	}
}
