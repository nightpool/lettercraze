package edu.wpi.zirconium.lettercraze.player.controllers;

import edu.wpi.zirconium.lettercraze.entities.*;
import edu.wpi.zirconium.lettercraze.player.LetterCrazePlayer;
import edu.wpi.zirconium.lettercraze.player.views.LevelScreen;
import edu.wpi.zirconium.lettercraze.shared.views.BoardView;
import edu.wpi.zirconium.lettercraze.shared.views.TileView;
import edu.wpi.zirconium.lettercraze.utils.ContainsBinding;
import edu.wpi.zirconium.utils.TimeFormatter;
import javafx.beans.InvalidationListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class LevelScreenControllers implements Initializable {

    @FXML private LevelScreen root;
    @FXML private BoardView board;
    @FXML private Text title;

    @FXML private Text wordPreview;
    @FXML private Rectangle wordPreviewBox;

    @FXML private Text time;
    @FXML private Text score;
    @FXML private Text wordsFound;
    @FXML private Text wordLabel;

    @FXML private TextArea previousMovesDisplay;

    @FXML private Button exitLevel;
    @FXML private Button submit;

    private Round currentRound;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exitLevel.setOnMouseClicked(this::onExitClicked);

        Level level = Level.get(root.getLevelKey());
        currentRound = new Round(level);

        title.textProperty().bind(level.titleProperty());
        score.textProperty().bind(currentRound.scoreBinding().asString());

        time.textProperty().bind(new TimeFormatter(currentRound.timeProperty()));
        time.setOnMouseClicked(_me -> currentRound.incrementTime());
        currentRound.timeProperty().greaterThan(60).addListener(
            (_p, _o, value) -> wordLabel.setText(value ? "minutes" : "seconds"));

        // TODO: something different for theme levels? Words remaining?
        wordsFound.textProperty().bind(currentRound.getWordsFound().sizeProperty().asString());

        currentRound.getCompletedMoves().addListener((InvalidationListener) o -> {
            String moves = currentRound.getCompletedMoves().stream()
                .map(Move::getWord).map(Word::asString).collect(Collectors.joining("\n"));
            previousMovesDisplay.setText(moves);
        });

        currentRound.getBoard().getTiles().forEach(t -> {
            TileView v = board.newTile(t.getPos());
            this.bindTile(v, t);
            board.getTiles().add(v);
        });

        wordPreviewBox.widthProperty().bind(board.widthProperty());
        currentRound.moveInProgressProperty().addListener((_m, _o, newMove) -> {
            newMove.wordBinding().addListener((_p, __o, newWord) -> {
                wordPreview.textProperty().set(newWord.asString());
            });
        });
        currentRound.reset();
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
}
