package edu.wpi.zirconium.lettercraze.entities;

import javafx.beans.binding.ObjectBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

public class Move {

    protected ObservableList<Tile> selectedTiles;
    protected Board prevBoard;

    public Move() {
        this.selectedTiles = FXCollections.observableArrayList();
    }

    /**
     * returns if the Move is valid
     * @return boolean whether the move (the word) is valid or not
     */
    public boolean isMoveValid() {
        return getWord().isValid();
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
            // TODO this is where the board state gets saved.
            // TODO move logic. Does Round do the move, or does Move do the move?
            return true;
        } else return false;
    }

    private ObjectBinding<Word> word;
    public ObjectBinding<Word> wordBinding() {
        if (word == null) {
            word = new ObjectBinding<Word>() {
                {
                    this.bind(selectedTiles);
                }
                @Override
                public void dispose() {
                    this.unbind(selectedTiles);
                }

                @Override
                public ObservableList<?> getDependencies() {
                    return FXCollections.singletonObservableList(selectedTiles);
                }

                @Override
                protected Word computeValue() {
                    return new Word(selectedTiles.stream().map(Tile::getLetter).toArray(Letter[]::new));
                }
            };
        }
        return word;
    }
    public Word getWord() {
        return wordBinding().get();
    };

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
}
