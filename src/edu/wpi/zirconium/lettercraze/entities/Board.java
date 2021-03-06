package edu.wpi.zirconium.lettercraze.entities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Board {
    protected LevelShape shape;
    protected ObservableList<Tile> tiles;

    /**
     * Creates Board object with a LevelShape.
     * @param ls the LevelShape describing the board
     */
    public Board(LevelShape ls) {
        this.shape = ls;
        this.tiles = FXCollections.observableArrayList();
    }

    /**
     * Adds a Tile to the Board.
     * @param t the Tile object to add to the Board
     * @return whether it added the Tile or not
     */
    public boolean addTile(Tile t) {
        return tiles.add(t);
    }

    /**
     * Removes a Tile from the Board.
     * @param t the Tile object to remove from the Board
     * @return whether it removed the Tile or not
     */
    public boolean removeTile(Tile t) {
        return tiles.remove(t);
    }

    /**
     * Gets the stream of Tiles that are on the board.
     * @return the Stream of Tiles that are on the board
     */
    public Stream<Tile> getTiles() {
        return tiles.stream();
    }
    
    /**
     * Gets the Optional Tile at the given position.
     * @param p the Point where you want to get the Optional Tile
     * @return Optional Tile at the Point
     */
    public Optional<Tile> getTile(Point p) {
        return tiles.stream().filter(t -> p.equals(t.getPos())).findFirst();
    }
    
    /**
     * Floats all the tiles upward.
     */
    public void floatAllUp() {
        shape.unblockedPoints()
            .filter(p -> !getTile(p).isPresent())
            .forEach(p -> getNextTile(p).ifPresent(t -> t.setPosition(p)));
    }
    
    /**
     * Gets the next non-empty tile in the column.
     * @param point the point to start searching downwards from
     * @return Tile that is not empty
     */
    private Optional<Tile> getNextTile(Point point) {
        return this.getTiles()
            .filter(t -> t.getPos().getColumn() == point.getColumn())
            .filter(t -> t.getPos().getRow() >= point.getRow())
            .min((t, t2) -> t.getPos().getRow() - t2.getPos().getRow());
    }
    
    /**
     * Gets the stream of Tiles that are on the board as an ObservableList of Tiles.
     * @return the Stream of Tiles that are on the board as an ObservableList of Tiles
     */
    public ObservableList<Tile> observableTiles() {
        return tiles;
    }

    /**
     * Clears the board, generating new tiles appropriate for the level type.
     * @param tileFactory factory for generating tiles.
     */
    public void clear(Function<Point, Letter> tileFactory) {
        this.tiles.clear();
        shape.unblockedPoints()
            .map(p -> new Tile(p, tileFactory.apply(p)))
            .forEach(this::addTile);
    }
    
    /**
     * Creates the dummy Board of the given size.
     * @param size the dimension of one side of the square LevelShape
     * @return Dummy Board that was generated.
     */
    public static Board dummy(int size) {
        LevelShape shape = Level.dummy(size).getShape();
        return Board.random(shape);
    }
    
    /**
     * Creates Board with a LevelShape that contains random Tiles.
     * @param shape the LevelShape describing the board
     * @return New board with random tiles.
     */
    public static Board random(LevelShape shape) {
        Board board = new Board(shape);
        shape.unblockedPoints()
            .map(p -> new Tile(p, Letter.random()))
            .forEach(board::addTile);
        return board;
    }
    
    /**
     * Gets the LevelShape of the Board.
     * @return the LevelShape of the Board
     */
    public LevelShape getShape() {
        return shape;
    }
}
