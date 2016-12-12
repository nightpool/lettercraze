package edu.wpi.zirconium.lettercraze.entities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.stream.Stream;

public class Board {
    protected LevelShape shape;
    protected ObservableList<Tile> tiles;

    /**
     * creates Board object with a LevelShape
     * @param ls the LevelShape describing the board
     */
    public Board(LevelShape ls) {
        this.shape = ls;
        this.tiles = FXCollections.observableArrayList();
    }

    /**
     * adds a Tile to the Board
     * @param t the Tile object to add to the Board
     * @return whether it added the Tile or not
     */
    public boolean addTile(Tile t) {
        return tiles.add(t);
    }

    /**
     * removes a Tile from the Board
     * @param t the Tile object to remove from the Board
     * @return whether it removed the Tile or not
     */
    public boolean removeTile(Tile t) {
        return tiles.remove(t);
    }

    public Stream<Tile> getTiles() {
        return tiles.stream();
    }

    public ObservableList<Tile> observableTiles() {
        return tiles;
    }

    public void clear() {
        this.tiles.clear();
        shape.unblockedPoints()
            .map(p -> new Tile(p, Letter.random()))
            .forEach(this::addTile);
    }

    public static Board dummy(int size) {
        LevelShape shape = Level.dummy(size).getShape();
        return Board.random(shape);
    }

    public static Board random(LevelShape shape) {
        Board board = new Board(shape);
        shape.unblockedPoints()
            .map(p -> new Tile(p, Letter.random()))
            .forEach(board::addTile);
        return board;
    }
}
