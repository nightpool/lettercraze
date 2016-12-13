package edu.wpi.zirconium.lettercraze.entities;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.ListBinding;
import javafx.beans.binding.ListExpression;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.stream.Collectors;

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
    private SimpleObjectProperty<Move> moveInProgress = new SimpleObjectProperty<>(this, "moveInProgress");

    /** Stack of recent Moves. */
    private ListProperty<Move> completedMoves = new SimpleListProperty<>(this, "completedMoves", FXCollections.observableArrayList());

    /** The number of seconds that the game has been played for.*/
    private SimpleIntegerProperty seconds = new SimpleIntegerProperty(this, "seconds");

    /**
     * Creates round object and initializes the board with level.
     * @param level to fill board with
     */
    public Round(Level level){
        this.level = level;
        this.board = new Board(level.getShape());

        setTime(0);
        reset();
    }

    /**
     * Reinitializes the round according to level type.
     * @return true if board has reset successfully
     */
    public boolean reset() {
        // current time does not reset if level is lightning
        board.clear();
        completedMoves.clear();
        setMoveInProgress(new Move());
        return true;
    }

    /**
     * Does the move currently in progress if possible.
     * @return true if move can be made, false otherwise
     */
    public boolean submitMove() {
        Move move = getMoveInProgress();
        if (move.isMoveValid(this)){
            move.doMove(this);
            completedMoves.add(move);
            setMoveInProgress(new Move());
            return true;
        }
        return false;
    }

    /**
     * Undoes the last move.
     * @return true if either current move or last move was undone
     */
    public boolean undoMove() {
        // clear out current move if it is in progress
        if (getMoveInProgress().getNumberSelectedTiles() > 0) {
            setMoveInProgress(new Move());
            return true;
        } else {
            if (!this.getCompletedMoves().isEmpty()) {
                Move lastMove = this.getCompletedMoves().get(this.getCompletedMoves().size() - 1);
                completedMoves.remove(lastMove);
                return lastMove.undo(this);
            } else {
                return false;
            }
        }
    }

    private IntegerBinding scoreBinding;
    public IntegerBinding scoreBinding() {
        if (scoreBinding == null) {
            scoreBinding = Bindings.createIntegerBinding(
                () -> completedMoves.stream().mapToInt(Move::getScore).sum(),
                completedMoves);
        }
        return scoreBinding;
    }

    /**
     * Returns the current round score.
     * @return return the point score
     */
    public int getScore() {
        return scoreBinding().get();
    }

    /**
     * Returns the current time.
     * @return the time in seconds
     */
    public int getTime() {
        return this.seconds.get();
    }

    /**
     * Returns the number of words successfully found during this round.
     * @return number of words successfully found
     */
    public int getNumWordsFound() {
        return this.completedMoves.size();
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
    	if(!canSelectTile(tile))
    		return false;
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
    	if(!canDeselectTile(tile))
    		return false;
        return getMoveInProgress().removeTile(tile);
    }

    /**
     * Increments the number of seconds by one.
     */
    public void incrementTime() {
        this.setTime(this.getTime() + 1);
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
    public ObservableList<Move> getCompletedMoves() {
        return completedMoves;
    }

    /**
     * @return number of seconds since the beginning of the game.
     */
    public SimpleIntegerProperty timeProperty() {
        return seconds;
    }

    private void setTime(int seconds) {
        this.seconds.set(seconds);
    }

    private ListBinding<Word> wordsFound;
    public ListExpression<Word> getWordsFound() {
        if (wordsFound == null) {
            wordsFound = new ListBinding<Word>() {
                {
                    bind(completedMoves);
                }

                @Override
                public void dispose() {
                   unbind(completedMoves);
                }

                @Override
                public ObservableList<?> getDependencies() {
                    return FXCollections.singletonObservableList(completedMoves);
                }

                @Override
                protected ObservableList<Word> computeValue() {
                   return FXCollections.observableList(
                       completedMoves.stream().map(Move::getWord).collect(Collectors.toList()));
                }
            };
        }
        return wordsFound;
    }

    public SimpleObjectProperty<Move> moveInProgressProperty() {
        return moveInProgress;
    }

    public void setMoveInProgress(Move moveInProgress) {
        this.moveInProgress.set(moveInProgress);
    }
}
