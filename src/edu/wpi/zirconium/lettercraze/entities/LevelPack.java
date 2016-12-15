package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.io.*;


public class LevelPack {
    private List<Level> levels = new ArrayList<>();
    private List<LevelStats> levelStats = new ArrayList<>();

    protected LevelPackData data;
    private final String title;

    private LevelPack(String title) {
        this.title = title;
    }

    public void saveStats(){

    }

    public boolean isUnlocked(LevelStats level){
        int index = levelStats.indexOf(level) - 1;
        return index < 0 || levelStats.get(index).numAchievedStars() > 0;
    }

    public Optional<LevelStats> statsForLevel(Level level) {
        return levelStats.stream().filter(ls -> ls.getLevel().equals(level)).findFirst();
    }

    
    /**
     * parseFile(String fileName) creates a level from a file.
     * @param fileName A string representing the name of the file you'd like to parse.
     * @return A Level parsed from the fileName input.
     */
    public Level parseFile(String fileName){

    	//String array that will contain 8 standard rows to Level File
    	String[] fileRows = new String[8];
    	
    	
    	try {
    		FileReader fileReader = new FileReader(fileName);
    		BufferedReader bufferedReader = new BufferedReader(fileReader);
    		
    		String line = null;
    		
    		//Read 8 standard rows into string array
    		for(int i = 0; i < 8; i++){
    			if((line = bufferedReader.readLine()) != null){
    				fileRows[i] = line;
    				System.out.println(line);
    			} else {
    				System.out.println("Improper Formatting of Level File " + fileName + "'");
    				break;
    			}
    		}
    		
    		//Get LevelType[0] and Key[1]
    		String[] firstRow = new String[2];
    		int n = 0;
    		for(String val: fileRows[0].split(":"))
    			firstRow[n] = val;
    		
    		//Get Score Thresholds
    		int[] thresholds = new int[3];
    		int t = 0;
    		for(String num: fileRows[1].split("_"))
    			thresholds[t] = Integer.parseInt(num);
    		
    		//Create a blank Level Shape
    		LevelShape thisShape = LevelShape.all(6);
    		
    		//Determine what type of level file is creating
    		if(firstRow[0] == "Theme"){
    			
    			ThemeLevel thisLevel = new ThemeLevel(6, firstRow[1]);
    			
    			//Enter all of the letters into the ThemeLevel
    			for(int r = 2; r < 8; r++){
    				for(int c = 0; c < 6; c++){
    					
    					if(Character.isLetter(fileRows[r].charAt(c))){
    						Letter thisLetter = Letter.valueOf(Character.toString(fileRows[r].charAt(c)));
    						thisLevel.addLetter(thisLetter);
    						thisShape.setTile(r, c, true);
    					} else if(fileRows[r].charAt(c) == '-'){
    						thisShape.setTile(r-2, c, false);
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
    			
    			
    		} else if (firstRow[0] == "Puzzle"){
    			
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
    						thisShape.setTile(r, c, true);
    					} else { // -, or a letter, or any other symbol
    						thisShape.setTile(r, c, false);
    					}
    				}
    			}
    			
    			thisLevel.setShape(thisShape);
    			
    			return thisLevel;
    			
    		} else if (firstRow[0] == "Lightning"){
    			
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
    						thisShape.setTile(r, c, true);
    					} else { // -, or a letter, or any other symbol
    						thisShape.setTile(r, c, false);
    					}
    				}
    			}
    			
    			thisLevel.setShape(thisShape);
    			
    			
    			return thisLevel;
    			
    		} else { //Improper Formatting
    			System.out.println("Improper Formatting of First Row");
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
    
    public static LevelPack dummyPuzzle() {
        LevelPack lp = new LevelPack("Puzzle Levels");
        lp.levelStats = Arrays.asList(
            new PuzzleLevelStats(Level.dummy(6), 1),
            new PuzzleLevelStats(Level.dummy(6), 2),
            new PuzzleLevelStats(Level.dummy(6), 3),
            new PuzzleLevelStats(Level.dummy(6), 0),
            new PuzzleLevelStats(Level.dummy(6), 0));
        return lp;
    }

    public static LevelPack dummyLightning() {
        LevelPack lp = new LevelPack("Lightning Levels");
        lp.levelStats = Arrays.asList(
            new LightningLevelStats(LightningLevel.dummy(), 15),
            new LightningLevelStats(LightningLevel.dummy(), 0),
            new LightningLevelStats(LightningLevel.dummy(), 0));
        return lp;
    }

    public static LevelPack dummyTheme() {
        LevelPack lp = new LevelPack("Theme Levels");
        lp.levelStats = Arrays.asList(
            new ThemeLevelStats(ThemeLevel.dummy(), 8),
            new ThemeLevelStats(ThemeLevel.dummy(), 0));
        return lp;
    }



    public List<Level> getLevels() {
        return levels;
    }

    public List<LevelStats> getLevelStats() {
        return levelStats;
    }

    public String getTitle() {
        return title;
    }
}
