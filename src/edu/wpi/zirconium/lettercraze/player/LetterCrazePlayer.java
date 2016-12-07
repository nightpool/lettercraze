package edu.wpi.zirconium.lettercraze.player;

import java.util.ArrayList;
import com.sun.javafx.application.LauncherImpl;
import edu.wpi.zirconium.lettercraze.entities.LevelPackData;
import edu.wpi.zirconium.lettercraze.views.SplashScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    
    /**
     * Default constructor.
     */
    LetterCrazePlayer() {
    	
    }
    
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

    public static void showMenuScreen() throws Exception {
        Parent menu = FXMLLoader.load(LetterCrazePlayer.class.getResource("views/Menu.fxml"));
        stage.setScene(new Scene(menu, 1024, 712));
    }

    public static void showLevelSelectScreen() throws Exception {
        Parent builder = FXMLLoader.load(LetterCrazePlayer.class.getResource("views/LevelSelect.fxml"));
        stage.setScene(new Scene(builder, 1024, 712));
    }

    public static void showPlayerLevelScreen() throws Exception {
        Parent builder = FXMLLoader.load(LetterCrazePlayer.class.getResource("views/Level.fxml"));
        stage.setScene(new Scene(builder, 1024, 712));
    }
    
// TODO achievement data stuff
    
    /**
     * Loads the LavelPack
     * @param file
     * @return
     */
    public LevelPackData loadData(String file){
    	// TODO 
    	return new LevelPackData;
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
