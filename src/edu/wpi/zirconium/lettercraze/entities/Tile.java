package edu.wpi.zirconium.lettercraze.entities;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Tile {

    private final Letter letter;
    private ObjectProperty<Point> position;

    /**
     * creates Tile object with a Letter, at a Position.
     * @param point the position of Tile
     * @param letter the Letter object
     */
    public Tile(Point point, Letter letter) {
        this.letter = letter;
        this.position = new SimpleObjectProperty<>(this, "position", point);
    }

    /**
     * Gets the score of the Tile from the Letter object.
     * @return the score of the Tile's Letter
     */
    public int getScore() {
        return getLetter().getScore();
    }
    
    /**
     * Returns true if the Tile is adjacent.
     * @param t the Tile to check for adjacency
     * @return true if the Tiles are adjacent
     */
    public boolean isAdjacent(Tile t) {
        return getPos().isAdjacent(t.getPos());
    }
    
    /**
     * Gets the Letter from the Tile.
     * @return the Letter shown on the Tile
     */
    public Letter getLetter() {
        return letter;
    }
    
    /**
     * Gets the Position of the Tile.
     * @return the Position of the Tile
     */
    public Point getPos() {
        return position.get();
    }

    /**
     * Sets the Position of the Tile.
     * @param Point position
     */
    public void setPosition(Point position) {
        this.position.set(position);
    }
    
    /**
     * Gets the ObjectProperty of the Point.
     * @return the ObjectProperty of the Point
     */
    public ObjectProperty<Point> positionProperty() {
        return position;
    }
}
