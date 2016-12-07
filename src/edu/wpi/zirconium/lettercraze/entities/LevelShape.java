package edu.wpi.zirconium.lettercraze.entities;

public class LevelShape {

	boolean[][] isTile = new boolean[6][6];
	
	LevelShape(boolean[][] shape){
		this.isTile = shape;
	}
	
	boolean isTile(int x, int y){
		if (isTile[x][y] == true){
			return true;
		} else {
			return false;
		}
	}
	
	boolean setTile(int x, int y, boolean tile){
		isTile[x][y] = tile;
		return true;
	}
}
