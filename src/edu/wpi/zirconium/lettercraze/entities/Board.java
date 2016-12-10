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
    
    /**
     * gets the tile at the given position
     * @param p the Point where you want to get the tile
     * @return Tile at the Point; if no Tile at the Point, returns null
     */
    public Tile getTile(Point p) {
    	for (Tile t : tiles) {
    		if (t.getPos().equals(p)) return t;
    	}
    	return null;
    }
    
    /**
     * floats all the tiles upward, generating new tiles until all slots are filled
     */
    public void floatAllUp() {
    	for (int row = 0; row <= 5; row ++) {
    		for (int col = 0; col <= 5; col ++) {
    			Point p = new Point(row, col);
        		if (shape.isTile(p) && getTile(p).equals(null)) {
        			Tile nextNonEmpty = getNextTile(col);
        			if (!nextNonEmpty.equals(null)) nextNonEmpty.setPosition(p);
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
    private Tile getNextTile(int col) {
	    for (int row = 0; row <= 5; row ++) {
			Point pBelow = new Point(row, col);
			Tile tBelow = getTile(pBelow);
			if (shape.isTile(pBelow) && !tBelow.equals(null)) {
				return tBelow;
			}
		}
	    return null;
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
