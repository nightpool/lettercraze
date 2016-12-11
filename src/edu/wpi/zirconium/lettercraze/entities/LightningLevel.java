package edu.wpi.zirconium.lettercraze.entities;

public class LightningLevel extends Level{

    int maxTime = 0;

    public LightningLevel(int size, String key) {
        super(size, key);
    }
    
    public static LightningLevel dummy() {
		LightningLevel lightningOne = new LightningLevel(6, "PuzzleOne");

		LevelShape loShape = new LevelShape(6);

		loShape.setTile(0, 0, false);
		loShape.setTile(0, 1, false);
		loShape.setTile(0, 2, true);
		loShape.setTile(0, 3, true);
		loShape.setTile(0, 4, false);
		loShape.setTile(0, 5, false);

		loShape.setTile(1, 0, false);
		loShape.setTile(1, 1, true);
		loShape.setTile(1, 2, true);
		loShape.setTile(1, 3, true);
		loShape.setTile(1, 4, true);
		loShape.setTile(1, 5, false);

		loShape.setTile(2, 0, true);
		loShape.setTile(2, 1, true);
		loShape.setTile(2, 2, true);
		loShape.setTile(2, 3, true);
		loShape.setTile(2, 4, true);
		loShape.setTile(2, 5, true);

		loShape.setTile(3, 0, true);
		loShape.setTile(3, 1, true);
		loShape.setTile(3, 2, true);
		loShape.setTile(3, 3, true);
		loShape.setTile(3, 4, true);
		loShape.setTile(3, 5, true);

		loShape.setTile(4, 0, false);
		loShape.setTile(4, 1, true);
		loShape.setTile(4, 2, true);
		loShape.setTile(4, 3, true);
		loShape.setTile(4, 4, true);
		loShape.setTile(4, 5, false);

		loShape.setTile(5, 0, false);
		loShape.setTile(5, 1, false);
		loShape.setTile(5, 2, true);
		loShape.setTile(5, 3, true);
		loShape.setTile(5, 4, false);
		loShape.setTile(5, 5, false);

		lightningOne.setShape(loShape);

		
		//These probably need to be increased depending on how
		// easy it is to find words.
		lightningOne.setThresholds(5, 10, 15);

		
		//Assuming maxTime is in seconds
		lightningOne.maxTime = 120;
		
		return lightningOne;

	}
    
}
