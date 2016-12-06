package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;

public class Round {
	protected Level level;
	
	protected Board board;
	
	protected Move moveInProgress;
	
	/** Stack of recent Moves */
	protected java.util.Stack<Move> completedMoves = new java.util.Stack<Move>();
	
	protected int seconds;
	
	protected int score;
	
	protected ArrayList<Word> wordsFound = new ArrayList<Word>();
	
	/**
	 * creates round object and initializes the board
	 * @param level to fill board with
	 */
	public Round(Level level){
		this.level = level;
		seconds = 0;
		reset();
		
	}
	
	/**
	 * reinitializes the round according to level type.
	 * @return true if board has reset successfully
	 */
	protected boolean reset() {
		score = 0;
		// current time does not reset if level is lightning
		board = new Board(level.getLevelShape());
		moveInProgress = null; // TODO maybe this should be different
		completedMoves.clear();
		wordsFound.clear();
		
	}
	
	/**
	 * ???
	 * @param word
	 * @return
	 */
	public boolean removeWord(Word word){
		
	}
	
	/**
	 * Does the move currently in progress if possible.
	 * @return true if move can be made, false otherwise
	 */
	public boolean doMove() {
		if(moveInProgress.do(this)){
			completedMoves.push(moveInProgress);
			// TODO how to reset the current Move?
			moveInProgress = null;
			return true;
			
		}
		return false;
	}
	
	/**
	 * Undos the last move.
	 * @return true if successful
	 */
	public boolean undoMove() {
		if(moveInProgress){
			// TODO unselect this move???
			
		}
		else{
			if (completedMoves.empty()){
				return false;
			}
			completedMoves.pop();
			return true;
		}
	}
	
	
	/**
	 * Returns the current round score.
	 * @return return the point score
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Returns the current time.
	 * @return the time in seconds
	 */
	public int getTime() {
		return this.seconds;
	}
	
	
	/**
	 * Determines, based on level type, if the game is over. 
	 * @return true if game has ended
	 */
	public boolean isOver() {
		// TODO
		
	}
	
	/**
	 * Increments the number of seconds by one.
	 * @return true if able to increment the time.
	 */
	public boolean incrementTime() {
		this.seconds ++;
	}
	
}
