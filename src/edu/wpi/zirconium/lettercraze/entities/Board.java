package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;

public class Board {
	protected LevelShape shape;
	protected ArrayList<Tile> tiles;
	
	public Board(LevelShape ls) {
		this.shape = ls;
		this.tiles = null;
	}
	
	public Tile getTile(int row, int col) {
		//TODO return the tile in the spot
		return null;
	}
	
	public boolean addTile(Tile t) {
		return tiles.add(t);
	}
	
	public boolean removeTile(Tile t) {
		return tiles.remove(t);
	}
}
