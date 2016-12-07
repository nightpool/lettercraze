package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;

public class Move {
	protected Word word;
	protected ArrayList<Tile> selectedTiles;
	protected Board prevBoard;
	
	/**
	 * creates Word object with a collection of Letters
	 * @param prevBoard the Board object that represents the state of the board before this move is made
	 */
	public Move(Board prevBoard) {
		this.prevBoard = prevBoard;
	}
	
	/**
	 * returns if the Move is valid
	 * @return boolean whether the move (the word) is valid or not
	 */
	public boolean isMoveValid() {
		return word.isValid();
	}
	
	/**
	 * returns if the Tile is valid to add to the word
	 * @return boolean whether the Tile is valid to add to the word
	 */
	public boolean isTileValid(Tile t) {
		boolean adjacent = (lastTile().getPoint().adjacent(t.getPoint()));
		boolean selected = selectedTiles.contains(t);
		
		return adjacent && !selected;
	}
	
	/**
	 * creates Word object with a collection of Letters
	 * @return whether the move (the word) is valid or not
	 */
	public boolean addTile(Tile t) {
		return selectedTiles.add(t);
	}
	
	/**
	 * removes the last Tile to be selected and returns it
	 * @return the last Tile to be selected
	 */
	public Tile popTile() {
		Tile t = lastTile();
		if (selectedTiles.remove(t)) return t;
		else return null;
	}
	
	/**
	 * does the move
	 * @param r the round
	 * @return whether the Move was completer
	 */
	public boolean doMove(Round r) {
		if (isMoveValid()) {
			//TODO move logic. Does Round do the move, or does Move do the move?
			return true;
		} else return false;
	}
	
	/**
	 * returns the score of the current
	 * @param r the round
	 * @return whether the Move was completer
	 */
	public int getScore() {
		int score = 0;
		for (Tile t : selectedTiles) score += t.getScore();
		return score;
	}
	
	private Tile lastTile() {
		return selectedTiles.get(selectedTiles.size() - 1);
	}
	
}
