package edu.wpi.zirconium.lettercraze;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import edu.wpi.zirconium.lettercraze.views.SplashScreen;

public class PlayerMain extends Application {

    @Override
    public void init() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/Menu.fxml"));
        primaryStage.setTitle("Lettercraze");
        primaryStage.setScene(new Scene(root, 1024, 712));
        primaryStage.show();
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(PlayerMain.class, SplashScreen.class, args);
    }
}
