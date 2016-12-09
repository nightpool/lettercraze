package edu.wpi.zirconium.lettercraze.entities;

public class PuzzleLevel extends Level{

    int wordLimit = 0;

    public PuzzleLevel(int size, String key, int wordLimit) {
        super(size, key);
        this.wordLimit = wordLimit;
    }
}
