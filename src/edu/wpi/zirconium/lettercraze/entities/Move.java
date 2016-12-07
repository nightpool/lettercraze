package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;
import java.util.List;

public class Move {
    protected Word word;
    protected List<Tile> selectedTiles;
    protected Board prevBoard;

    public Move() {
        this.word = new Word();
        this.selectedTiles = new ArrayList<>();
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
        boolean adjacent = lastTile().isAdjacent(t);
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
            // TODO this is where the board state gets saved.
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
        return word.getScore();
    }

    private Tile lastTile() {
        return selectedTiles.get(selectedTiles.size() - 1);
    }

    public boolean undo(Round round) {
        // TODO Auto-generated method stub
        return false;
    }

    public int getNumberSelectedTiles() {
        return selectedTiles.size();
    }

}
