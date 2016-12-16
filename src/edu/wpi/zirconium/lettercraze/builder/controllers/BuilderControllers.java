package edu.wpi.zirconium.lettercraze.builder.controllers;

import edu.wpi.zirconium.lettercraze.builder.LetterCrazeBuilder;
import edu.wpi.zirconium.lettercraze.builder.views.BuilderScreen;
import edu.wpi.zirconium.lettercraze.entities.Level;
import edu.wpi.zirconium.lettercraze.entities.LightningLevel;
import edu.wpi.zirconium.lettercraze.entities.PuzzleLevel;
import edu.wpi.zirconium.lettercraze.entities.ThemeLevel;
import edu.wpi.zirconium.lettercraze.player.views.LevelScreen;
import edu.wpi.zirconium.lettercraze.shared.views.BoardView;
import edu.wpi.zirconium.lettercraze.shared.views.TileView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BuilderControllers implements Initializable {

    @FXML private BuilderScreen root;

    @FXML private Pane backButton;
    @FXML private Button saveButton;
    @FXML private Button previewButton;

    @FXML private TextField title;
    @FXML private BoardView board;
    @FXML private TabPane tabPane;

    private Level level;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        level = root.getLevel();

        backButton.setOnMouseClicked(_me -> LetterCrazeBuilder.showSelectScreen());

        saveButton.disableProperty().bind(level.pathProperty().isNull());
        saveButton.setOnMouseClicked(_me -> level.save());

        title.textProperty().bindBidirectional(level.titleProperty());

        board.setBoardHeight(level.getShape().getSize());
        board.setBoardWidth(level.getShape().getSize());
        level.getShape().points().forEach(p -> {
            TileView v = board.newTile(p);
            v.blockedProperty().bind(level.getShape().bindingAt(p).not());
            v.setOnMouseClicked(me -> {
                level.getShape().toggleTile(p);
            });
            board.getTiles().add(v);
        });

        previewButton.setOnMouseClicked(_me -> {
            Stage stage = new Stage();
            stage.initOwner(root.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(new Scene(new LevelScreen(getLevel()), 1024, 712));
            stage.show();
        });

        if (level instanceof PuzzleLevel) {
            setupPuzzle((PuzzleLevel) level);
        } else if (level instanceof LightningLevel) {
            setupLightning((LightningLevel) level);
        } else if (level instanceof ThemeLevel) {
            setupTheme((ThemeLevel) level);
        }

        tabPane.getSelectionModel().selectedIndexProperty().addListener(i -> getLevel());
    }

    private Level getLevel() {
        if (tabPane.getSelectionModel().getSelectedIndex() == 0) {
            if (puzzleSub == null) {
                setupPuzzle(null);
            }
            return puzzleSub;
        } else if (tabPane.getSelectionModel().getSelectedIndex() == 1) {
            if (lightningSub == null) {
                setupLightning(null);
            }
            return lightningSub;
        } else if (tabPane.getSelectionModel().getSelectedIndex() == 2) {
            if (themeSub == null) {
                setupTheme(null);
            }
            return themeSub;
        }
        return null;
    }

    @FXML private TextField puzzleWords;
    @FXML private TextField puzzleStar1;
    @FXML private TextField puzzleStar2;
    @FXML private TextField puzzleStar3;

    private PuzzleLevel puzzleSub;
    private void setupPuzzle(PuzzleLevel level) {
        if (level != null) {
            puzzleSub = level;
            puzzleWords.setText(String.valueOf(puzzleSub.getWordLimit()));
            puzzleStar1.setText(String.valueOf(puzzleSub.getThreshold(0)));
            puzzleStar2.setText(String.valueOf(puzzleSub.getThreshold(1)));
            puzzleStar3.setText(String.valueOf(puzzleSub.getThreshold(2)));
        } else {
            puzzleSub = new PuzzleLevel(this.level.getShape().getSize());
            puzzleSub.setShape(this.level.getShape());
            puzzleSub.titleProperty().bind(this.level.titleProperty());
        }
        puzzleWords.textProperty().addListener(new NumberValidator(
            i -> puzzleSub.setWordLimit(i), puzzleWords));

        puzzleStar1.textProperty().addListener(new NumberValidator(
            i -> puzzleSub.setThreshold(0, i), puzzleStar1));
        puzzleStar2.textProperty().addListener(new NumberValidator(
            i -> puzzleSub.setThreshold(1, i), puzzleStar2));
        puzzleStar3.textProperty().addListener(new NumberValidator(
            i -> puzzleSub.setThreshold(2, i), puzzleStar3));
    }

    @FXML private TextField lightningTime;
    @FXML private TextField lightningStar1;
    @FXML private TextField lightningStar2;
    @FXML private TextField lightningStar3;

    private LightningLevel lightningSub;
    private void setupLightning(LightningLevel level) {
        if (level != null) {
            lightningSub = level;
            lightningTime.setText(String.valueOf(lightningSub.getMaxTime()));
            lightningStar1.setText(String.valueOf(lightningSub.getThreshold(0)));
            lightningStar2.setText(String.valueOf(lightningSub.getThreshold(1)));
            lightningStar3.setText(String.valueOf(lightningSub.getThreshold(2)));
        } else {
            lightningSub = new LightningLevel(this.level.getShape().getSize());
            lightningSub.setShape(this.level.getShape());
            lightningSub.titleProperty().bind(this.level.titleProperty());
        }

        lightningTime.textProperty().addListener(new NumberValidator(
            i -> lightningSub.setMaxTime(i), lightningTime));

        lightningStar1.textProperty().addListener(new NumberValidator(
            i -> lightningSub.setThreshold(0, i), lightningStar1));
        lightningStar2.textProperty().addListener(new NumberValidator(
            i -> lightningSub.setThreshold(1, i), lightningStar2));
        lightningStar3.textProperty().addListener(new NumberValidator(
            i -> lightningSub.setThreshold(2, i), lightningStar3));
    }

    @FXML private TextArea themeWords;

    private ThemeLevel themeSub;
    private void setupTheme(ThemeLevel level) {
        if (level != null) {
            themeSub = level;
            themeWords.setText(themeSub.getWords().stream().collect(Collectors.joining("\n")));
        } else {
            themeSub = new ThemeLevel(this.level.getShape().getSize());
            themeSub.setShape(this.level.getShape());
            themeSub.titleProperty().bind(this.level.titleProperty());
        }

        themeWords.textProperty().addListener((_o, _v, newValue) -> {
            themeSub.setWords(
                Stream.of(newValue.split("\n")).collect(Collectors.toList())
            );
        });
    }

    private static class NumberValidator implements ChangeListener<String> {
        private Consumer<Integer> consumer;
        private Node display;

        private NumberValidator(Consumer<Integer> consumer, Node display) {
            this.consumer = consumer;
            this.display = display;
        }

        @Override
        public void changed(ObservableValue<? extends String> _n, String _v, String newValue) {
            try {
                int i = Integer.parseInt(newValue);
                display.getStyleClass().removeIf("invalid"::equals);
                consumer.accept(i);
            } catch (NumberFormatException n) {
                display.getStyleClass().add("invalid");
            }
        }
    }
}
