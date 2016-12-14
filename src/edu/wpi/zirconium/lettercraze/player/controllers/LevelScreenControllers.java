package edu.wpi.zirconium.lettercraze.player.controllers;

import edu.wpi.zirconium.lettercraze.entities.Level;
import edu.wpi.zirconium.lettercraze.entities.Move;
import edu.wpi.zirconium.lettercraze.entities.Round;
import edu.wpi.zirconium.lettercraze.entities.Tile;
import edu.wpi.zirconium.lettercraze.player.LetterCrazePlayer;
import edu.wpi.zirconium.lettercraze.player.views.LevelScreen;
import edu.wpi.zirconium.lettercraze.player.views.StarsView;
import edu.wpi.zirconium.lettercraze.player.views.SubmitButton;
import edu.wpi.zirconium.lettercraze.shared.views.BoardView;
import edu.wpi.zirconium.lettercraze.shared.views.TileView;
import edu.wpi.zirconium.utils.TimeFormatter;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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
    @FXML private SubmitButton submit;

    private Round currentRound;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exitLevel.setOnMouseClicked(this::onExitClicked);
        reset.setOnMouseClicked(this::onResetClicked);

        Level level = Level.get(root.getLevelKey());
        currentRound = new Round(level);

        title.textProperty().bind(level.titleProperty());
        score.textProperty().bind(currentRound.scoreBinding().asString());

        stars.starsActiveProperty().bind(currentRound.starsEarnedBinding());

        time.textProperty().bind(TimeFormatter.forValue(currentRound.timeProperty()));
        timeLabel.setText("time");

        Timer timeUpdater = new Timer(true);
        timeUpdater.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(currentRound::incrementTime);
            }
        }, 0, 1000);

        // TODO: something different for theme levels? Words remaining?
        wordsFound.textProperty().bind(currentRound.getWordsFound().sizeProperty().asString());

        currentRound.getCompletedMoves().addListener((InvalidationListener) o -> {
            String moves = currentRound.getCompletedMoves().stream()
                .map(Move::asString).collect(Collectors.joining("\n"));
            previousMovesDisplay.setText(moves);
        });

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
        currentRound.moveInProgressProperty().addListener((_m, _o, newMove) -> {
            wordPreview.textProperty().bind(Bindings.createStringBinding(
                () -> newMove.getWord().asString(),
                newMove.wordBinding()));
        });

        submit.setOnMouseClicked(me -> currentRound.submitMove());
        currentRound.moveInProgressProperty().addListener((_m, _o, newMove) -> {
            submit.validProperty().bind(currentRound.currentMoveValidBinding());
            submit.scoreProperty().bind(newMove.scoreBinding());
        });

        currentRound.reset();
    }

    private void bindTile(TileView v, Tile t) {
        v.valueProperty().set(t.getLetter().getCharacter());
        currentRound.moveInProgressProperty().addListener((_m, _o, newMove) -> {
            v.selectedProperty().bind(Bindings.createBooleanBinding(
                () -> newMove.getSelectedTiles().contains(t),
                newMove.getSelectedTiles()));
        });
        t.positionProperty().addListener((_p, oldP, newP) -> {
            v.setRow(newP.getRow());
            v.setColumn(newP.getColumn());
        });
        v.setOnMouseClicked(new SelectTileController(currentRound, t, v));
    }

    private void onExitClicked(MouseEvent mouseEvent) {
        LetterCrazePlayer.showLevelSelectScreen();
    }
    
    private void onResetClicked(MouseEvent mouseEvent) {
        currentRound.reset();
    }
}
