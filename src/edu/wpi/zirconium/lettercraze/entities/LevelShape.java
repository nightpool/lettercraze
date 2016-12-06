package edu.wpi.zirconium.lettercraze.entities;

public class LevelShape {

	boolean[][] isTile = new boolean[6][6];
	
	LevelShape(boolean[][] shape){
		this.isTile = shape;
	}
}
