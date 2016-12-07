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
	
	public boolean adjacent(Point p) {
		return (abs(column - p.column) == 1 || abs(row - p.row) == 1);	//TODO abs no worky?
	}
	
	
}
