package edu.wpi.zirconium.lettercraze.entities;

import edu.wpi.zirconium.lettercraze.utils.WordTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.IntStream;

public class Level {

    private final String key;
    private LevelShape shape;
    private int[] scoreThresholds = new int[3];

    private StringProperty title = new SimpleStringProperty(this, "title", "Game title");

    protected Level(int size, String key){
        this.key = key;
        this.shape = new LevelShape(size);

        scoreThresholds[0] = 1;
        scoreThresholds[1] = 2;
        scoreThresholds[2] = 3;
    }

    /**
     * Returns the number of starts achieved in the Level based on the given threshold values
     * @param thresholdValue the current {@link #thresholdValue(Round)}
     * @return the number of stars achieved (1, 2, or 3)
     */
    int numAchievedStars(int thresholdValue) {
        if(thresholdValue < scoreThresholds[0]){
            return 0;
        } else if (thresholdValue >= scoreThresholds[0] && thresholdValue < scoreThresholds[1]){
            return 1;
        } else if (thresholdValue >= scoreThresholds[1] && thresholdValue < scoreThresholds[2]){
            return 2;
        } else { // thresholdValue >= scoreThresholds[2];
            return 3;
        }
    }

    /**
     * Return the LetterLhape of this Level.
     * @return this level's levelshape
     */
    LevelShape getShape() {
        return this.shape;
    }

    /**
     * Sets the LevelShape of the Level.
     * @param ls The LevelShape to set this.shape to
     */
    public void setShape(LevelShape ls){
    	this.shape = ls;
    }

    /**
     * Sets the thresholds for 1, 2, and 3 stars, respectively.
     * @param l, m, h integer for each score threshold
     */
    public void setThresholds(int l, int m, int h){
    	this.scoreThresholds[0] = l;
    	this.scoreThresholds[1] = m;
    	this.scoreThresholds[2] = h;
    }

    /**
     * Returns true if the round is over.
     * @param r the Round
     * @return true if it is over
     */
    public boolean isOver(Round r){
        //TODO
        return false;
    }

    /**
     * Gets the key of the Level.
     * @return the Level's key
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets the title of the Level.
     * @return the Level's title
     */
    public String getTitle() {
        return title.get();
    }

    /**
     * Gets the StringProperty of the Level's title.
     * @return the StringProperty of the Level's title
     */
    public StringProperty titleProperty() {
        return title;
    }

    /**
     * Sets the title of the Level.
     * @param title String representing the new title
     */
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

    /**
     * Consults the dictionary to see if the word is valid.
     * @param word word to test if it is in the dictionary
     * @return true if word exists in the dictionary
     */
    public boolean isWordValid(String word){
    	return WordTable.isWord(word);
    }

    /**
     * Loads a dummy level of the given size.
     * @param size the length of one edge of the square level boundary
     * @return the new dummy Level
     */
    public static Level dummy(int size) {
        return dummy(size, "");
    }

    /**
     * Loads a dummy level of the given size.
     * @param size the length of one edge of the square level boundary
     * @param key
     * @return the new dummy Level
     */
    public static Level dummy(int size, String key) {
        Level level = new Level(size, key);
        level.setTitle("Dummy "+size);
        IntStream.range(0, size).forEach(i -> {
            IntStream.range(0, size).forEach(j -> level.getShape().setTile(i, j, true));
//            level.getShape().setTile(i, 0, true);
//            level.getShape().setTile(0, i, true);
//            level.getShape().setTile(i, size-1, true);
//            level.getShape().setTile(size-1, i, true);
        });
        return level;
    }

    public int thresholdValue(Round round) {
        return round.getScore();
    }

    /**
     * @return Should stats for this level be persisted
     */
    public boolean canSave() {
        return getPack().isPresent();
    }

    /**
     * Creats a new LevelStats object representing the persisted data for the current state of the round
     * @param round the round to read data from
     * @return the persistent level stats object
     */
    public LevelStats statsFor(Round round) {
        return null;
    }

    private LevelPack pack;
    public Optional<LevelPack> getPack() {
        return Optional.ofNullable(pack);
    }

    public void setPack(LevelPack pack) {
        this.pack = pack;
    }

    public static Level fromPath(Path resolve) {
        System.out.println("Loading Level '"+resolve+"'...");
        Level level = new PuzzleLevel(6, resolve.getFileName().toString().replace(".txt",""));
        level.setShape(LevelShape.all(6));
        return level;
    }
}
