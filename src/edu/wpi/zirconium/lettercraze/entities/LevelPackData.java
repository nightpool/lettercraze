package edu.wpi.zirconium.lettercraze.entities;

import java.util.ArrayList;
import java.util.List;

public class LevelPackData {

    protected List<Level> levels;
    protected String saveFile;

    public LevelPackData(String saveFile){
        levels = new ArrayList<Level>();
    }

    public boolean saveLevels(){
        return false;

    }

    public boolean addLevel(Level level){
        return false;

    }

    public boolean moveLevel(Level level, int toIndex){
        return false;

    }

    public boolean removeLevel(Level level){
        return false;

    }
}

