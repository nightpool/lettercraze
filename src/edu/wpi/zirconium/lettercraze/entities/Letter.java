package edu.wpi.zirconium.lettercraze.entities;

public class Letter {

    protected String character;
    protected int score;

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

}
