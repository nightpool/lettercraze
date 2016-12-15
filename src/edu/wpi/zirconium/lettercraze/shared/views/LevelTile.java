package edu.wpi.zirconium.lettercraze.shared.views;

import edu.wpi.zirconium.lettercraze.entities.Level;
import edu.wpi.zirconium.lettercraze.entities.LevelStats;
import javafx.beans.property.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

import java.io.IOException;

public class LevelTile extends AnchorPane {

    public LevelTile(LevelStats levelStats) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LevelTile.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't load FXML : " + getClass().getSimpleName());
        }

        getStyleClass().add("level-tile--inactive");
        unlockedProperty().addListener((i -> {
            if (isUnlocked()) {
                getStyleClass().add("level-tile--active");
                getStyleClass().removeIf("level-tile--inactive"::equals);
            } else {
                getStyleClass().add("level-tile--inactive");
                getStyleClass().removeIf("level-tile--active"::equals);
            }
        }));

        disableProperty().bind(unlockedProperty().not());

        for (int i = 0; i < 3; i++) {
            SVGPath star = getStar(i);
            int finalI = i;
            starsProperty().addListener((_s, _o, value) -> {
                star.setFill((value.intValue() > finalI) ? Color.BLACK : Color.TRANSPARENT);
                star.setStroke((value.intValue() > finalI) ? Color.BLACK : Color.valueOf("#ebebeb"));
            });
        }

        setLevelStats(levelStats);
        setStars(levelStats.numAchievedStars());
        scoreProperty().set("" + levelStats.thresholdValue());
        titleProperty().set(levelStats.getLevel().getTitle());
    }

    private Group getContainer() {
        return (Group) this.lookup("#container");
    }

    private BooleanProperty unlocked = new SimpleBooleanProperty(this, "unlocked", false);

    public boolean isUnlocked() {
        return unlocked.get();
    }

    public BooleanProperty unlockedProperty() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked.set(unlocked);
    }

    private Text titleNode () {
        return (Text) this.lookup("#name");
    }

    private VBox starContainer () {
        return (VBox) this.lookup("#stars");
    }

    private Text scoreNode () {
        return (Text) this.lookup("#score");
    }

    private SVGPath getStar(int i) {
        return (SVGPath) starContainer().getChildren().get(i);
    }

    public StringProperty titleProperty() {
        return titleNode().textProperty();
    }

    public StringProperty scoreProperty() {
        return scoreNode().textProperty();
    }

    private IntegerProperty stars = new SimpleIntegerProperty(this, "stars", 0);
    public int getStars() {
        return stars.get();
    }

    public IntegerProperty starsProperty() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars.set(stars);
    }

    private SimpleObjectProperty<LevelStats> levelStats = new SimpleObjectProperty<>(this, "levelStats");
    public void setLevelStats(LevelStats levelStats) {
        this.levelStats.set(levelStats);
    }

    public LevelStats getLevelStats() {
        return this.levelStats.get();
    }

    public Level getLevel() {
        return getLevelStats().getLevel();
    }
}
