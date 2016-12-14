package edu.wpi.zirconium.lettercraze.player.views;

import edu.wpi.zirconium.lettercraze.entities.LevelPack;
import edu.wpi.zirconium.lettercraze.entities.LevelStats;
import edu.wpi.zirconium.lettercraze.shared.views.LevelTile;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class LevelPackView extends AnchorPane {

    public LevelPackView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LevelPack.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't load FXML : " + getClass().getSimpleName());
        }
        pack.addListener(i -> {
            this.clearTiles();
            getTitleNode().setText(getPack().getTitle());
            getPack().getLevelStats().forEach(this::addTile);
        });
    }

    private void clearTiles() {
        this.getList().getChildren().clear();
    }

    private VBox getList() {
        return (VBox) ((ScrollPane) this.lookup("#scrollPane")).getContent();
    }

    private Text getTitleNode() {
        return (Text) lookup("#title");
    }

    private void addTile(LevelStats levelStats) {
        LevelTile tile = new LevelTile(levelStats);
        tile.setUnlocked(getPack().isUnlocked(levelStats));
        this.getList().getChildren().add(tile);
    }

    private final ObjectProperty<LevelPack> pack = new SimpleObjectProperty<>(this, "pack");

    public LevelPack getPack() {
        return pack.get();
    }

    public ObjectProperty<LevelPack> packProperty() {
        return pack;
    }

    public void setPack(LevelPack pack) {
        this.pack.set(pack);
    }
}
