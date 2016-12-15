package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LevelShape {

    private final int size;
    private ObservableList<Boolean> shape;

    public LevelShape(int size) {
        this.size = size;
        this.shape =FXCollections.observableArrayList();
        shape.addAll(Collections.nCopies(size*size, false));
    }

    public int getSize() {
        return size;
    }

    public boolean isTile(Point p){
        return this.shape.get(p.getRow() * size + p.getColumn());
    }

    public void setTile(int row, int column, boolean present) {
        this.shape.set(row * size + column, present);
    }

    public void setTile(Point p, boolean present){
        setTile(p.getRow(), p.getColumn(), present);
    }

    public Stream<Point> unblockedPoints() {
        return IntStream.range(0,size*size)
                .mapToObj(i -> new Point(i%size, i/size))
                .filter(this::isTile);
    }

    public static LevelShape all(int size) {
        LevelShape shape = new LevelShape(size);
        shape.shape.replaceAll(c -> true);
        return shape;
    }
    
    /**
     * Gets the ObservableList of bools that represents the currently enabled Tiles.
     * @return ObservableList of bools that represents the currently enabled Tiles
     */
    public ObservableList<Boolean> getTiles() {
        return shape;
    }
    
}
