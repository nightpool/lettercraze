package edu.wpi.zirconium.lettercraze.entities;

public class LightningLevelStats extends LevelStats{

    int mostWords;

    LightningLevelStats(Level l, int words) {
        super(l);
        this.mostWords = words;
    }

}
