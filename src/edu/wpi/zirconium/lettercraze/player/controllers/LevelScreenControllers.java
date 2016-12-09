package edu.wpi.zirconium.lettercraze.player.controllers;

import edu.wpi.zirconium.lettercraze.entities.Level;
import edu.wpi.zirconium.lettercraze.entities.Round;
import edu.wpi.zirconium.lettercraze.entities.Tile;
import edu.wpi.zirconium.lettercraze.entities.bindings.WordStringBinding;
import edu.wpi.zirconium.lettercraze.player.LetterCrazePlayer;
import edu.wpi.zirconium.lettercraze.player.views.LevelScreen;
import edu.wpi.zirconium.lettercraze.shared.BoardView;
import edu.wpi.zirconium.lettercraze.shared.TileView;
import edu.wpi.zirconium.lettercraze.utils.ContainsBinding;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class LevelScreenControllers implements Initializable {

    @FXML private LevelScreen root;
    @FXML private Button exitLevel;
    @FXML private BoardView board;
    @FXML private Text title;

    @FXML private Text wordPreview;
    @FXML private Rectangle wordPreviewBox;

    private Round currentRound;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exitLevel.setOnMouseClicked(this::onExitClicked);

        Level level = Level.get(root.getLevelKey());
        currentRound = new Round(level);

        title.textProperty().bind(level.titleProperty());
        // TODO score
        currentRound.getBoard().getTiles().forEach(t -> {
            TileView v = board.newTile(t.getPos());
            this.bindTile(v, t);
            board.getTiles().add(v);
        });

        wordPreviewBox.widthProperty().bind(board.widthProperty());
        currentRound.moveInProgressProperty().addListener((_m, _o, newMove) -> {
            newMove.wordBinding().addListener((wordBinding, __o, _n) -> {
                wordPreview.textProperty().bind(new WordStringBinding(wordBinding));
            });
        });
    }

    private void bindTile(TileView v, Tile t) {
        v.valueProperty().set(t.getLetter().getCharacter());
        currentRound.moveInProgressProperty().addListener((_m, _o, newMove) ->
            v.selectedProperty().bind(new ContainsBinding<Tile>(t, newMove.getSelectedTiles())));
        t.positionProperty().addListener((_p, oldP, newP) -> {
            v.setRow(newP.getRow());
            v.setColumn(newP.getColumn());
        });
        v.setOnMouseClicked(new SelectTileController(currentRound, t, v));
    }

    private void onExitClicked(MouseEvent mouseEvent) {
        LetterCrazePlayer.showLevelSelectScreen();
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