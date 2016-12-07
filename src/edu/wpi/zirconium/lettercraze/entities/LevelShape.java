package edu.wpi.zirconium.lettercraze.entities;

public class LevelShape {

    private final int size;
    private boolean[][] shape;

    public LevelShape(int size, boolean[][] shape){
        // TODO assert shape is of the right size
        this.size = size;
        this.shape = shape;
    }

    public LevelShape(int size) {
        this.size = size;
        this.shape = new boolean[size][size];
    }

    boolean isTile(int x, int y){
        return this.shape[x][y];
    }

    boolean setTile(int x, int y, boolean tile){
        this.shape[x][y] = tile;
        return true;
    }
}
