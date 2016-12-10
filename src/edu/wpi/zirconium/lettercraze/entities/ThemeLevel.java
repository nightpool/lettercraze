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
    
    public static ThemeLevel dummy(){
		ThemeLevel digitsOfPi = new ThemeLevel(6, "DigitsOfPi");
    	
		List<Word> dOPWords = new ArrayList<>();
		List<Letter> dOPLetters = new ArrayList<>();
		
		Letter T1 = new Letter("T", 0);
		Letter P2 = new Letter("P", 0);
		Letter O3 = new Letter("O", 0);
		Letter O4 = new Letter("O", 0);
		Letter N5 = new Letter("N", 0);
		Letter E6 = new Letter("E", 0);
		
		Letter H7 = new Letter("H", 0);
		Letter R8 = new Letter("R", 0);
		Letter I9 = new Letter("I", 0);
		Letter O10 = new Letter("O", 0);
		
		Letter U11 = new Letter("U", 0);
		Letter E12 = new Letter("E", 0);
		Letter O13 = new Letter("O", 0);
		Letter F14 = new Letter("F", 0);
		Letter N15 = new Letter("N", 0);
		
		Letter O16 = new Letter("O", 0);
		Letter T17 = new Letter("T", 0);
		Letter N18 = new Letter("N", 0);
		Letter E19 = new Letter("E", 0);
		Letter E20 = new Letter("E", 0);
		Letter I21 = new Letter("I", 0);

		Letter T22 = new Letter("T", 0);
		Letter W23 = new Letter("W", 0);
		Letter I24 = new Letter("I", 0);
		Letter V25 = new Letter("V", 0);
		
		Letter F26 = new Letter("F", 0);
		Letter R27 = new Letter("R", 0);
		Letter E28 = new Letter("E", 0);
		Letter N29 = new Letter("N", 0);
		Letter N30 = new Letter("N", 0);
		Letter E31 = new Letter("E", 0);
		
		
		Word three = new Word(T1, H7, R8, E12, E19);
		Word point = new Word(P2, O3, I9, N18, T17);
		Word one1 = new Word(O4, N5, E6);
		Word four = new Word(F26, O16, U11, R27);
		Word one2 = new Word(O10, N15, E20);
		Word five = new Word(F14, I21, V25, E31);
		Word nine = new Word(N30, I24, N29, E28);
		Word two = new Word(T22, W23, O13);
		
		
		dOPWords.add(three);
		dOPWords.add(point);
		dOPWords.add(one1);
		dOPWords.add(four);
		dOPWords.add(one2);
		dOPWords.add(five);
		dOPWords.add(nine);
		dOPWords.add(two);
		
		dOPLetters.add(T1);
		dOPLetters.add(P2);
		dOPLetters.add(O3);
		dOPLetters.add(O4);
		dOPLetters.add(N5);
		dOPLetters.add(E6);
		dOPLetters.add(H7);
		dOPLetters.add(R8);
		dOPLetters.add(I9);
		dOPLetters.add(O10);
		dOPLetters.add(U11);
		dOPLetters.add(E12);
		dOPLetters.add(O13);
		dOPLetters.add(F14);
		dOPLetters.add(N15);
		dOPLetters.add(O16);
		dOPLetters.add(T17);
		dOPLetters.add(N18);
		dOPLetters.add(E19);
		dOPLetters.add(E20);
		dOPLetters.add(I21);
		dOPLetters.add(T22);
		dOPLetters.add(W23);
		dOPLetters.add(I24);
		dOPLetters.add(V25);
		dOPLetters.add(F26);
		dOPLetters.add(R27);
		dOPLetters.add(E28);
		dOPLetters.add(N29);
		dOPLetters.add(N30);
		dOPLetters.add(E31);
		
		
		digitsOfPi.words = dOPWords;
		digitsOfPi.letters = dOPLetters;
		
		LevelShape dOPShape = new LevelShape(6);
		
		dOPShape.setTile(0, 0, true);
		dOPShape.setTile(0, 1, true);
		dOPShape.setTile(0, 2, true);
		dOPShape.setTile(0, 3, true);
		dOPShape.setTile(0, 4, true);
		dOPShape.setTile(0, 5, true);
		
		dOPShape.setTile(1, 0, true);
		dOPShape.setTile(1, 1, true);
		dOPShape.setTile(1, 3, true);
		dOPShape.setTile(1, 5, true);
		
		dOPShape.setTile(2, 0, true);
		dOPShape.setTile(2, 2, true);
		dOPShape.setTile(2, 3, true);
		dOPShape.setTile(2, 4, true);
		dOPShape.setTile(2, 5, true);
		
		dOPShape.setTile(3, 0, true);
		dOPShape.setTile(3, 1, true);
		dOPShape.setTile(3, 2, true);
		dOPShape.setTile(3, 3, true);
		dOPShape.setTile(3, 4, true);
		dOPShape.setTile(3, 5, true);
		
		dOPShape.setTile(4, 1, true);
		dOPShape.setTile(4, 2, true);
		dOPShape.setTile(4, 3, true);
		dOPShape.setTile(4, 5, true);
		
		dOPShape.setTile(5, 0, true);
		dOPShape.setTile(5, 1, true);
		dOPShape.setTile(5, 2, true);
		dOPShape.setTile(5, 3, true);
		dOPShape.setTile(5, 4, true);
		dOPShape.setTile(5, 5, true);
		
		digitsOfPi.setShape(dOPShape);
		
		digitsOfPi.setThresholds(6, 7, 8);
    	
    	return digitsOfPi;
    	
    }
}

