package edu.wpi.zirconium.lettercraze.entities;

public class Point {

    protected int row;
    protected int column;

    /**
     * creates Point object at the given row and column
     * @param row the row
     * @param column the column
     */
    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }


    public int getColumn() {
        return column;
    }

    /**
     * checks in the given Point is isAdjacent to this Point
     * @param p the Point object to check
     * @return whether it is isAdjacent or not
     */
    public boolean isAdjacent(Point p) {
        return (Math.abs(getColumn() - p.getColumn()) <= 1 && Math.abs(getRow() - p.getRow()) <= 1);
    }

    @Override
    public String toString() {
        return "Point{" + "row=" + row + ", column=" + column + '}';
    }
}
