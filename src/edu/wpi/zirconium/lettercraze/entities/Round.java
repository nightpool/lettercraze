package edu.wpi.zirconium.lettercraze.entities;

import javafx.beans.property.SimpleObjectProperty;

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
    protected final Level level;

    /** The board that is formed from the level of this Round. */
    protected Board board;

    /** The current move in progress. Always non-null. */
    protected SimpleObjectProperty<Move> moveInProgress = new SimpleObjectProperty<>();

    /** Stack of recent Moves. */
    protected List<Move> completedMoves;

    /** The number of seconds that the game has been played for.*/
    protected int seconds;

    /** The score accumulated in this level so far. */
    protected int score;

    /** Array list of the words found so far in the game. */
    // TODO implement the logic for this if needed.
    protected ArrayList<Word> wordsFound;

    /**
     * Creates round object and initializes the board with level.
     * @param level to fill board with
     */
    public Round(Level level){
        this.level = level;
        this.completedMoves = new ArrayList<>();
        this.wordsFound = new ArrayList<>();

        seconds = 0;
        reset();
    }

    /**
     * Reinitializes the round according to level type.
     * @return true if board has reset successfully
     */
    public boolean reset() {
        score = 0;
        // current time does not reset if level is lightning
        board = Board.random(level.getShape());
        setMoveInProgress(new Move());
        completedMoves.clear();
        wordsFound.clear();
        return true;
    }

    /**
     * Does the move currently in progress if possible.
     * @return true if move can be made, false otherwise
     */
    public boolean submitMove() {
        Move move = getMoveInProgress();
        if (move.isMoveValid()){
            this.score += move.getScore();
            completedMoves.add(move);
            setMoveInProgress(new Move());
            return true;
        }
        return false;
    }

    /**
     * Undos the last move.
     * @return true if either current move or last move was undone
     */
    public boolean undoMove() {
        // clear out current move if it is in progress
        if (getMoveInProgress().getNumberSelectedTiles() > 0) {
            setMoveInProgress(new Move());
            return true;
        } else {
            // if don't have any completed moves, no undo
            if (completedMoves.size() == 0){
                return false;
            }
            // otherwise, undo last move
            Move lastMove = completedMoves.remove(completedMoves.size() - 1);
            score -= lastMove.getScore();

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
        return this.wordsFound.size();
    }

    /**
     * Determines, based on level type, if the game is over.
     * @return true if game has ended
     */
    public boolean isOver() {
        return this.level.isOver(this);
    }

    /**
     * Returns true if it's possible to select the given tile
     * @param tile the tile in question
     * @return whether it can be selected or now
     */
    public boolean canSelectTile(Tile tile) {
        return getMoveInProgress().canAdd(tile);
    }

    /**
     * Selects the given tile
     * @param tile the tile to be selected
     * @return true if the tile was successfully selected, false otherwise
     */
    public boolean selectTile(Tile tile) {
        return getMoveInProgress().addTile(tile);
    }

    /**
     * Can the player deselect the given tile
     * @param tile the tile in question
     * @return true if the player can deselected the tile, false otherwise
     */
    public boolean canDeselectTile(Tile tile) {
        return getMoveInProgress().canRemove(tile);
    }

    /**
     * Deselects the given tile
     * @param tile the tile to deselect
     * @return true if the tile was successfully deselected, false otherwise
     */
    public boolean deselectTile(Tile tile) {
        return getMoveInProgress().removeTile(tile);
    }

    /**
     * Increments the number of seconds by one.
     */
    public void incrementTime() {
        this.seconds++;
    }

    /**
     * @return the base level for this round
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @return the current state of the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * @return the current, uncompleted move. Always non-null
     */
    public Move getMoveInProgress() {
        return moveInProgress.get();
    }

    /**
     * @return moves the player has made so far
     */
    public List<Move> getCompletedMoves() {
        return completedMoves;
    }

    /**
     * @return number of seconds since the beginning of the game.
     */
    public int getSeconds() {
        return seconds;
    }

    public ArrayList<Word> getWordsFound() {
        return wordsFound;
    }

    public SimpleObjectProperty<Move> moveInProgressProperty() {
        return moveInProgress;
    }

    public void setMoveInProgress(Move moveInProgress) {
        this.moveInProgress.set(moveInProgress);
    }
}
