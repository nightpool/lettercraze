package edu.wpi.zirconium.lettercraze.entities;

import java.util.Arrays;
import java.util.List;

public class Word {

    protected List<Letter> letters;

    /**
     * creates Word object with a collection of Letters
     * @param letters The new letters to construct the word out of
     */
    public Word(Letter... letters) {
        this.letters = Arrays.asList(letters);
    }

    /**
     * gets the score of the word from all the letters in each word
     * @return the score of the Word
     */
    public int getScore() {
        return letters.stream().mapToInt(Letter::getScore).sum();
    }

    /**
     * gets the score of the word from all the letters in each word
     * @return the validity of the word from the dictionary
     */
    public boolean isValid() {
        return false;    //TODO check the dictionary for this word
    }
}
