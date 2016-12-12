package edu.wpi.zirconium.lettercraze.entities;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Tile {

    private final Letter letter;
    private ObjectProperty<Point> position;

    /**
     * creates Tile object with a Letter, at a Position
     * @param point the position of Tile
     * @param letter the Letter object
     */
    public Tile(Point point, Letter letter) {
        this.letter = letter;
        this.position = new SimpleObjectProperty<>(this, "position", point);
    }

    /**
     * gets the score of the Tile from the Letter object
     * @return the score of the Tile's Letter
     */
    public int getScore() {
        return getLetter().getScore();
    }

    public boolean isAdjacent(Tile t) {
        return getPos().isAdjacent(t.getPos());
    }

    public Letter getLetter() {
        return letter;
    }
    
    /**
     * gets the Position of the Tile
     * @return the Position of the Tile
     */
    public Point getPos() {
        return position.get();
    }

    /**
     * sets the Position of the Tile
     */
    public void setPosition(Point position) {
        this.position.set(position);
    }

    public ObjectProperty<Point> positionProperty() {
        return position;
    }
}
