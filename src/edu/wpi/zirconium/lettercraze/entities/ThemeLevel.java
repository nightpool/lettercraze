package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;
import java.util.List;

public class ThemeLevel extends Level{

    List<Word> words;
    List<Letter> letters;

    ThemeLevel(int size, String key) {
        super(size, key);
        this.words = new ArrayList<>();
        this.letters = new ArrayList<>();
    }

    @Override
    public boolean isOver(Round r){
        return r.getCompletedMoves().size() == this.words.size();
    }

    /**
     * 
     * @param w Word to add to the Array List of words.
     */
    public void addWord(Word w){
    	this.words.add(w);
    }
    
    /**
     * 
     * @param l Letter to add to the Array List of letters.
     * Must add letters in order of arrangement on the board from top to bottom, left to right.
     */
    public void addLetter(Letter l){
    	this.letters.add(l);
    }
    
    /**
     * 
     * @return The list of words that make up this theme level
     */
    public List<Word> getWords(){
    	return this.words;
    }
    
    /**
     * 
     * @return The list of letters that make up this theme level
     */
    public List<Letter> getLetters(){
    	return this.letters;
    }
    
    
    public static ThemeLevel dummy(){
        ThemeLevel digitsOfPi = new ThemeLevel(6, "DigitsOfPi");

        //List<Word> dOPWords = new ArrayList<>();
        //List<Letter> dOPLetters = new ArrayList<>();

        Letter T1 = Letter.T;
        Letter P2 = Letter.P;
        Letter O3 = Letter.O;
        Letter O4 = Letter.O;
        Letter N5 = Letter.N;
        Letter E6 = Letter.E;

        Letter H7 = Letter.H;
        Letter R8 = Letter.R;
        Letter I9 = Letter.I;
        Letter O10 = Letter.O;

        Letter U11 = Letter.U;
        Letter E12 = Letter.E;
        Letter O13 = Letter.O;
        Letter F14 = Letter.F;
        Letter N15 = Letter.N;

        Letter O16 = Letter.O;
        Letter T17 = Letter.T;
        Letter N18 = Letter.N;
        Letter E19 = Letter.E;
        Letter E20 = Letter.E;
        Letter I21 = Letter.I;

        Letter T22 = Letter.T;
        Letter W23 = Letter.W;
        Letter I24 = Letter.I;
        Letter V25 = Letter.V;

        Letter F26 = Letter.F;
        Letter R27 = Letter.R;
        Letter E28 = Letter.E;
        Letter N29 = Letter.N;
        Letter N30 = Letter.N;
        Letter E31 = Letter.E;

        Word three = new Word(T1, H7, R8, E12, E19);
        Word point = new Word(P2, O3, I9, N18, T17);
        Word one1 = new Word(O4, N5, E6);
        Word four = new Word(F26, O16, U11, R27);
        Word one2 = new Word(O10, N15, E20);
        Word five = new Word(F14, I21, V25, E31);
        Word nine = new Word(N30, I24, N29, E28);
        Word two = new Word(T22, W23, O13);

        digitsOfPi.addWord(three);
        digitsOfPi.addWord(point);
        digitsOfPi.addWord(one1);
        digitsOfPi.addWord(four);
        digitsOfPi.addWord(one2);
        digitsOfPi.addWord(five);
        digitsOfPi.addWord(nine);
        digitsOfPi.addWord(two);

        digitsOfPi.addLetter(T1);
        digitsOfPi.addLetter(P2);
        digitsOfPi.addLetter(O3);
        digitsOfPi.addLetter(O4);
        digitsOfPi.addLetter(N5);
        digitsOfPi.addLetter(E6);
        digitsOfPi.addLetter(H7);
        digitsOfPi.addLetter(R8);
        digitsOfPi.addLetter(I9);
        digitsOfPi.addLetter(O10);
        digitsOfPi.addLetter(U11);
        digitsOfPi.addLetter(E12);
        digitsOfPi.addLetter(O13);
        digitsOfPi.addLetter(F14);
        digitsOfPi.addLetter(N15);
        digitsOfPi.addLetter(O16);
        digitsOfPi.addLetter(T17);
        digitsOfPi.addLetter(N18);
        digitsOfPi.addLetter(E19);
        digitsOfPi.addLetter(E20);
        digitsOfPi.addLetter(I21);
        digitsOfPi.addLetter(T22);
        digitsOfPi.addLetter(W23);
        digitsOfPi.addLetter(I24);
        digitsOfPi.addLetter(V25);
        digitsOfPi.addLetter(F26);
        digitsOfPi.addLetter(R27);
        digitsOfPi.addLetter(E28);
        digitsOfPi.addLetter(N29);
        digitsOfPi.addLetter(N30);
        digitsOfPi.addLetter(E31);

        //digitsOfPi.words = dOPWords;
        //digitsOfPi.letters = dOPLetters;

        LevelShape dOPShape = LevelShape.all(6);

        dOPShape.setTile(1, 2, false);
        dOPShape.setTile(1, 4, false);
        dOPShape.setTile(2, 1, false);
        dOPShape.setTile(4, 0, false);
        dOPShape.setTile(4, 4, false);

        digitsOfPi.setShape(dOPShape);

        digitsOfPi.setThresholds(6, 7, 8);

        return digitsOfPi;

    }
}
