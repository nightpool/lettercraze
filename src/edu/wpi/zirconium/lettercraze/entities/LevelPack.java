package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;


public class LevelPack {
	protected ArrayList<LevelStats> levelStats = new ArrayList<LevelStats>();
	
	protected LevelPackData data;
	
	//protected File saveFile; //TODO Evan! (dunno what to import)
	
	public LevelPack(String saveFile){
	
	}

	public void saveStats(){
		
	}
	
	public boolean isUnlocked(Level level){
		return false;
		
	}
	
	public LevelStats unlockedLevels(){
		return null;
		
	}
	
	public LevelStats lastUnlockedLevel(){
		return null;
		
	}
}
