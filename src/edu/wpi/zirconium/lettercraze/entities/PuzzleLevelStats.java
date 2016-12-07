package edu.wpi.zirconium.lettercraze.entities;

public class PuzzleLevelStats extends LevelStats{

	int mostPoints;
	
	PuzzleLevelStats(Level l, int points) {
		super(l);
		this.mostPoints = points;
	}


}
