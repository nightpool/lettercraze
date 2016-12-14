package edu.wpi.zirconium.lettercraze.entities;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Move {

    protected ObservableList<Tile> selectedTiles;
    protected List<Tile> prevTiles;

    /**
     * creates Move object and initialized with an empty ObservableArrayList of selected Tiles.
     */
    public Move() {
        this.selectedTiles = FXCollections.observableArrayList();
    }

    /**
     * Returns true if the Move is valid based on Round parameters.
     * @param r Round to base validity of move upon.
     * @return boolean true if the move (the word) is valid
     */
    public boolean isMoveValid(Round r) {
        return getNumberSelectedTiles() >= 3
            && r.getLevel().isWordValid(getWord().asString());
    }

    /**
     * Returns if the Tile is valid to add to the word.
     * @return boolean whether the Tile is valid to add to the word
     */
    public boolean canAdd(Tile tile) {
        boolean adjacent = lastTile().map(tile::isAdjacent).orElse(true);
        boolean selected = selectedTiles.contains(tile);

        return adjacent && !selected;
    }

    /**
     * Creates Word object with a collection of Letters.
     * @return whether the move (the word) is valid or not
     */
    public boolean addTile(Tile tile) {
        return selectedTiles.add(tile);
    }

    /**
     * Returns true if the Tile can be removed.
     * @return true if the Tile can be removed
     */
    public boolean canRemove(Tile tile) {
        return lastTile().map(tile::equals).orElse(false);
    }
    
    /**
     * Removes the Tile from the Move's list of Tiles that form the word.
     * @return true if the Tile was removed
     */
    public boolean removeTile(Tile t) {
        return selectedTiles.remove(t);
    }

    /**
     * Does the move.
     * @param r the round
     * @return whether the Move was completed
     */
    public boolean doMove(Round r) {
        if (isMoveValid(r)) {
            prevTiles = new ArrayList<Tile>();
            for (Tile t : r.getBoard().getTiles().collect(Collectors.toList())) {
                prevTiles.add(new Tile(new Point(t.getPos().getRow(),t.getPos().getColumn()), t.getLetter()));
            }
            getSelectedTiles().forEach(t -> r.getBoard().removeTile(t));
            r.getBoard().floatAllUp();
            r.getLevel().getShape().unblockedPoints()
                .filter(p -> !r.getBoard().getTile(p).isPresent())
                .forEach(p -> r.getBoard().addTile(new Tile(p, Letter.random())));
            return true;
        } else return false;
    }

    private ObjectBinding<Word> word;
    /**
     * Gets the ObjectBinding that represents the Word.
     * @return the ObjectBinging of the Word
     */
    public ObjectBinding<Word> wordBinding() {
        if (word == null) {
            word = Bindings.createObjectBinding(
                () -> new Word(selectedTiles.stream().map(Tile::getLetter).toArray(Letter[]::new)),
                selectedTiles);
        }
        return word;
    }
    
    /**
     * Gets the Word from the currently selected Move.
     * @return the Word currently being formed
     */
    public Word getWord() {
        return wordBinding().get();
    };

    private IntegerBinding score;
    
    /**
     * Gets the IntegerBinding that represents the score of the currently selected word.
     * @return IntegerBinding that represents the score of the currently selected word
     */
    public IntegerBinding scoreBinding() {
        if (score == null) {
            score = Bindings.createIntegerBinding(() -> getWord().getScore(),
                wordBinding());
        }
        return score;
    }
    
    /**
     * Gets the score of the currently selected word.
     * @return the score of the currently selected word
     */
    public int getScore() {
        return getWord().getScore();
    }
    
    /**
     * Gets the last Tile in the currently selected word.
     * @return the last Tile in the word
     */
    private Optional<Tile> lastTile() {
        if (selectedTiles.size() > 0) {
            return Optional.of(selectedTiles.get(selectedTiles.size() - 1));
        } else {
            return Optional.empty();
        }
    }
    /**
     * Gets the ObservableList of Tiles that represents the currently selected Tiles.
     * @return ObservableList of Tiles that represents the currently selected Tiles
     */
    public ObservableList<Tile> getSelectedTiles() {
        return selectedTiles;
    }
    
    /**
     * Checks the validity of the undo.
     * @param round the Round object
     * @return true if the Move can be undone
     */
    public boolean canUndo(Round round) {
        return !prevTiles.isEmpty();
    }
    
    /**
     * Undoes the move, setting the Round to the previous state.
     * @param round the Round
     * @return true if the move was undone, false if not
     */
    public boolean undo(Round round) {
        if (canUndo(round)) {
            round.getBoard().observableTiles().clear();
            round.getBoard().observableTiles().addAll(prevTiles);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Gets the number of currently selected Tiles.
     * @return the number of currently selected Tiles
     */
    public int getNumberSelectedTiles() {
        return selectedTiles.size();
    }
    
    /**
     * Returns the currently selected word as a String.
     * @return the String that represents the currently selected word
     */
    public String asString() {
        return String.format("%s (+%d points)", getWord().asString(), getWord().getScore());
    }
}
