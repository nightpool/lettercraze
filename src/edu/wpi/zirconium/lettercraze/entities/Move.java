package edu.wpi.zirconium.lettercraze.entities;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Move {

    protected ObservableList<Tile> selectedTiles;
    protected List<Tile> prevTiles;

    public Move() {
        this.selectedTiles = FXCollections.observableArrayList();
    }


    private BooleanBinding valid;

    public BooleanBinding isValidBinding() {
        if (valid == null) {
            valid = Bindings.createBooleanBinding(
                () -> getNumberSelectedTiles() > 0 && getWord().isValid(),
                selectedTiles);
        }
        return valid;
    }

    /**
     * returns if the Move is valid
     * @return boolean whether the move (the word) is valid or not
     */
    public boolean isMoveValid() {
        return isValidBinding().get();
    }

    /**
     * returns if the Tile is valid to add to the word
     * @return boolean whether the Tile is valid to add to the word
     */
    public boolean canAdd(Tile tile) {
        boolean adjacent = lastTile().map(tile::isAdjacent).orElse(true);
        boolean selected = selectedTiles.contains(tile);

        return adjacent && !selected;
    }

    /**
     * creates Word object with a collection of Letters
     * @return whether the move (the word) is valid or not
     */
    public boolean addTile(Tile tile) {
        return selectedTiles.add(tile);
    }

    public boolean canRemove(Tile tile) {
        return lastTile().map(tile::equals).orElse(false);
    }

    public boolean removeTile(Tile t) {
        return selectedTiles.remove(t);
    }

    /**
     * does the move
     * @param r the round
     * @return whether the Move was completed
     */
    public boolean doMove(Round r) {
        if (isMoveValid()) {
            prevTiles = r.getBoard().getTiles().collect(Collectors.toList());
            getSelectedTiles().forEach(t -> r.getBoard().removeTile(t));
            getSelectedTiles().forEach(t ->
                r.getBoard().addTile(new Tile(t.getPos(), Letter.random())));
            return true;
        } else return false;
    }

    private ObjectBinding<Word> word;
    public ObjectBinding<Word> wordBinding() {
        if (word == null) {
            word = Bindings.createObjectBinding(
                () -> new Word(selectedTiles.stream().map(Tile::getLetter).toArray(Letter[]::new)),
                selectedTiles);
        }
        return word;
    }
    public Word getWord() {
        return wordBinding().get();
    };

    private IntegerBinding score;
    public IntegerBinding scoreBinding() {
        if (score == null) {
            score = Bindings.createIntegerBinding(() -> getWord().getScore(),
                wordBinding());
        }
        return score;
    }
    public int getScore() {
        return getWord().getScore();
    }

    private Optional<Tile> lastTile() {
        if (selectedTiles.size() > 0) {
            return Optional.of(selectedTiles.get(selectedTiles.size() - 1));
        } else {
            return Optional.empty();
        }
    }

    public ObservableList<Tile> getSelectedTiles() {
        return selectedTiles;
    }

    public boolean undo(Round round) {
        // TODO Auto-generated method stub
        return false;
    }

    public int getNumberSelectedTiles() {
        return selectedTiles.size();
    }

    public String asString() {
        return String.format("%s (+%d points)", getWord().asString(), getWord().getScore());
    }
}
