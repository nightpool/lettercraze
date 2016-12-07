package edu.wpi.zirconium.lettercraze.entities;

public abstract class LevelStats {

	private final Level level;
	private int starsAchieved;
	
	LevelStats(Level l){
		this.level = l;
		this.starsAchieved = 0;
	}

	public Level getLevel() {
		return level;
	}
}
