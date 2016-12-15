package edu.wpi.zirconium.lettercraze.entities;

public class Point {

    protected final int row;
    protected final int column;

    /**
     * creates Point object at the given row and column.
     * @param row the row
     * @param column the column
     */
    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    /**
     * Gets the row from the Point.
     * @return the row from the Point
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column from the Point.
     * @return the column from the Point
     */
    public int getColumn() {
        return column;
    }

    /**
     * checks in the given Point is isAdjacent to this Point.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (row != point.row) return false;
        return column == point.column;

    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }
}
