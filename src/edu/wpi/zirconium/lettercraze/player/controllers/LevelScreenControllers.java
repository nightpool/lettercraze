package edu.wpi.zirconium.lettercraze.player.controllers;

import edu.wpi.zirconium.lettercraze.entities.*;
import edu.wpi.zirconium.lettercraze.player.views.LevelScreen;
import edu.wpi.zirconium.lettercraze.player.views.StarsView;
import edu.wpi.zirconium.lettercraze.player.views.SubmitButton;
import edu.wpi.zirconium.lettercraze.shared.views.BoardView;
import edu.wpi.zirconium.lettercraze.shared.views.TileView;
import edu.wpi.zirconium.utils.TimeFormatter;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableNumberValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.fxmisc.easybind.EasyBind;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class LevelScreenControllers implements Initializable {

    @FXML private LevelScreen root;
    @FXML private BoardView board;
    @FXML private Text title;

    @FXML private Text wordPreview;
    @FXML private Rectangle wordPreviewBox;

    @FXML private StarsView stars;

    @FXML private Text time;
    @FXML private Text timeLabel;
    @FXML private Text score;
    @FXML private Text wordsFound;
    @FXML private Text wordLabel;

    @FXML private TextArea previousMovesDisplay;

    @FXML private Button exitLevel;
    @FXML private Button reset;
    @FXML private Button undo;
    @FXML private SubmitButton submit;

    private Round currentRound;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exitLevel.setOnMouseClicked(me -> root.onExit());
        reset.setOnMouseClicked(me -> currentRound.reset());
        undo.setOnMouseClicked(me -> currentRound.undoMove());

        Level level = root.getLevel();
        currentRound = new Round(level);

        title.textProperty().bind(level.titleProperty());
        score.textProperty().bind(currentRound.scoreBinding().asString());

        stars.starsActiveProperty().bind(currentRound.starsEarnedBinding());

        time.textProperty().bind(TimeFormatter.forValue(
            level.getTimeLimit()
                .<ObservableNumberValue>map(l -> Bindings.subtract(l, currentRound.timeProperty()))
                .orElse(currentRound.timeProperty())));

        Timer timeUpdater = new Timer(true);
        timeUpdater.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(currentRound::incrementTime);
            }
        }, 0, 1000);

        currentRound.setIsOverCallback(v -> {
            submit.setVisible(!v);
        });

        // TODO: something different for theme levels? Words remaining?
        wordsFound.textProperty().bind(currentRound.getWordsFound().sizeProperty().asString());

        currentRound.getCompletedMoves().addListener((InvalidationListener) o -> {
            String moves = currentRound.getCompletedMoves().stream()
                .map(Move::asString).collect(Collectors.joining("\n"));
            previousMovesDisplay.setText(moves);
        });

        board.setBoardHeight(level.getShape().getSize());
        board.setBoardWidth(level.getShape().getSize());
        currentRound.getBoard().observableTiles().addListener(
            (ListChangeListener<? super Tile>) l -> {
                while (l.next()) {
                    for (Tile t : l.getAddedSubList()) {
                        TileView v = board.newTile(t.getPos());
                        this.bindTile(v, t);
                        board.getTiles().add(v);
                    }

                    for (Tile t : l.getRemoved()) {
                        board.getTiles()
                             .removeIf(v -> v.getPos().equals(t.getPos()));
                    }
                }
            }
        );

        wordPreviewBox.widthProperty().bind(board.widthProperty());
        wordPreview.textProperty().bind(EasyBind.map(
            EasyBind.select(currentRound.moveInProgressProperty()).selectObject(Move::wordBinding),
            Word::asString));

        submit.setOnAction(me -> currentRound.submitMove());

        submit.validProperty().bind(
            EasyBind.monadic(currentRound.moveInProgressProperty())
                .flatMap(move -> EasyBind.map(move.wordBinding(), _w -> move.isMoveValid(currentRound))));
        submit.scoreProperty().bind(
            EasyBind.monadic(currentRound.moveInProgressProperty())
                .flatMap(Move::scoreBinding));

        currentRound.reset();

        Platform.runLater(() -> {
            root.getScene().getAccelerators().put(
                new KeyCodeCombination(KeyCode.ENTER), currentRound::submitMove);
            root.getScene().getAccelerators().put(
                new KeyCodeCombination(KeyCode.ESCAPE), currentRound::clearCurrentMove);
            root.getScene().getAccelerators().put(
                new KeyCodeCombination(KeyCode.LEFT), currentRound::undoMove);
        });
    }

    /**
     * Bind a Tile object to its TileView.
     * @param v The View
     * @param t The Tile
     */
    private void bindTile(TileView v, Tile t) {
        v.valueProperty().set(t.getLetter().getCharacter());

        v.selectedProperty().bind(
            EasyBind.monadic(currentRound.moveInProgressProperty())
                .flatMap(move -> EasyBind.map(move.wordBinding(), _w -> move.getSelectedTiles().contains(t))));

        t.positionProperty().addListener((_p, oldP, newP) -> {
            v.setRow(newP.getRow());
            v.setColumn(newP.getColumn());
        });
        v.setOnMouseClicked(new SelectTileController(currentRound, t, v));
    }
}
