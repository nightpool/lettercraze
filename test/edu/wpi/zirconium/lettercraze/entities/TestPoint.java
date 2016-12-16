/**
 * 
 */
package edu.wpi.zirconium.lettercraze.entities;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mt Landis
 *
 */
public class TestPoint {

	private Point point;
	/**
	 * @throws java.lang.Exception if error
	 */
	@Before
	public void setUp() throws Exception {
		point = new Point(1,2);
	}

	/**
	 * @throws java.lang.Exception if errror
	 */
	@After
	public void tearDown() throws Exception {
		point = null;
		assertNull(point);
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Point#Point(int, int)}.
	 */
	@Test
	public void testPoint() {
		assertEquals(1, point.getRow());
		assertEquals(2, point.getColumn());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Point#getRow()}.
	 */
	@Test
	public void testGetRow() {
		assertEquals(1, point.getRow());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Point#getColumn()}.
	 */
	@Test
	public void testGetColumn() {
		assertEquals(2, point.getColumn());
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Point#isAdjacent(edu.wpi.zirconium.lettercraze.entities.Point)}.
	 */
	@Test
	public void testIsAdjacentCardinal() {
		assertTrue(point.isAdjacent(new Point(2,2)));
		assertTrue(point.isAdjacent(new Point(1,3)));
	}
	
	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Point#isAdjacent(edu.wpi.zirconium.lettercraze.entities.Point)}.
	 */
	@Test
	public void testIsAdjacentDiagonal() {
		assertTrue(point.isAdjacent(new Point(2,3)));
		assertTrue(point.isAdjacent(new Point(0,3)));
	}
	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Point#isAdjacent(edu.wpi.zirconium.lettercraze.entities.Point)}.
	 */
	@Test
	public void testIsAdjacentOnlyRow() {
		assertFalse(point.isAdjacent(new Point(2,4)));
	}
	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Point#isAdjacent(edu.wpi.zirconium.lettercraze.entities.Point)}.
	 */
	@Test
	public void testIsAdjacentOnlyColumn() {
		assertFalse(point.isAdjacent(new Point(3,3)));
	}
	
	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.Point#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("Point{" + "row=" + 1 + ", column=" + 2 + '}',point.toString());
	}

}
