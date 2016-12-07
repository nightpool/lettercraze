package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;
import java.util.List;

public class ThemeLevel extends Level{

    List<Word> words;
    List<Letter> letters;

    ThemeLevel(String key) {
        super(key);
        this.words = new ArrayList<>();
        this.letters = new ArrayList<>();
    }
}
