package edu.wpi.zirconium.lettercraze.shared.views;

import edu.wpi.zirconium.lettercraze.entities.LevelPack;
import edu.wpi.zirconium.lettercraze.entities.LevelStats;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.stream.Stream;

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
            getList().getChildren().addAll(this.getStaticTiles());
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

    public Stream<LevelTile> getTiles() {
        return this.getList().getChildren().stream()
            .filter(t -> t instanceof LevelTile)
            .map(t -> (LevelTile) t);
    }

    private BooleanProperty editable = new SimpleBooleanProperty(this, "editable", false);

    public boolean isEditable() {
        return editable.get();
    }

    public BooleanProperty editableProperty() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable.set(editable);
    }

    private ListProperty<Node> staticTiles = new SimpleListProperty<>(this, "staticTiles", FXCollections.observableArrayList());

    public ObservableList<Node> getStaticTiles() {
        return staticTiles.get();
    }
}
