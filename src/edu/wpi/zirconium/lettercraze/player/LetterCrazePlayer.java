package edu.wpi.zirconium.lettercraze.player;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LetterCrazePlayer extends Application {

	
	static private Stage stage;
	
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
	
	public static void showLevelSelectScreen() throws Exception{
		
		Parent builder = FXMLLoader.load(LetterCrazePlayer.class.getResource("views/PlayerLevelSelect.fxml"));
        stage.setScene(new Scene(builder, 1024, 712));
	}
	
	public static void showPlayerLevelScreen() throws Exception{
		
		Parent builder = FXMLLoader.load(LetterCrazePlayer.class.getResource("views/Level.fxml"));
        stage.setScene(new Scene(builder, 1024, 712));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
