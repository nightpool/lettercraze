package edu.wpi.zirconium.lettercraze.player.controllers;

import edu.wpi.zirconium.lettercraze.player.LetterCrazePlayer;
import edu.wpi.zirconium.lettercraze.shared.BoardView;
import edu.wpi.zirconium.lettercraze.shared.BoardView.TileView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class LevelControllers implements Initializable {

    @FXML private Button exitLevel;
    @FXML private BoardView board;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exitLevel.setOnMouseClicked(this::onExitClicked);

        createDummyTiles();
        board.getTiles().forEach(t -> t.setOnMouseClicked(new ToggleTileStateController(t)));
    }

    private void createDummyTiles() {

        int tiles = board.getBoardHeight() * board.getBoardWidth();
        Random r = new Random();

        IntStream.range(0, tiles)
            .mapToObj(i -> board.newTile(i%board.getBoardWidth(), i/board.getBoardWidth()))
            .forEach((e) -> {
                e.valueProperty().set(String.valueOf((char)(r.nextInt(26) + 'a')));
                board.getTiles().add(e);
            });
    }

    private void onExitClicked(MouseEvent mouseEvent) {
        try {
            LetterCrazePlayer.showLevelSelectScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class ToggleTileStateController implements EventHandler<MouseEvent> {
        private final TileView t;
        int state;

        public ToggleTileStateController(TileView t) {
            this.t = t;
            state = 0;
        }

        @Override
        public void handle(MouseEvent event) {
            switch (state){
                case 0: t.setBlocked(true); t.setSelected(false); break;
                case 1: t.setBlocked(false); t.setSelected(true); break;
                case 2: t.setBlocked(false); t.setSelected(false); break;
            }
            state = (state + 1) % 3;
        }
    }
}