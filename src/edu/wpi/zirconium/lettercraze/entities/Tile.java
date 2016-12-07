package edu.wpi.zirconium.lettercraze.entities;

public class Tile {

    private final Letter character;
    private Point position;
    private boolean selected;

    /**
     * creates Tile object with a Letter, at a Position
     * @param c the Letter object
     * @param ps the position of Tile
     */
    public Tile(Letter c, Point ps) {
        this.character = c;
        this.position = ps;
        this.selected = false;
    }

    /**
     * gets the score of the Tile from the Letter object
     * @return the score of the Tile's Letter
     */
    public int getScore() {
        return character.getScore();
    }

    /**
     * gets the position of the Tile
     * @return the Point where the Tile is
     */
    public Point getPoint() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public boolean isAdjacent(Tile t) {
        return getPoint().isAdjacent(t.getPoint());
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
