package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Executable state of a level in the Letter Craze Player Application.
 * <p>
 *
 * <p>
 * @author Christopher Bove (cpbove@wpi.edu)
 */
public class Round {
    /** The level that is loaded into this current round.    */
    protected Level level;

    /** The board that is formed from the level of this Round. */
    protected Board board;

    /** the current move in progress. Either null or happenning. */
    protected Move moveInProgress;

    /** Stack of recent Moves. */
    protected List<Move> completedMoves;

    /** The number of seconds that the game has been played for.*/
    protected int seconds;

    /** The score accumulated in this level so far. */
    protected int score;

    /** The number of words found in this round so far. */
    protected int numWordsFound;

    /** Array list of the words found so far in the game. */
    // TODO implement the logic for this if needed.
    protected ArrayList<Word> wordsFound;

    /**
     * Creates round object and initializes the board with level.
     * @param level to fill board with
     */
    public Round(Level level){
        this.level = level;
        seconds = 0;
        reset();
        completedMoves = new ArrayList<>();
        wordsFound = new ArrayList<>();
    }

    /**
     * Reinitializes the round according to level type.
     * @return true if board has reset successfully
     */
    public boolean reset() {
        score = 0;
        numWordsFound = 0;
        // current time does not reset if level is lightning
        board = new Board(level.getLevelShape());
        moveInProgress = new Move(); // TODO maybe this should be different
        completedMoves.clear();
        wordsFound.clear();
        return true;
    }

    /**
     * Does the move currently in progress if possible.
     * @return true if move can be made, false otherwise
     */
    public boolean doMove() {
        if(moveInProgress.doMove(this)){
            this.score += moveInProgress.getScore();
            numWordsFound ++;
            completedMoves.add(moveInProgress);
            // TODO how to reset the current Move?
            moveInProgress = null;

            return true;
        }
        return false;
    }

    /**
     * Undos the last move.
     * @return true if either current move or last move was undone
     */
    public boolean undoMove() {
        // clear out current move if it is in progress (not null)
        if(moveInProgress.getNumberSelectedTiles() == 0){
            moveInProgress = null;
            return true;
        } else {
            // if don't have any completed moves, no undo
            if (completedMoves.size() == 0){
                return false;
            }
            // otherwise, undo last move
            Move lastMove = completedMoves.remove(completedMoves.size() - 1);
            score -= lastMove.getScore();
            numWordsFound--;

            return lastMove.undo(this);

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
     * Returns the number of words successfully found during this round.
     * @return number of words successfully found
     */
    public int getNumWordsFound() {
        return this.numWordsFound;
    }

    /**
     * Determines, based on level type, if the game is over.
     * @return true if game has ended
     */
    public boolean isOver() {
        return this.level.isOver(this);
    }

    /**
     * Increments the number of seconds by one.
     */
    public void incrementTime() {
        this.seconds++;
    }

}
