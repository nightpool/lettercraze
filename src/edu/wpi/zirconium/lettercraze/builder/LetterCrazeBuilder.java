package edu.wpi.zirconium.lettercraze.builder;

import com.sun.javafx.application.LauncherImpl;
import edu.wpi.zirconium.lettercraze.entities.LevelPackData;
import edu.wpi.zirconium.lettercraze.views.SplashScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the main application class for the letter craze builder.
 * <p>
 *
 * @author Zirconium
 *
 */
public class LetterCrazeBuilder extends Application {

    /** The holder for the stage. TODO  */
    static private Stage stage;

    /** Array that holds the level packs, each one containing all the levels for a game. */
    protected List<LevelPackData> levelPacks = new ArrayList<LevelPackData>();

    /**
     *     Loads the level pack at the path specified.
     * @param file full path to file of the desired pack to load.
     * @return true if pack was successfully loaded
     */
    public boolean loadPack(String file){
        // TODO
        return false;
    }

    /**
     * Saves the entire pack of level data to disk.
     * @param file full path to file to save the current level pack data to.
     * @return true if operation was successful
     */
    public boolean savePack(String file){
        // TODO
        return false;
    }

    @Override
    public void init() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ignored) {}
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LetterCrazeBuilder.stage = primaryStage;
        LetterCrazeBuilder.stage.setTitle("LetterCraze Builder");
        showMenuScreen();
        LetterCrazeBuilder.stage.show();
    }

    @Override
    public void stop() throws Exception {
        LetterCrazeBuilder.stage = null;
    }

    public static void showMenuScreen() {
        try {
            Parent menu = FXMLLoader.load(LetterCrazeBuilder.class.getResource("views/Menu.fxml"));
            stage.setScene(new Scene(menu, 1024, 712));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't load FXML : Menu");
        }

    }

    public static void showBuilderScreen() {
        try {
            Parent builder = FXMLLoader.load(LetterCrazeBuilder.class.getResource("views/Builder.fxml"));
            stage.setScene(new Scene(builder, 1024, 712));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't load FXML : Builder");
        }
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(LetterCrazeBuilder.class, SplashScreen.class, args);
    }
}
