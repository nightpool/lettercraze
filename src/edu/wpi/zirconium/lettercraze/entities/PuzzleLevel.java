package edu.wpi.zirconium.lettercraze.entities;

public class PuzzleLevel extends Level {

	int wordLimit = 0;

	public PuzzleLevel(int size, String key, int wordLimit) {
		super(size, key);
		this.wordLimit = wordLimit;
	}

	@Override
	public boolean isOver(Round r){
		
		if(r.getCompletedMoves().size() >= this.wordLimit){
			return true;
		}
		
		return false;
	}
	
	
	public static PuzzleLevel dummy() {
		PuzzleLevel puzzleOne = new PuzzleLevel(6, "PuzzleOne", 20);

		LevelShape poShape = new LevelShape(6);

		poShape.setTile(0, 0, true);
		poShape.setTile(0, 1, true);
		poShape.setTile(0, 2, true);
		poShape.setTile(0, 3, true);
		poShape.setTile(0, 4, true);
		poShape.setTile(0, 5, true);

		poShape.setTile(1, 0, true);
		poShape.setTile(1, 1, true);
		poShape.setTile(1, 2, true);
		poShape.setTile(1, 3, true);
		poShape.setTile(1, 4, true);
		poShape.setTile(1, 5, true);

		poShape.setTile(2, 0, true);
		poShape.setTile(2, 1, true);
		poShape.setTile(2, 2, false);
		poShape.setTile(2, 3, false);
		poShape.setTile(2, 4, true);
		poShape.setTile(2, 5, true);

		poShape.setTile(3, 0, true);
		poShape.setTile(3, 1, true);
		poShape.setTile(3, 2, false);
		poShape.setTile(3, 3, false);
		poShape.setTile(3, 4, true);
		poShape.setTile(3, 5, true);

		poShape.setTile(4, 0, true);
		poShape.setTile(4, 1, true);
		poShape.setTile(4, 2, true);
		poShape.setTile(4, 3, true);
		poShape.setTile(4, 4, true);
		poShape.setTile(4, 5, true);

		poShape.setTile(5, 0, true);
		poShape.setTile(5, 1, true);
		poShape.setTile(5, 2, true);
		poShape.setTile(5, 3, true);
		poShape.setTile(5, 4, true);
		poShape.setTile(5, 5, true);

		puzzleOne.setShape(poShape);

		
		//These may need to be lowered depending on how hard it
		// is to get points.
		puzzleOne.setThresholds(100, 150, 200);

		return puzzleOne;

	}
	
}
