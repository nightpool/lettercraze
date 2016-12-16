/**
 * 
 */
package edu.wpi.zirconium.lettercraze.entities;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * @author Joe Crimi/Zirconium
 *
 */
public class TestLevelShape {
	
	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.LevelShape#getSize()}.
	 */
	@Test
	public void testGetSize() {
		LevelShape testShapeGetSize = new LevelShape(6);
		
		int size = testShapeGetSize.getSize();
		
		assertEquals(size, 6);
		
		LevelShape testShapeGetSize5 = new LevelShape(5);
		int size5 = testShapeGetSize5.getSize();
		
		assertEquals(size5, 5);
    }
    

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.LevelShape#isTile(Point)}.
	 */
	@Test
	public void testIsTile() {
		LevelShape testShapeIsTile = new LevelShape(6);
		
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 6; j++){
				if(i == j){
					testShapeIsTile.setTile(i, j, false);
				} else {
					testShapeIsTile.setTile(i, j, true);
				}
			}
		}
		
		Point test1 = new Point(0,0);
		Point test2 = new Point(5,4);
		Point test3 = new Point(3,3);
		
		assertFalse(testShapeIsTile.isTile(test1));
		assertTrue(testShapeIsTile.isTile(test2));
		assertFalse(testShapeIsTile.isTile(test3));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.LevelShape#setTile(int, int, boolean)}.
	 */
	@Test
	public void testSetTile() {
		LevelShape testShapeSetTile = new LevelShape(6);
		
		testShapeSetTile.setTile(0, 3, true);
		testShapeSetTile.setTile(0, 4, false);
		
	}

}
