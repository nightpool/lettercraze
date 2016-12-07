package edu.wpi.zirconium.lettercraze.entities;

public class Point {
	
	protected int row, column;
	
	/**
	 * creates Point object at the given row and column
	 * @param row the row
	 * @param column the column
	 */
	public Point(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	/**
	 * checks in the given Point is isAdjacent to this Point
	 * @param p the Point object to check
	 * @return whether it is isAdjacent or not
	 */
	public boolean isAdjacent(Point p) {
		return (Math.abs(column - p.column) == 1 || Math.abs(row - p.row) == 1);
	}
}
