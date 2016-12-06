package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;

public class Board {
	protected LevelShape shape;
	protected ArrayList<Tile> tiles;
	
	public Board(LevelShape ls) {
		this.shape = ls;
	}
	
	public Tile getTile(int row, int col) {
		//TODO return the tile
		return null;
	}
	
	public boolean addTile(Tile t) {
		//TODO add tile to ArrayList of tiles
		return false;
	}
	
	public boolean removeTile(Tile t) {
		//TODO remove tile from ArrayList of tiles
		return false;
	}
}
