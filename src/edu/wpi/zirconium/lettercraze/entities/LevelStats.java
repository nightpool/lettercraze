package edu.wpi.zirconium.lettercraze.entities;

public abstract class LevelStats {

	Level level;
	int starsAchieved = 0;
	
	LevelStats(Level l){
		this.level = l;
	}
}
