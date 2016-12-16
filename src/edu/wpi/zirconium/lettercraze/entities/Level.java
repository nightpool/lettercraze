package edu.wpi.zirconium.lettercraze.entities;

import edu.wpi.zirconium.lettercraze.utils.WordTable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import java.io.*;

public class Level {

    private LevelShape shape;
    private int[] scoreThresholds = new int[3];

    private StringProperty title = new SimpleStringProperty(this, "title");
    private ObjectProperty<Path> path = new SimpleObjectProperty<>(this, "path");

    protected Level(int size) {
        this.shape = new LevelShape(size);

        scoreThresholds[0] = 1;
        scoreThresholds[1] = 2;
        scoreThresholds[2] = 3;
    }

    /**
     * Returns the number of starts achieved in the Level based on the given threshold values.
     * @param thresholdValue the current {@link #thresholdValue(Round)}
     * @return the number of stars achieved (1, 2, or 3)
     */
    int numAchievedStars(int thresholdValue) {
        if(thresholdValue < getThreshold(0)){
            return 0;
        } else if (thresholdValue >= getThreshold(0) && thresholdValue < getThreshold(1)){
            return 1;
        } else if (thresholdValue >= getThreshold(1) && thresholdValue < getThreshold(2)){
            return 2;
        } else { // thresholdValue >= getThreshold(2);
            return 3;
        }
    }

    /**
     * Return the LetterLhape of this Level.
     * @return this level's levelshape
     */
    public LevelShape getShape() {
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
     * @param l the low, or first star, threshold
     * @param m the middle, or second star, threshold
     * @param h the high, or third star, threshold
     */
    public void setThresholds(int l, int m, int h){
        this.scoreThresholds[0] = l;
        this.scoreThresholds[1] = m;
        this.scoreThresholds[2] = h;
    }

    /**
     * Set the star threshold values individually.
     * @param i the zero-based index of the threshold to set (0, 1 or 2)
     * @param value the value to set the threshold to
     */
    public void setThreshold(int i, int value) {
        this.scoreThresholds[i] = value;
    }

    /**
     * Get an individual threshold value.
     * @param i the zero-based index of the threshold to get
     * @return the threshold value
     */
    public int getThreshold(int i) {
        return this.scoreThresholds[i];
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

    public Optional<Integer> getTimeLimit() {
        return Optional.empty();
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
        Level level = new Level(size);
        level.setTitle("Dummy "+size);
        IntStream.range(0, size).forEach(i -> {
            IntStream.range(0, size).forEach(j -> level.getShape().setTile(i, j, true));
        });
        return level;
    }
    
    /**
     * Gets the threshold value for the current round.
     * @param round
     * @return
     */
    public int thresholdValue(Round round) {
        return round.getScore();
    }

    /**
     * Populates the Board with random letters.
     * @param board the Board to populate
     */
    public void populateBoard(Board board){
        board.clear(p -> Letter.random());
    }
    
    /**
     * Regenerates the Letter at the given Point.
     * @param p the Point to regenerate the letter at
     * @return the Optional Letter object
     */
    public Optional<Letter> regenLetter(Point p) {
        return Optional.of(Letter.random());
    }

    /**
     * Returns true if the level can be saved.
     * @return true if the pack for this level be present
     */
    public boolean canSave() {
        return getPack().isPresent();
    }
    
    /**
     * Gets the Optional Path object for the save location.
     * @return the Optional Path for the save location
     */
    public Optional<Path> getPath() {
        return Optional.ofNullable(path.get());
    }
    
    /**
     * Gets the ObjectProperty Path object for the save location.
     * @return the ObjectProperty Path for the save location
     */
    public ObjectProperty<Path> pathProperty() {
        return path;
    }
    
    /**
     * Sets the Path for the save location.
     * @param path the Path object for the save location
     */
    public void setPath(Path path) {
        this.path.set(path);
    }

    /**
     * Creates a new LevelStats object representing the persisted data for the current state of the round.
     * @param round the round to read data from
     * @return the persistent LevelStats object
     */
    public LevelStats statsFor(Round round) {
        return null;
    }

    /**
     * @return The default Level Stats object for this level
     */
    public LevelStats initialStats() {
        return null;
    };

    private LevelPack pack;
    /**
     * Gets the LevelPack from the Level.
     * @return the Optional LevelPack from the Level
     */
    public Optional<LevelPack> getPack() {
        return Optional.ofNullable(pack);
    }

    /**
     * Sets the LevelPack of the Level.
     * @param pack the LevelPack to set
     */
    public void setPack(LevelPack pack) {
        this.pack = pack;
    }

    /**
     * @return whether the current level is valid or not
     */
    public boolean isValid() {
        return getThreshold(0) > 0 &&
            getThreshold(1) > 0 &&
            getThreshold(2) > 0 &&
            this.getTitle() != null && !this.getTitle().isEmpty();
    }

    /**
     * Returns the Level saved at the given Path (save location).
     * @param path the Path of the Level's save location
     * @return
     */
    public static Level fromPath(Path path) {
        //String array that will contain 8 standard rows to Level File
        String[] fileRows = new String[8];

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path.toFile());
        } catch (FileNotFoundException e) {
            throw new LevelFileMalformationException("Can't access "+path);
        }

        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;

            //Read 8 standard rows into string array
            for (int i = 0; i < 8; i++) {
                if ((line = bufferedReader.readLine()) != null) {
                    fileRows[i] = line;
                } else {
                    throw new LevelFileMalformationException("Improper Formatting of Level File '" + path + "'");
                }
            }

            //Get LevelType[0] and Key[1]
            String[] firstRow = new String[2];
            int n = 0;
            for (String val : fileRows[0].split(":"))
                firstRow[n++] = val;

            //Get Score Thresholds
            int[] thresholds = new int[3];
            int t = 0;
            for (String num : fileRows[1].split("_")) {
                thresholds[t] = Integer.parseInt(num);
                t++;
            }

            //Create a blank Level Shape
            LevelShape thisShape = LevelShape.all(6);

            //Determine what type of level file is creating
            if (firstRow[0].equals("Theme")) {

                ThemeLevel thisLevel = new ThemeLevel(6);
                thisLevel.setTitle(firstRow[1].trim());
                thisLevel.setPath(path);

                //Enter all of the letters into the ThemeLevel
                for (int r = 2; r < 8; r++) {
                    for (int c = 0; c < 6; c++) {
                        if (Character.isLetter(fileRows[r].charAt(c))) {
                            Letter thisLetter = Letter.valueOf(Character.toString(fileRows[r].charAt(c)));
                            thisLevel.addLetter(thisLetter);
                            thisShape.setTile(r - 2, c, true);
                        } else if (fileRows[r].charAt(c) == '-') {
                            thisShape.setTile(r - 2, c, false);
                        } else {
                            throw new LevelFileMalformationException("The character at (" + (r - 2) + ", " + c + ") is invalid");
                        }
                    }
                }

                //Enter all of the Words into the ThemeLevel
                int numWords = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    numWords++;
                    if (numWords <= 12) {
                        // Add Line to thisLevel.words

                        //check to see if the word contains anything BUT letters
                        for (int s = 0; s < line.length(); s++) {
                            if (!(Character.isLetter(line.charAt(s)))) {
                                throw new LevelFileMalformationException("All Words Must Only Contain Letters!");

                            }
                        }
                        //If not, add the word to the list of strings
                        thisLevel.addWord(line);
                    } else {
                        throw new LevelFileMalformationException("You Have Too Many Words!");

                    }
                }

                thisLevel.setShape(thisShape);
                thisLevel.setThresholds(thresholds[0], thresholds[1], thresholds[2]);

                return thisLevel;

            } else if (firstRow[0].equals("Puzzle")) {

                PuzzleLevel thisLevel = new PuzzleLevel(6);
                thisLevel.setTitle(firstRow[1].trim());
                thisLevel.setPath(path);

                if ((line = bufferedReader.readLine()) != null) {

                    for (int s = 0; s < line.length(); s++) {
                        if (!(Character.isDigit(line.charAt(s)))) {
                            throw new LevelFileMalformationException("Your Word Limit MUST be an integer!");

                        }
                    }

                    thisLevel.setWordLimit(Integer.parseInt(line));
                } else {
                    throw new LevelFileMalformationException("You need a new line with a word limit!");

                }

                thisLevel.setThresholds(thresholds[0], thresholds[1], thresholds[2]);

                //Edit Level Shape
                for (int r = 2; r < 8; r++) {
                    for (int c = 0; c < 6; c++) {
                        if (fileRows[r].charAt(c) == '+') {
                            thisShape.setTile(r - 2, c, true);
                        } else { // -, or a letter, or any other symbol
                            thisShape.setTile(r - 2, c, false);
                        }
                    }
                }

                thisLevel.setShape(thisShape);

                return thisLevel;

            } else if (firstRow[0].equals("Lightning")) {

                LightningLevel thisLevel = new LightningLevel(6);
                thisLevel.setTitle(firstRow[1].trim());
                thisLevel.setPath(path);

                if ((line = bufferedReader.readLine()) != null) {

                    for (int s = 0; s < line.length(); s++) {
                        if (!(Character.isDigit(line.charAt(s)))) {
                            throw new LevelFileMalformationException("Your Time Limit MUST be an integer!");
                        }
                    }

                    thisLevel.setTimeLimit(Integer.parseInt(line));
                } else {
                    throw new LevelFileMalformationException("You need a new line with a time limit!");
                }

                thisLevel.setThresholds(thresholds[0], thresholds[1], thresholds[2]);

                //Edit Level Shape
                for (int r = 2; r < 8; r++) {
                    for (int c = 0; c < 6; c++) {
                        if (fileRows[r].charAt(c) == '+') {
                            thisShape.setTile(r - 2, c, true);
                        } else { // -, or a letter, or any other symbol
                            thisShape.setTile(r - 2, c, false);
                        }
                    }
                }

                thisLevel.setShape(thisShape);

                return thisLevel;

            } else {
                throw new LevelFileMalformationException("Didn't recognize level type " + firstRow[0]);
            }

        } catch (IOException ex) {
            throw new LevelFileMalformationException("How did you get here?");
        }
    }

    /**
     * Saves the Level.
     */
    public void save() {
        Path path = getPath().orElseThrow(() -> new IllegalStateException("Can't save a level without a path!"));
        try {
            System.out.println("Saving Level...\n"+toFileFormat());
            Files.write(path, Collections.singleton(toFileFormat()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the String to put in the save file for the Level.
     * @return the String to put in the save file for the Level
     */
    protected String toFileFormat() {
        String type = getClass().getSimpleName().replace("Level", "");
        return type + ": " + getTitle() + "\n" +
            Arrays.stream(scoreThresholds)
                .mapToObj(Integer::toString).collect(Collectors.joining("_")) + "\n" +
            boardString();
    }

    protected String boardString() {
        int size = this.getShape().getSize();
        StringBuilder builder = new StringBuilder(size *(size +1));
        int i = 0;
        for (boolean tile : this.getShape().getTiles()) {
            builder.append(tile ? "+" : "-");
            i++;
            if (i % size == 0) {
                builder.append("\n");
            }
        }
           return builder.toString().trim();
    }
}
