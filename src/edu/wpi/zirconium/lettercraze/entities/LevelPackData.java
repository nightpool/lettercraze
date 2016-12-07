package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;

public class LevelPackData {
	
	protected ArrayList<Level> levels = new ArrayList<Level>();
	
	protected String saveFile;
	
	public LevelPackData(String saveFile){
		
	}
	
	public boolean saveLevels(){
		return false;
		
	}
	
	public boolean addLevel(Level level){
		return false;
		
	}
	
	public boolean moveLevel(Level level, int i){
		return false;
		
	}
	
	public boolean removeLevel(Level level){
		return false;
		
	}
}

