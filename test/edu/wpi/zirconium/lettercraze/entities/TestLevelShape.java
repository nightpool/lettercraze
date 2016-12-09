/**
 * 
 */
package edu.wpi.zirconium.lettercraze.entities;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Joe Crimi/Zirconium
 *
 */
public class TestLevelShape {

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.LevelShape#LevelShape(int, boolean[][])}.
	 */
	@Test
	public void testLevelShapeIntBooleanArrayArray() {
		
		boolean[][] testDoubleArray = new boolean[6][6];
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 6; j++){
				if(i == j){
					testDoubleArray[i][j] = false;
				} else {
					testDoubleArray[i][j] = true;
				}
			}
		}
		LevelShape testShapeIBAA = new LevelShape(36, testDoubleArray);
		
		assertTrue(testShapeIBAA.size == 36);
		assertTrue(testShapeIBAA.shape == testDoubleArray);
		assertFalse(testShapeIBAA.shape[5][5] == true);
		assertTrue(testShapeIBAA.shape[3][1] == true);
		
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.LevelShape#LevelShape(int)}.
	 */
	@Test
	public void testLevelShapeInt() {
		boolean[][] testBool = new boolean[6][6];
		
		LevelShape testShapeI = new LevelShape(6);
		
		
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 6; j++){
				if(i == j){
					testBool[i][j] = false;
					testShapeI.shape[i][j] = false;
				} else {
					testShapeI.shape[i][j] = true;
					testBool[i][j] = true;
				}
			}
		}
		
		assertEquals(testShapeI.shape, testBool);
		
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.LevelShape#isTile(int, int)}.
	 */
	@Test
	public void testIsTile() {
		LevelShape testShapeIsTile = new LevelShape(6);
		
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 6; j++){
				if(i == j){
					testShapeIsTile.shape[i][j] = false;
				} else {
					testShapeIsTile.shape[i][j] = true;
				}
			}
		}
		
		assertFalse(testShapeIsTile.isTile(5,5));
		assertTrue(testShapeIsTile.isTile(4, 5));
	}

	/**
	 * Test method for {@link edu.wpi.zirconium.lettercraze.entities.LevelShape#setTile(int, int, boolean)}.
	 */
	@Test
	public void testSetTile() {
		LevelShape testShapeSetTile = new LevelShape(6);
		
		testShapeSetTile.setTile(0, 3, true);
		testShapeSetTile.setTile(0, 4, false);
		
		assertFalse(testShapeSetTile.shape[0][4]);
		assertTrue(testShapeSetTile.shape[0][3]);
	}

}
