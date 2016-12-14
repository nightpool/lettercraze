package edu.wpi.zirconium.lettercraze.entities;

public class LightningLevel extends Level{

    int maxTime = 0;

    public LightningLevel(int size, String key) {
        super(size, key);
    }
    
    
    @Override
    public boolean isOver(Round r){
        return r.getTime() >= this.maxTime;
    }
    
    
    public static LightningLevel dummy() {
        LightningLevel lightningOne = new LightningLevel(6, "PuzzleOne");

        LevelShape loShape = LevelShape.all(6);

        loShape.setTile(0, 0, false);
        loShape.setTile(0, 1, false);
        loShape.setTile(0, 4, false);
        loShape.setTile(0, 5, false);
        loShape.setTile(1, 0, false);
        loShape.setTile(1, 5, false);
        loShape.setTile(4, 0, false);
        loShape.setTile(4, 5, false);
        loShape.setTile(5, 0, false);
        loShape.setTile(5, 1, false);
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
