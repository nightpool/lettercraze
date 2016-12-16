package edu.wpi.zirconium.lettercraze.entities;

/**
 * Represents a error that was encountered when loading a level file.
 * @author Joe / Zirconium
 */
public class LevelFileMalformationException extends RuntimeException {

	private static final long serialVersionUID = 384548761548521177L;

	/**
	 * Create a level loading error with a message.
	 * @param message the exception message (see {@link Exception#Exception(String)}
	 */
	public LevelFileMalformationException(String message) {
		super(message);
	}
	
}
