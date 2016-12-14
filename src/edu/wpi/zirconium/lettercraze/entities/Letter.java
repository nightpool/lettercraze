package edu.wpi.zirconium.lettercraze.entities;

import java.util.Random;

/**
 * Enum for letters that go on tiles in letter craze.
 * @author Chris Bove
 *
 */
public enum Letter {
	
	E("E",1),
	T("T",1),
	A("A",2),
	O("O",2),
	I("I",2),
	N("N",2),
	S("S",2),
	H("H",2),
	R("R",2),
	D("D",3),
	L("L",3),
	C("C",3),
	U("U",3),
	M("M",3),
	W("W",3),
	F("F",4),
	G("G",4),
	Y("Y",4),
	P("P",4),
	B("B",4),
	V("V",5),
	K("K",5),
	J("J",7),
	X("X",7),
	QU("QU",11),
	Z("Z",8);

	/** The string of the actual letter character (or Qu) of the letter. */
    protected final String character;
    /** The int point value score of the letter. */
    protected final int score;

    /**
     * creates Letter object with a String and a score.
     * @param s the String that is the character or combination of characters (i.e. A,B,C,Qu, etc)
     * @param score the score assigned to the Letter
     */
    private Letter(String s, int score) {
        this.character = s;
        this.score = score;
    }

    /**
     * Returns the point score of this letter tile.
     * @return int score associated with this letter tile
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the string of this letter tile.
     * @return string letter for this letter (+Qu)
     */
    public String getCharacter() {
        return character;
    }

    /**
     * This generates a new random letter based on probability distribution of letters.
     * for the English language.
     * @return new letter based on proper distribution.
     */
    public static Letter random() {
    	double rand = new Random().nextDouble(); // generate number between 0 and 1
    	Letter letter;
    	// get ready for the cascade of doom...
    	if (rand <= 0.12702) {
    		letter = Letter.E;
    	}
    	else if (rand <= 0.21758) {
    		letter = Letter.T;
    	}
    	else if (rand <= 0.29925) {
    		letter = Letter.A;
    	}
    	else if (rand <= 0.37432) {
    		letter = Letter.O;
    	}
    	else if (rand <= 0.44398) {
    		letter = Letter.I;
    	}
    	else if (rand <= 0.51147) {
    		letter = Letter.N;
    	}
    	else if (rand <= 0.57474) {
    		letter = Letter.S;
    	}
    	else if (rand <= 0.63568) {
    		letter = Letter.H;
    	}
    	else if (rand <= 0.69555) {
    		letter = Letter.R;
    	}
    	else if (rand <= 0.73808) {
    		letter = Letter.D;
    	}
    	else if (rand <= 0.77833) {
    		letter = Letter.L;
    	}
    	else if (rand <= 0.80615) {
    		letter = Letter.C;
    	}
    	else if (rand <= 0.83373) {
    		letter = Letter.U;
    	}
    	else if (rand <= 0.85779) {
    		letter = Letter.M;
    	}
    	else if (rand <= 0.88139) {
    		letter = Letter.W;
    	}
    	else if (rand <= 0.90367) {
    		letter = Letter.F;
    	}
    	else if (rand <= 0.92382) {
    		letter = Letter.G;
    	}
    	else if (rand <= 0.94356) {
    		letter = Letter.Y;
    	}
    	else if (rand <= 0.96285) {
    		letter = Letter.P;
    	}
    	else if (rand <= 0.97777) {
    		letter = Letter.B;
    	}
    	else if (rand <= 0.98755) {
    		letter = Letter.V;
    	}
    	else if (rand <= 0.99527) {
    		letter = Letter.K;
    	}
    	else if (rand <= 0.99680) {
    		letter = Letter.J;
    	}
    	else if (rand <= 0.99830) {
    		letter = Letter.X;
    	}
    	else if (rand <= 0.99925) {
    		letter = Letter.QU;
    	}
    	else {
    		letter = Letter.Z;
    	}
        return letter;
    }
    
}
