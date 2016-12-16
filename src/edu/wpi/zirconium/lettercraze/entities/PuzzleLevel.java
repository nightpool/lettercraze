package edu.wpi.zirconium.lettercraze.entities;

public class PuzzleLevel extends Level {

    private int wordLimit = 0;
    
    /**
     * Creates a Puzzle Level with the given parameters. 
     * @param size Size of the LevelShape.
     */
    public PuzzleLevel(int size) {
        super(size);
    }

    /**
     * Gets the word limit for the Puzzle Level.
     * @return the word limit as an integer
     */
    public int getWordLimit() {
        return wordLimit;
    }

    /**
     * setWordLimit(int wl) is the setter method for Word Limit.
     * @param wordLimit the word Limit of the puzzle level
     */
    public void setWordLimit(int wordLimit) {
        this.wordLimit = wordLimit;
    }

    /**
     * isOver(Round r) Overrides the same method in Level.java.
     * @param r The Round for which the method checks the end conditions of.
     * @return True if the level-type rules show that the round is over, false otherwise.
     */
    @Override
    public boolean isOver(Round r){
		return r.getCompletedMoves().size() >= getWordLimit();
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public LevelStats statsFor(Round round) {
        return new PuzzleLevelStats(this, round.getScore());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LevelStats initialStats() {
        return new PuzzleLevelStats(this, 0);
    }

    @Override
    protected String boardString() {
        return super.boardString() + "\n" + getWordLimit();
    }

    /**
     * dummy() creates a sample Puzzle Level, complete with score thresholds and a moveLimit.
     * @return a static sample Puzzle Level.
     */
    public static PuzzleLevel dummy() {
        PuzzleLevel puzzleOne = new PuzzleLevel(6);

        LevelShape poShape = LevelShape.all(6);

        poShape.setTile(2, 2, false);
        poShape.setTile(2, 3, false);
        poShape.setTile(3, 2, false);
        poShape.setTile(3, 3, false);

        puzzleOne.setShape(poShape);

        //These may need to be lowered depending on how hard it
        // is to get points.
        puzzleOne.setThresholds(100, 150, 200);

        puzzleOne.setWordLimit(20);
        
        return puzzleOne;
    }
}
