package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;

public class Board {
	protected LevelShape shape;
	protected ArrayList<Tile> tiles;
	
	/**
	 * creates Board object with a LevelShape
	 * @param ls the LevelShape describing the board
	 */
	public Board(LevelShape ls) {
		this.shape = ls;
		this.tiles = null;
	}
	
	/**
	 * gets the Tile from the given position
	 * @param row the row to get the Tile from
	 * @param col the column to get the Tile from
	 * @return the Tile at the position
	 */
	public Tile getTile(int row, int col) {
		//TODO return the tile in the spot
		return null;
	}
	
	/**
	 * adds a Tile to the Board
	 * @param t the Tile object to add to the Board
	 * @return whether it added the Tile or not
	 */
	public boolean addTile(Tile t) {
		return tiles.add(t);
	}
	
	/**
	 * removes a Tile from the Board
	 * @param t the Tile object to remove from the Board
	 * @return whether it removed the Tile or not
	 */
	public boolean removeTile(Tile t) {
		return tiles.remove(t);
	}
}
