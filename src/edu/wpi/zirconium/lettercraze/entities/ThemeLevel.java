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
}
