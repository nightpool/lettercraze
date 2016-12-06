package edu.wpi.zirconium.lettercraze.entities;

public class Tile {
	protected Letter character;
	protected Point position;
	protected boolean selected;
	
	/**
	 * creates Tile object with a Letter, at a Position
	 * @param c the Letter object
	 * @param ps the position of Tile
	 */
	public Tile(Letter c, Point ps) {
		this.character = c;
		this.position = ps;
		this.selected = false;
	}
	
	/**
	 * get the score of the Tile from the Letter object
	 */
	public int getScore() {
		return character.getScore();
	}
	
}
