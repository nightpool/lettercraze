package edu.wpi.zirconium.lettercraze.player;

import com.sun.javafx.application.LauncherImpl;
import edu.wpi.zirconium.lettercraze.entities.Level;
import edu.wpi.zirconium.lettercraze.entities.LevelPack;
import edu.wpi.zirconium.lettercraze.player.views.LevelScreen;
import edu.wpi.zirconium.lettercraze.player.views.LevelSelectScreen;
import edu.wpi.zirconium.lettercraze.shared.LetterCrazeApplication;
import edu.wpi.zirconium.lettercraze.shared.views.SplashScreen;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application class for the letter craze player.
 * <p>
 * @author Zirconium
 *
 */
public class LetterCrazePlayer extends LetterCrazeApplication {
    /** Holder for the Java FX stage.  */
    static private Stage stage;

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
        LevelSelectScreen screen = new LevelSelectScreen(LevelPack::get);
        stage.setScene(new Scene(screen, 1024, 712));
    }

    public static void showLevelScreen(Level level) {
        LevelScreen screen = new LevelScreen(level);
        screen.setExitHandler(LetterCrazePlayer::showLevelSelectScreen);
        stage.setScene(new Scene(screen, 1024, 712));
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(LetterCrazePlayer.class, SplashScreen.class, args);
    }

}
