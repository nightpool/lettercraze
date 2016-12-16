package edu.wpi.zirconium.lettercraze.entities;

import java.util.Optional;

public class LightningLevel extends Level{

    /** the time limit of the level */
    private int maxTime = 0;

    /**
     * LightningLevel(int size, String key) creates a LightningLevel with given parameters.
     * @param size The Length and Width of the level.
     *
     */
    public LightningLevel(int size) {
        super(size);
    }

    /**
     * Gets the maximum time allowed of the Level.
     * @return the maximum time
     */
    public int getMaxTime() {
        return maxTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Integer> getTimeLimit() {
        return Optional.of(getMaxTime());
    }

    /**
     * Sets the maximum time for the Level.
     * @param maxTime the maximum time
     */
    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    /**
     * isOver(Round r) overrides method in Level.java.
     * @param r the current Round.
     * @return whether or not the round is over given the current status of the game.
     */
    @Override
    public boolean isOver(Round r){
        return r.getTime() >= getMaxTime();
    }

    @Override
    public int thresholdValue(Round round) {
        return round.getNumWordsFound();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LevelStats statsFor(Round round) {
        return new LightningLevelStats(this, round.getNumWordsFound());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LevelStats initialStats() {
        return new LightningLevelStats(this, 0);
    }

    /**
     * setTimeLimit(int tl) is the setter method for maxTime.
     * @param tl the Time Limit of the lightning level in seconds
     */
    public void setTimeLimit(int tl){
    	this.maxTime = tl;
    }

    @Override
    protected String boardString() {
        return super.boardString() + "\n" + getMaxTime();
    }

    /**
     * dummy() creates a sample Lightning Level complete with a level shape, maxTime, and score thresholds.
     * @return a static sample Lightning Level.
     */
    public static LightningLevel dummy() {
        LightningLevel lightningOne = new LightningLevel(6);

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
