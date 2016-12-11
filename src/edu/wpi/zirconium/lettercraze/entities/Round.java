package edu.wpi.zirconium.lettercraze.entities;

import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /** the current move in progress. Either null or happenning. */
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
        setMoveInProgress(null);
        completedMoves.clear();
        wordsFound.clear();
        return true;
    }

    /**
     * Does the move currently in progress if possible.
     * @return true if move can be made, false otherwise
     */
    public boolean submitMove() {
        Optional<Move> moveInProgress = getMoveInProgress();
        if (!moveInProgress.isPresent()) {
            return false;
        }
        Move move = moveInProgress.get();
        if (move.isMoveValid()){
            this.score += move.getScore();
            completedMoves.add(move);
            this.moveInProgress = null;
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
        if(getMoveInProgress().isPresent()){
            setMoveInProgress(null);
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

    public boolean canSelectTile(Tile tile) {
        return getMoveInProgress().map(m -> m.canAdd(tile)).orElse(true);
    }

    public boolean selectTile(Tile t) {
        return getMoveInProgress().orElseGet(() -> {
            Move m = new Move();
            this.setMoveInProgress(m);
            return m;
        }).addTile(t);
    }

    public boolean canDeselectTile(Tile tile) {
        return getMoveInProgress().map(m -> m.canRemove(tile)).orElse(false);
    }

    public boolean deselectTile(Tile t) {
        return getMoveInProgress().map(m -> m.removeTile(t)).orElse(false);
    }

    /**
     * Increments the number of seconds by one.
     */
    public void incrementTime() {
        this.seconds++;
    }

    public Level getLevel() {
        return level;
    }

    public Board getBoard() {
        return board;
    }

    public Optional<Move> getMoveInProgress() {
        return Optional.ofNullable(moveInProgress.get());
    }

    public List<Move> getCompletedMoves() {
        return completedMoves;
    }

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
