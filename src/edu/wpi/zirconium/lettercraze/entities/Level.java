package edu.wpi.zirconium.lettercraze.entities;

public class Level {

	String key;
	LevelShape shape;
	int[] scoreThresholds = new int[3];
	
	Level(String key, LevelShape shape, int[] scoreThresh){
		this.key = key;
		this.shape = shape;
		this.scoreThresholds = scoreThresh;
	}
}
