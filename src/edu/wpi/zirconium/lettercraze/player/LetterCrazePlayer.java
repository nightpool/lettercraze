package edu.wpi.zirconium.lettercraze.player;

import com.sun.javafx.application.LauncherImpl;
import edu.wpi.zirconium.lettercraze.entities.Level;
import edu.wpi.zirconium.lettercraze.entities.LevelPack;
import edu.wpi.zirconium.lettercraze.entities.LevelPackData;
import edu.wpi.zirconium.lettercraze.entities.Round;
import edu.wpi.zirconium.lettercraze.views.SplashScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Main application class for the letter craze player.
 * <p>
 * @author Zirconium
 *
 */
public class LetterCrazePlayer extends Application {
    /** Holder for the Java FX stage.  */
    static private Stage stage;

    // TODO - achievements
    // Achievement achievements...

    /** Array that holds the level packs, each one containing all the levels for a game. */
    protected ArrayList<LevelPack> levelPacks = new ArrayList<LevelPack>();

    /** The current round of the game. */
    Round currentRound;

    @Override
    public void init() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ignored) {}
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LetterCrazePlayer.stage = primaryStage;
        LetterCrazePlayer.stage.setTitle("LetterCraze");
        showMenuScreen();
        LetterCrazePlayer.stage.show();
    }

    public static void showMenuScreen() {
        try {
            Parent menu = FXMLLoader.load(LetterCrazePlayer.class.getResource("views/Menu.fxml"));
            stage.setScene(new Scene(menu, 1024, 712));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't load FXML : Menu");
        }
    }

    public static void showLevelSelectScreen() {
        try {
            Parent builder = FXMLLoader.load(LetterCrazePlayer.class.getResource("views/LevelSelect.fxml"));
            stage.setScene(new Scene(builder, 1024, 712));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't load FXML : LevelSelect");
        }
    }

    public static void showPlayerLevelScreen() {
        try {
            Parent player = FXMLLoader.load(LetterCrazePlayer.class.getResource("views/Level.fxml"));
            stage.setScene(new Scene(player, 1024, 712));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't load FXML : Level");
        }
    }

// TODO achievement data stuff

    /**
     * Loads the LevelPack
     * @param file the File to load the level pack from.
     */
    public LevelPackData loadData(String file){
        return new LevelPackData("bla");
//        levelPacks.add(LevelPackData(file));
//        return levelPacks.get(0);
    }

    /**
     * Starts the round from the given level.
     * @param level to start round with
     * @return true if no errors occurred
     */
    public boolean startRound(Level level){
        currentRound = new Round(level);
        // TODO - is this where the check happens if a locked level can be played?
        return true;
    }

    /**
     * Updates the stats for the current round's progress.
     */
    public void updateStatsFromCurrentRound() {
        // TODO no idea.
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(LetterCrazePlayer.class, SplashScreen.class, args);
    }

}
