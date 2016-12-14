package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;
import java.util.List;


public class LevelPack {
    protected ArrayList<LevelStats> levelStats = new ArrayList<LevelStats>();

    protected LevelPackData data;

    public LevelPack(String saveFile){

    }

    public void saveStats(){

    }

    public boolean isUnlocked(Level level){
        return false;

    }

    public List<LevelStats> unlockedLevels(){
        return null;

    }

    public LevelStats lastUnlockedLevel(){
        return null;

    }
}
