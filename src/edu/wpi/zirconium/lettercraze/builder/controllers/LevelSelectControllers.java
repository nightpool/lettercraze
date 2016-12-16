package edu.wpi.zirconium.lettercraze.builder.controllers;

import edu.wpi.zirconium.lettercraze.builder.LetterCrazeBuilder;
import edu.wpi.zirconium.lettercraze.builder.views.BuilderScreen;
import edu.wpi.zirconium.lettercraze.builder.views.EditLevelTile;
import edu.wpi.zirconium.lettercraze.builder.views.LevelSelectScreen;
import edu.wpi.zirconium.lettercraze.entities.*;
import edu.wpi.zirconium.lettercraze.shared.views.LevelPackView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class LevelSelectControllers implements Initializable {

    @FXML private LevelSelectScreen root;

    @FXML private Pane backButton;

    @FXML private LevelPackView puzzlePack;
    @FXML private LevelPackView lightningPack;
    @FXML private LevelPackView themePack;

    @FXML private StackPane newPuzzle;
    @FXML private StackPane newLightning;
    @FXML private StackPane newTheme;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        backButton.setOnMouseClicked(this::onReturnToMenuClicked);

        puzzlePack.setOnNewEditTile(this::bindTile);
        lightningPack.setOnNewEditTile(this::bindTile);
        themePack.setOnNewEditTile(this::bindTile);

        puzzlePack.setPack(root.getLevelPackFactory().apply("puzzle_levels"));
        lightningPack.setPack(root.getLevelPackFactory().apply("lightning_levels"));
        themePack.setPack(root.getLevelPackFactory().apply("theme_levels"));

        newPuzzle.setOnMouseClicked(me -> {
            Level l = puzzlePack.getPack().newLevel(() -> new PuzzleLevel(6));
            show(BuilderScreen.scene(l));
        });
        newLightning.setOnMouseClicked(me -> {
            Level l = lightningPack.getPack().newLevel(() -> new LightningLevel(6));
            show(BuilderScreen.scene(l));
        });
        newTheme.setOnMouseClicked(me -> {
            Level l = themePack.getPack().newLevel(() -> new ThemeLevel(6));
            show(BuilderScreen.scene(l));
        });
    }

    private void show(Scene scene) {
        ((Stage) root.getScene().getWindow()).setScene(scene);
    }

    private void bindTile(EditLevelTile lt) {
        LevelPack pack = lt.getLevel().getPack().orElseThrow(
            () -> new IllegalStateException("Can't bind unsaved levels!"));

        lt.setOnTileClicked(me -> show(BuilderScreen.scene(lt.getLevel())));

        lt.getDownControl().setOnMouseClicked(me -> {
            pack.statsForLevel(lt.getLevel())
                .ifPresent(stats -> {
                    int i = pack.getLevelStats().indexOf(stats);
                    try {
                        Collections.swap(pack.getLevelStats(), i, i + 1);
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                });
            pack.saveStats();
        });

        lt.getUpControl().setOnMouseClicked(me -> {
            pack.statsForLevel(lt.getLevel())
                .ifPresent(stats -> {
                    int i = pack.getLevelStats().indexOf(stats);
                    try {
                        Collections.swap(pack.getLevelStats(), i, i - 1);
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                });
            pack.saveStats();
        });

        lt.getRemoveControl().setOnMouseClicked(me -> {
            pack.statsForLevel(lt.getLevel()).ifPresent(pack.getLevelStats()::remove);
            pack.saveStats();
        });
    }

    /**
     * Navigates to the menu screen.
     * @param mouseEvent
     */
    private void onReturnToMenuClicked(MouseEvent mouseEvent) {
        LetterCrazeBuilder.showMenuScreen();
    }
}