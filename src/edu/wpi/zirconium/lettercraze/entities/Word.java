package edu.wpi.zirconium.lettercraze.entities;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Word {

    protected final List<Letter> letters;

    /**
     * creates Word object with a collection of Letters
     * @param letters The new letters to construct the word out of
     */
    public Word(Letter... letters) {
        this.letters = Arrays.asList(letters);
    }

    public String asString() {
        return letters.stream().map(Letter::getCharacter).collect(Collectors.joining(""));
    }

    /**
     * gets the score of the word from all the letters in each word
     * @return the score of the Word
     */
    public int getScore() {
        return letters.stream().mapToInt(Letter::getScore).sum() * letters.size();
    }

}
