package edu.wpi.zirconium.lettercraze.entities;

public class PuzzleLevel extends Level {

    int wordLimit = 0;

    public PuzzleLevel(int size, String key, int wordLimit) {
        super(size, key);
        this.wordLimit = wordLimit;
    }

    @Override
    public LevelType getType() {
        return LevelType.PUZZLE;
    }

    @Override
    public boolean isOver(Round r){
		return r.getCompletedMoves().size() >= this.wordLimit;
	}

    public static PuzzleLevel dummy() {
        PuzzleLevel puzzleOne = new PuzzleLevel(6, "PuzzleOne", 20);

        LevelShape poShape = LevelShape.all(6);

        poShape.setTile(2, 2, false);
        poShape.setTile(2, 3, false);
        poShape.setTile(3, 2, false);
        poShape.setTile(3, 3, false);

        puzzleOne.setShape(poShape);

        //These may need to be lowered depending on how hard it
        // is to get points.
        puzzleOne.setThresholds(100, 150, 200);

        return puzzleOne;
    }
}
