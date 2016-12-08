package edu.wpi.zirconium.lettercraze.entities;

public class Level {

    private final String key;
    private LevelShape shape;
    private int[] scoreThresholds = new int[3];

    public Level(String key){
        this.key = key;
        this.shape = new LevelShape(6);

        scoreThresholds[0] = 0;
        scoreThresholds[1] = 0;
        scoreThresholds[2] = 0;
    }

    int numAchievedStars(int score){
        if(score < scoreThresholds[0]){
            return 0;
        } else if (score >= scoreThresholds[0] && score < scoreThresholds[1]){
            return 1;
        } else if (score >= scoreThresholds[1] && score < scoreThresholds[2]){
            return 2;
        } else { // score >= scoreThresholds[2];
            return 3;
        }
    }

    /**
     * Return the letter shape of this level.
     * @return this level's levelshape
     */
    LevelShape getLevelShape() {
        return this.shape;
    }

    boolean isOver(Round r){
        //TODO
        return false;
    }

    public String getKey() {
        return key;
    }
}