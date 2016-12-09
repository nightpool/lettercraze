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

    /**
     * This generates a new random letter based on probability distribution of letters
     * for the English language.
     * @return new letter based on proper distribution.
     */
    public static Letter random() {
    	double rand = new Random().nextDouble(); // generate number between 0 and 1
    	String letter;
    	// get ready for the cascade of doom...
    	if (rand <= 0.12702)
    		letter = "E";
    	else if (rand <= 0.21758)
    		letter = "T";
    	else if (rand <= 0.29925)
    		letter = "A";
    	else if (rand <= 0.37432)
    		letter = "O";
    	else if (rand <= 0.44398)
    		letter = "I";
    	else if (rand <= 0.51147)
    		letter = "N";
    	else if (rand <= 0.57474)
    		letter = "S";
    	else if (rand <= 0.63568)
    		letter = "H";
    	else if (rand <= 0.69555)
    		letter = "R";
    	else if (rand <= 0.73808)
    		letter = "D";
    	else if (rand <= 0.77833)
    		letter = "L";
    	else if (rand <= 0.80615)
    		letter = "C";
    	else if (rand <= 0.83373)
    		letter = "U";
    	else if (rand <= 0.85779)
    		letter = "M";
    	else if (rand <= 0.88139)
    		letter = "W";
    	else if (rand <= 0.90367)
    		letter = "F";
    	else if (rand <= 0.92382)
    		letter = "G";
    	else if (rand <= 0.94356)
    		letter = "Y";
    	else if (rand <= 0.96285)
    		letter = "P";
    	else if (rand <= 0.97777)
    		letter = "B";
    	else if (rand <= 0.98755)
    		letter = "V";
    	else if (rand <= 0.99527)
    		letter = "K";
    	else if (rand <= 0.99680)
    		letter = "J";
    	else if (rand <= 0.99830)
    		letter = "X";
    	else if (rand <= 0.99925)
    		letter = "Qu";
    	else
    		letter = "Z";
    	
        return new Letter(letter, 0);
    }
}
