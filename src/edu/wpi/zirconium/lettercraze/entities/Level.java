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

    private final String key;
    private LevelShape shape;
    private int[] scoreThresholds = new int[3];

    private StringProperty title = new SimpleStringProperty(this, "title", "Game title");
    private ObjectProperty<Path> path = new SimpleObjectProperty<>(this, "path");

    protected Level(int size, String key) {
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
	 * Get an individual threshold value
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

    public Optional<Path> getPath() {
        return Optional.ofNullable(path.get());
    }

    public ObjectProperty<Path> pathProperty() {
        return path;
    }

    public void setPath(Path path) {
        this.path.set(path);
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

    public static Level fromPath(Path fileName) {
    	//String array that will contain 8 standard rows to Level File
    	String[] fileRows = new String[8];
    	
    	
    	try {
    		FileReader fileReader = new FileReader(fileName.toFile());
    		BufferedReader bufferedReader = new BufferedReader(fileReader);
    		
    		String line = null;
    		
    		//Read 8 standard rows into string array
    		for(int i = 0; i < 8; i++){
    			if((line = bufferedReader.readLine()) != null){
    				fileRows[i] = line;
    				System.out.println(line);
    			} else {
    				System.out.println("Improper Formatting of Level File '" + fileName + "'");
    				bufferedReader.close();
    				return null;
    			}
    		}
    		
    		//Get LevelType[0] and Key[1]
    		String[] firstRow = new String[2];
    		int n = 0;
    		for(String val: fileRows[0].split(":"))
    			firstRow[n++] = val;
    		
    		//Get Score Thresholds
    		int[] thresholds = new int[3];
    		int t = 0;
    		for(String num: fileRows[1].split("_"))
    			thresholds[t] = Integer.parseInt(num);
    		
    		//Create a blank Level Shape
    		LevelShape thisShape = LevelShape.all(6);
    		
    		//Determine what type of level file is creating
    		if(firstRow[0].equals("Theme")){
    			
    			ThemeLevel thisLevel = new ThemeLevel(6, firstRow[1]);
    			
    			//Enter all of the letters into the ThemeLevel
    			for(int r = 2; r < 8; r++){
    				for(int c = 0; c < 6; c++){
    					//System.out.println("The character at (" + (r-2) + ", " + c + ") is " + fileRows[r].charAt(c));
    					if(Character.isLetter(fileRows[r].charAt(c))){
    						Letter thisLetter = Letter.valueOf(Character.toString(fileRows[r].charAt(c)));
    						thisLevel.addLetter(thisLetter);
    						thisShape.setTile(r-2, c, true);
    						System.out.println("Set (" + (r-2) + ", " + c + ") to true and " + fileRows[r].charAt(c));
    					} else if(fileRows[r].charAt(c) == '-'){
    						thisShape.setTile(r-2, c, false);
    						System.out.println("Set (" + (r-2) + ", " + c + ") to false");
    					} else { // Improper Formatting
    						System.out.println("The character at (" + (r-2) + ", " + c + ") is invalid");
    						bufferedReader.close();
    						return null;
    					}
    				}
    			}
    			
    			//Enter all of the Words into the ThemeLevel
    			int numWords = 0;
    			while((line = bufferedReader.readLine()) != null){
    				numWords++;
    				if(numWords <= 12){
    					// Add Line to thisLevel.words
    					
    					//check to see if the word contains anything BUT letters
    					for(int s = 0; s < line.length(); s++){
    						if(!(Character.isLetter(line.charAt(s)))){
    							System.out.println("All Words Must Only Contain Letters!");
    							bufferedReader.close();
    							return null;
    						}
    					}
    					//If not, add the word to the list of strings
    					thisLevel.addWord(line);
    				} else {
    					System.out.println("You Have Too Many Words!");
    					bufferedReader.close();
    					return null;
    				}
    			}
    			
    			thisLevel.setShape(thisShape);
    			thisLevel.setThresholds(thresholds[0], thresholds[1], thresholds[2]);
    			
    			bufferedReader.close();
    			return thisLevel;
    			
    			
    		} else if (firstRow[0].equals("Puzzle")){
    			
    			PuzzleLevel thisLevel = new PuzzleLevel(6, firstRow[1]);
    			
    			if((line = bufferedReader.readLine()) != null){
    				thisLevel.setWordLimit(Integer.parseInt(line));
    			} else {
    				System.out.println("You need a new line with a word limit!");
    				bufferedReader.close();
    				
    				return null;
    			}
    			
    			thisLevel.setThresholds(thresholds[0], thresholds[1], thresholds[2]);
    			bufferedReader.close();
    			
    			//Edit Level Shape
    			for(int r = 2; r < 8; r++){
    				for(int c = 0; c < 6; c++){
    					if(fileRows[r].charAt(c) == '+'){
    						thisShape.setTile(r-2, c, true);
    					} else { // -, or a letter, or any other symbol
    						thisShape.setTile(r-2, c, false);
    					}
    				}
    			}
    			
    			thisLevel.setShape(thisShape);
    			
    			return thisLevel;
    			
    		} else if (firstRow[0].equals("Lightning")){
    			
    			LightningLevel thisLevel = new LightningLevel(6, firstRow[1]);
    			
    			if((line = bufferedReader.readLine()) != null){
    				thisLevel.setTimeLimit(Integer.parseInt(line));
    			} else {
    				System.out.println("You need a new line with a time limit!");
    				bufferedReader.close();
    				return null;
    			}
    			
    			thisLevel.setThresholds(thresholds[0], thresholds[1], thresholds[2]);
    			bufferedReader.close();
    			
    			//Edit Level Shape
    			for(int r = 2; r < 8; r++){
    				for(int c = 0; c < 6; c++){
    					if(fileRows[r].charAt(c) == '+'){
    						thisShape.setTile(r-2, c, true);
    					} else { // -, or a letter, or any other symbol
    						thisShape.setTile(r-2, c, false);
    					}
    				}
    			}
    			
    			thisLevel.setShape(thisShape);
    			
    			
    			return thisLevel;
    			
    		} else { //Improper Formatting
    			System.out.println("Didn't recognize level type " + firstRow[0]);
    		}
    		
    		//Close file
    		bufferedReader.close();
    		
    	}
    	catch(FileNotFoundException ex){
    		System.out.println("Unable to open file '" + fileName + "'");
    	}
    	catch(IOException ex){
    		System.out.println("Error reading file '" + fileName + "'");
    	}
    		
    	//Method should not get here because there should not be an error;
    	return null;
    }

    public void save() {
        Path path = getPath().orElseThrow(() -> new IllegalStateException("Can't save a level without a path!"));
        try {
            Files.write(path, Collections.singleton(toFileFormat()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String toFileFormat() {
        String type = getClass().getSimpleName().replace("Level", "");
        return type + ": " + getTitle() + "\n" +
            Arrays.stream(scoreThresholds)
                .mapToObj(Integer::toString).collect(Collectors.joining("_")) + "\n";
    }
}
