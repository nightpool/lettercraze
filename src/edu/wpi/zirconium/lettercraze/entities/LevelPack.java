package edu.wpi.zirconium.lettercraze.entities;


import edu.wpi.zirconium.lettercraze.shared.LetterCrazeApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class LevelPack {
    protected ObservableList<LevelStats> levelStats = FXCollections.observableArrayList();

    private final String key;

    /**
     * Creates the LevelPack object with the given key.
     * @param key the key of the LevelPack
     */
    protected LevelPack(String key) {
        this.key = key;
    }

    /**
     * Returns true if the level is unlocked.
     * @param level the LevelStats object to check
     * @return true if the level is unlocked
     */
    public boolean isUnlocked(LevelStats level){
        int index = levelStats.indexOf(level) - 1;
        return index < 0 || levelStats.get(index).numAchievedStars() > 0;
    }

    /**
     * Returns the LevelStats of the Level.
     * @param level the Level to get the LevelStats for
     * @return the Optional LevelStats of the Level
     */
    public Optional<LevelStats> statsForLevel(Level level) {
        return levelStats.stream().filter(ls -> ls.getLevel().equals(level)).findFirst();
    }
        
    /**
     * Gets the Levels from the LevelPack.
     * @return the Stream of Levels in the LevelPack
     */
    public Stream<Level> getLevels() {
        return levelStats.stream().map(LevelStats::getLevel);
    }

    /**
     * Gets the LevelStats from the LevelPack.
     * @return the List of LevelStats in the LevelPack
     */
    public ObservableList<LevelStats> getLevelStats() {
        return levelStats;
    }

    /**
     * The unique name of the level pack.
     * This is the name of the folder representing the level pack in the filesystem (if any)
     */
    public String getKey() {
        return key;
    }

    private String title;
    /**
     * Generate a human-readable title for the LevelPack.
     */
    public String getTitle() {
        if(title == null) {
            StringBuilder title = new StringBuilder();

            boolean capNext = true;
            for (char c : getKey().toCharArray()) {
                if (c == '_') {
                    capNext = true;
                    c = ' ';
                } else if (capNext) {
                    capNext = false;
                    c = Character.toTitleCase(c);
                }
                title.append(c);
            }

            this.title = title.toString();
        }
        return title;
    }

    /**
     * Consumes a LevelStats and, if it's a higher score then the current stats for that level,
     * replaces those stats in the collection.
     * If the given LevelStats isn't in the current pack, then an {@link IllegalStateException} is thrown
     *
     * LevelStats are compared using {@link LevelStats#thresholdValue()} method
     * @param levelStats the level stats to replace the current one with
     * @return true iff the collection was modified
     */
    public boolean replaceIfBetter(LevelStats levelStats) {
        LevelStats current = statsForLevel(levelStats.getLevel())
            .orElseThrow(() -> new IllegalStateException("Level stats for "+levelStats.getLevel()+" not found"));
        if (levelStats.thresholdValue() > current.thresholdValue()) {
            this.levelStats.set(this.levelStats.indexOf(current), levelStats);
            return true;
        }
        return false;
    }

    /**
     * Load the given LevelPack from the filesystem.
     * @param key the name of the levelpack to load
     * @return the LevelPack loaded
     */
    public static LevelPack get(String key) {
        Path folder = LetterCrazeApplication.dataFolder().resolve(key);
        try {
            LevelPack lp = new LevelPack(key);
            lp.levelStats = Files.lines(folder.resolve("pack.conf"))
                .map(line -> LevelStats.fromString(line, k -> {
                    Level l = Level.fromPath(folder.resolve(k));
                    l.setPack(lp);
                    return l;
                }))
                .filter(s -> s != null)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
            return lp;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't read LevelPack : "+key);
        }
    }

    /**
     * Returns the Stream of Strings in the file format.
     * @return the Stream of Strings for the file
     */
    public Stream<String> toFile() {
        return getLevelStats().stream()
            .map(LevelStats::saveString);
    }

    /**
     * Saves the stats of the LevelPack.
     */
    public void saveStats() {
        try {
            Files.write(getFolder().resolve("pack.conf"), (Iterable<String>)toFile()::iterator);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Path getFolder() {
        return LetterCrazeApplication.dataFolder().resolve(key);
    }

    /**
     * Create a new Level and add it to this collection.
     *
     * @param levelFactory a factory for the level to be created
     * @return the newly created level, whose level stats are new part of this pack.
     */
    public Level newLevel(Supplier<? extends Level> levelFactory) {
        Level l = levelFactory.get();
        l.setPack(this);
        Random random = new Random();
        l.setPath(getFolder().resolve("level_"+Integer.toHexString(random.nextInt())+".txt"));
        getLevelStats().add(l.initialStats());
        this.saveStats();
        return l;
    }
}
