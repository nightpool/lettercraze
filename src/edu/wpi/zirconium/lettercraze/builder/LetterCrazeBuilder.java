package edu.wpi.zirconium.lettercraze.builder;

import com.sun.javafx.application.LauncherImpl;
import edu.wpi.zirconium.lettercraze.shared.LetterCrazeApplication;
import edu.wpi.zirconium.lettercraze.shared.views.SplashScreen;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is the main application class for the letter craze builder.
 * <p>
 *
 * @author Zirconium
 *
 */
public class LetterCrazeBuilder extends LetterCrazeApplication {

    /** The holder for the stage. TODO  */
    static private Stage stage;

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
    
    public static void showSelectScreen() {
        try {
            Parent selector = FXMLLoader.load(LetterCrazeBuilder.class.getResource("views/LevelSelect.fxml"));
            stage.setScene(new Scene(selector, 1024, 712));
        } catch (IOException e) {
            e.printStackTrace();
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
