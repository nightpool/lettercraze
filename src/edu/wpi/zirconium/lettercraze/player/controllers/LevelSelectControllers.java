package edu.wpi.zirconium.lettercraze.player.controllers;

import edu.wpi.zirconium.lettercraze.entities.LevelPack;
import edu.wpi.zirconium.lettercraze.player.LetterCrazePlayer;
import edu.wpi.zirconium.lettercraze.player.views.LevelPackView;
import edu.wpi.zirconium.lettercraze.player.views.LevelSelectScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class LevelSelectControllers implements Initializable {

    @FXML private LevelSelectScreen root;
    @FXML private Pane backButton;

    @FXML private LevelPackView puzzlePack;
    @FXML private LevelPackView lightningPack;
    @FXML private LevelPackView themePack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        backButton.setOnMouseClicked(this::onReturnToMenuClicked);

        this.puzzlePack.setPack(LevelPack.dummyPuzzle());
        this.lightningPack.setPack(LevelPack.dummyLightning());
        this.themePack.setPack(LevelPack.dummyTheme());
        
        // for each of the packs, check if the levels are unlocked and only allow clicks
        // on the ones that are
        puzzlePack.getTiles().forEach(lt -> {
        	String key = lt.getLevel().getKey();
        	if(this.puzzlePack.getPack().isUnlocked(lt.getLevelStats()))
        		lt.setOnMouseClicked(me -> LetterCrazePlayer.showLevelScreen(key));
        }
        		);

        lightningPack.getTiles().forEach(lt -> {
        	String key = lt.getLevel().getKey();
        	if(this.lightningPack.getPack().isUnlocked(lt.getLevelStats()))
        		lt.setOnMouseClicked(me -> LetterCrazePlayer.showLevelScreen(key));
        }
        		);

        themePack.getTiles().forEach(lt -> {
        	String key = lt.getLevel().getKey();
        	if(this.themePack.getPack().isUnlocked(lt.getLevelStats()))
        		lt.setOnMouseClicked(me -> LetterCrazePlayer.showLevelScreen(key));
        }
        		);

    }

    private void onReturnToMenuClicked(MouseEvent mouseEvent) {
        LetterCrazePlayer.showMenuScreen();
    }
}
