package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Board {
    protected LevelShape shape;
    protected List<Tile> tiles;

    /**
     * creates Board object with a LevelShape
     * @param ls the LevelShape describing the board
     */
    public Board(LevelShape ls) {
        this.shape = ls;
        this.tiles = new ArrayList<>();
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
