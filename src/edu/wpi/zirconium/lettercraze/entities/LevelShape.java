package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LevelShape {

    private final int size;
    private List<Boolean> shape;

    public LevelShape(int size) {
        this.size = size;
        this.shape = new ArrayList<Boolean>(size*size);
        shape.addAll(Collections.nCopies(size*size, false));
    }

    public int getSize() {
        return size;
    }
    
    public List<Boolean> getShape(){
    	return shape;
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
}
