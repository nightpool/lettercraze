package edu.wpi.zirconium.lettercraze.entities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;
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
    
    /**
     * gets the Optional Tile at the given position
     * @param p the Point where you want to get the Optional<Tile>
     * @return Optional Tile at the Point
     */
    public Optional<Tile> getTile(Point p) {
    	for (Tile t : tiles) {
    	    boolean atPos = t.getPos().isAt(p);
    		if (atPos) return Optional.of(t);
    	}
    	return Optional.empty();
    }
    
    /**
     * floats all the tiles upward, generating new tiles until all slots are filled
     */
    public void floatAllUp() {
    	for (int row = 0; row <= shape.getSize(); row ++) {
    		for (int col = 0; col <= shape.getSize(); col ++) {
    			Point p = new Point(row, col);
    			boolean isTile = shape.isTile(p);
    			boolean isPresent = getTile(p).isPresent();
        		if (isTile && !isPresent) {
        			Optional<Tile> nextNonEmpty = getNextTile(col);
        			if (nextNonEmpty.isPresent()) nextNonEmpty.get().setPosition(p);
        			else tiles.add(new Tile(p,Letter.random()));
        		}
        	}
    	}
    }
    
    /**
     * Gets the next non-empty tile in the column
     * @param col the column to search
     * @return Tile that is not empty
     */
    private Optional<Tile> getNextTile(int col) {
        return this.getTiles()
            .filter(t -> t.getPos().getColumn() == 0)
            .min((t, t2) -> t.getPos().getRow() - t2.getPos().getRow());
//	    for (int row = 0; row <= shape.getSize(); row ++) {
//			Point pBelow = new Point(row, col);
//			Optional<Tile> tBelow = getTile(pBelow);
//			if (shape.isTile(pBelow) && tBelow.isPresent()) {
//			    return tBelow.get();
//			}
//		}
//	    return null;
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
    
    public LevelShape getShape() {
        return shape;
    }
}
