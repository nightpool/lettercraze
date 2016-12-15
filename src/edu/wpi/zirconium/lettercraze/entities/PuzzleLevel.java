package edu.wpi.zirconium.lettercraze.entities;

public class PuzzleLevel extends Level {

    private int wordLimit = 0;

    
    /**
     * Creates a Puzzle Level with the given parameters. 
     * @param size Size of the LevelShape.
     * @param key a unique string to associate with the level.
     */
    public PuzzleLevel(int size, String key) {
        super(size, key);
    }

    
    /**
     * isOver(Round r) Overrides the same method in Level.java.
     * @param r The Round for which the method checks the end conditions of.
     * @return True if the level-type rules show that the round is over, false otherwise.
     */
    @Override
    public boolean isOver(Round r){
		return r.getCompletedMoves().size() >= this.wordLimit;
	}


    /**
     * {@inheritDoc}
     */
    @Override
    public LevelStats statsFor(Round round) {
        return new PuzzleLevelStats(this, round.getNumWordsFound());
    }
    
    /**
     * dummy() creates a sample Puzzle Level, complete with score thresholds and a moveLimit
     * @return a static sample Puzzle Level.
     */
    public static PuzzleLevel dummy() {
        PuzzleLevel puzzleOne = new PuzzleLevel(6, "PuzzleOne");

        LevelShape poShape = LevelShape.all(6);

        poShape.setTile(2, 2, false);
        poShape.setTile(2, 3, false);
        poShape.setTile(3, 2, false);
        poShape.setTile(3, 3, false);

        puzzleOne.setShape(poShape);

        //These may need to be lowered depending on how hard it
        // is to get points.
        puzzleOne.setThresholds(100, 150, 200);

        puzzleOne.wordLimit = 20;
        
        return puzzleOne;
    }
}
