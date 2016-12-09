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
    	int points;
    	// get ready for the cascade of doom...
    	if (rand <= 0.12702) {
    		letter = "E";
    		points = 1;
    	}
    	else if (rand <= 0.21758) {
    		letter = "T";
    		points = 1;
    	}
    	else if (rand <= 0.29925) {
    		letter = "A";
    		points = 2;
    	}
    	else if (rand <= 0.37432) {
    		letter = "O";
    		points = 2;
    	}
    	else if (rand <= 0.44398) {
    		letter = "I";
    		points = 2;
    	}
    	else if (rand <= 0.51147) {
    		letter = "N";
    		points = 2;
    	}
    	else if (rand <= 0.57474) {
    		letter = "S";
    		points = 2;
    	}
    	else if (rand <= 0.63568) {
    		letter = "H";
    		points = 2;
    	}
    	else if (rand <= 0.69555) {
    		letter = "R";
    		points = 2;
    	}
    	else if (rand <= 0.73808) {
    		letter = "D";
    		points = 3;
    	}
    	else if (rand <= 0.77833) {
    		letter = "L";
    		points = 3;
    	}
    	else if (rand <= 0.80615) {
    		letter = "C";
    		points = 3;
    	}
    	else if (rand <= 0.83373) {
    		letter = "U";
    		points = 3;
    	}
    	else if (rand <= 0.85779) {
    		letter = "M";
    		points = 3;
    	}
    	else if (rand <= 0.88139) {
    		letter = "W";
    		points = 3;
    	}
    	else if (rand <= 0.90367) {
    		letter = "F";
    		points = 4;
    	}
    	else if (rand <= 0.92382) {
    		letter = "G";
    		points = 4;
    	}
    	else if (rand <= 0.94356) {
    		letter = "Y";
    		points = 4;
    	}
    	else if (rand <= 0.96285) {
    		letter = "P";
    		points = 4;
    	}
    	else if (rand <= 0.97777) {
    		letter = "B";
    		points = 4;
    	}
    	else if (rand <= 0.98755) {
    		letter = "V";
    		points = 5;
    	}
    	else if (rand <= 0.99527) {
    		letter = "K";
    		points = 5;
    	}
    	else if (rand <= 0.99680) {
    		letter = "J";
    		points = 7;
    	}
    	else if (rand <= 0.99830) {
    		letter = "X";
    		points = 7;
    	}
    	else if (rand <= 0.99925) {
    		letter = "Qu";
    		points = 11;
    	}
    	else {
    		letter = "Z";
    		points = 8;
    	}
        return new Letter(letter, points);
    }
}
