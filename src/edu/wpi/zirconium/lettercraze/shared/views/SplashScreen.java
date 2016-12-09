package edu.wpi.zirconium.lettercraze.shared.views;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SplashScreen extends Preloader {

    private Stage splashScreen;

    @Override
    public void start(Stage stage) throws Exception {
        splashScreen = stage;
        Parent root = FXMLLoader.load(getClass().getResource("Splash.fxml"));
        splashScreen.setScene(new Scene(root, 1024, 712));
        splashScreen.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        if (stateChangeNotification.getType() == StateChangeNotification.Type.BEFORE_START) {
            splashScreen.hide();
        }
    }
}
