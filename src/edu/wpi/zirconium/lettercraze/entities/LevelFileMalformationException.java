/**
 * 
 */
package edu.wpi.zirconium.lettercraze.entities;

/**
 * @author Joe / Zirconium
 *
 */
public class LevelFileMalformationException extends RuntimeException {

	private static final long serialVersionUID = 384548761548521177L;

	//Constructor that accepts a message
	public LevelFileMalformationException(String message) {
		super(message);
	}
	
}
