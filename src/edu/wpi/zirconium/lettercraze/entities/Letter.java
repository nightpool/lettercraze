package edu.wpi.zirconium.lettercraze.entities;

import java.util.Random;

public class Letter {

    protected final String character;
    protected final int score;

    /**
     * creates Letter object with a String and a score
     * @param s the String that is the character or combination of characters (i.e. A,B,C,Qu, etc)
     * @param score the score assigned to the Letter
     */
    public Letter(String s, int score) {
        this.character = s;
        this.score = score;
    }

    /**
     * returns the score of the character
     */
    public int getScore() {
        return score;
    }

    public String getCharacter() {
        return character;
    }

    public static Letter random() {
        Random r = new Random();
        return new Letter(String.valueOf((char)(r.nextInt(26) + 'a')), 0);
    }
}
