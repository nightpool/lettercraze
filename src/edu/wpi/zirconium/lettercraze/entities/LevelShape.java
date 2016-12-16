package edu.wpi.zirconium.lettercraze.entities;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LevelShape {

    private final int size;
    private ObservableList<Boolean> shape;

    /**
     * Creates a LevelShape with square dimensions size by size.
     * @param size Height and Width of LevelShape.
     */
    public LevelShape(int size) {
        this.size = size;
        this.shape = FXCollections.observableArrayList();
        shape.addAll(Collections.nCopies(size*size, false));
    }

    /**
     * size getter method.
     * @return the size used to create the LevelShape.
     */
    public int getSize() {
        return size;
    }

    /**
     * isTile(Point p) tells user if tile at given point is active or not.
     * @param p the point at which the tile exists.
     * @return true if the tile is active, false if it is not.
     */
    public boolean isTile(Point p){
        return this.shape.get(p.getRow() * size + p.getColumn());
    }

    /**
     * setTile(int row, int column, boolean present) sets whether or not the tile is active.
     * @param row the row coordinate that the tile exists in.
     * @param column the column coordinate that the tile exists in.
     * @param present true if the tile at (row, column) should be active, otherwise false.
     */
    public void setTile(int row, int column, boolean present) {
        this.shape.set(row * size + column, present);
    }

    /**
     * setTile(Point p, boolean present) functions the same at setTile.
     * @param p The Point at which the tile exists.
     * @param present present if the tile at (point) should be active, otherwise false.
     */
    public void setTile(Point p, boolean present){
        setTile(p.getRow(), p.getColumn(), present);
    }

    /**
     * Toggle the tile at a given point
     * @param p the point to toggle the tile at
     */
    public void toggleTile(Point p){
        setTile(p, !isTile(p));
    }
    
    /**
     * unblockedPoints() gets the user a stream of all points that a tile can be placed.
     * @return a stream of points that a tile can be placed.
     */
    public Stream<Point> unblockedPoints() {
        return points().filter(this::isTile);
    }

    /**
     * points() gets the user a stream of all of the points in a shape.
     * @return a stream of all points in a LevelShape
     */
    public Stream<Point> points() {
        return IntStream.range(0,size*size)
            .mapToObj(i -> new Point(i/size, i%size));
    }

    /**
     * Gets the shape as an ObservableList of Booleans.
     * @return the shape as an ObservableList of Booleans
     */
    public ObservableList<Boolean> getTiles() {
        return shape;
    }
    
    /**
     * Returns the BooleanBinding at the Point.
     * @param p the Point to get the BooleanBinding from
     * @return the BooleanBinding representing the availability of the tile
     */
    public BooleanBinding bindingAt(Point p) {
        return Bindings.booleanValueAt(shape, p.getRow() * size + p.getColumn());
    }

    /**
     * all(int size) creates a LevelShape with all points/tiles set to true.
     * @param size the size of the LevelShape to be created.
     * @return A LevelShape with size by size area of points set to true.
     */
    public static LevelShape all(int size) {
        LevelShape shape = new LevelShape(size);
        shape.shape.replaceAll(c -> true);
        return shape;
    }

    /**
     * Gets the index of the given Point in the Stream of Tiles.
     * @param p the Point
     * @return the index of that Point in the Stream of Tiles
     */
    public int indexOf(Point p) {
        return this.unblockedPoints().collect(Collectors.toList()).indexOf(p);
    }
}
