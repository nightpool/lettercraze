package edu.wpi.zirconium.lettercraze.entities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LevelPackData implements Serializable {

    protected ArrayList<Level> levels;
    protected String saveFile;

    public LevelPackData(String saveFile){  // TODO pull save data, figure this stuff out
        levels = new ArrayList<Level>();  
        
        String fileName= "data.txt";
        try {
        	FileInputStream fin = new FileInputStream(fileName);
        	ObjectInputStream oin = new ObjectInputStream(fin);
			levels = (ArrayList<Level>) oin.readObject();
			oin.close();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    public boolean saveLevels(){ // TODO export save data, figure this stuff out
    	try {
			FileOutputStream saveFile = new FileOutputStream(new File("data.txt"));
			ObjectOutputStream out = new ObjectOutputStream(saveFile);
			out.writeObject(levels);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false; 					
    }
    
    /**
     * Adds the given level to the end of the list of levels, provided it is not already present
     * @param Level to be added
     * @return True if level is successfully added
     */
    public boolean addLevel(Level level){
    	if(!levels.contains(level)){
    		return levels.add(level);
    	}else{
    		return false;
    	}
    }
    
    /**
     * Moves the given level to the given index, first removing the level and closing the gap, and then adding the level by pushing levels farther
     * @param Level to be added, index to add to
     * @return True if level is successfully moved, false if there were two instances of the level (the first will be removed)
     */
    public boolean moveLevel(Level level, int toIndex){
        levels.remove(level);
        if (!levels.contains(level)){
        	levels.add(toIndex, level);
        	return true;
        }
        return false;
    }
    
    /**
     * Removes the given level from the list of levels
     * @param Level to be removed
     * @return True if level is successfully removed
     */
    public boolean removeLevel(Level level){
    	return levels.remove(level);
    }
}

