package edu.wpi.zirconium.lettercraze.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.stream.IntStream;

public class Level {

    private final String key;
    private LevelShape shape;
    private int[] scoreThresholds = new int[3];

    private StringProperty title = new SimpleStringProperty(this, "title", "Game Title");

    Level(int size, String key){
        this.key = key;
        this.shape = new LevelShape(size);

        scoreThresholds[0] = 0;
        scoreThresholds[1] = 1;
        scoreThresholds[2] = 2;
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
    LevelShape getShape() {
        return this.shape;
    }

    boolean isOver(Round r){
        //TODO
        return false;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    /**
     * Load the Level for the given key. Will cache levels to prevent loading them multiple times.
     * @return the new or cached loaded level.
     */
    public static Level get(String levelKey) {
        return Level.dummy(6);
    }

    public static Level dummy(int size) {
        Level level = new Level(size, "");
        IntStream.range(0, size).forEach(i -> {
            IntStream.range(0, size).forEach(j -> level.getShape().setTile(i, j, true));
//            level.getShape().setTile(i, 0, true);
//            level.getShape().setTile(0, i, true);
//            level.getShape().setTile(i, size-1, true);
//            level.getShape().setTile(size-1, i, true);
        });
        return level;
    }

}
