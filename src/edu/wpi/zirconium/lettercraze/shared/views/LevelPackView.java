package edu.wpi.zirconium.lettercraze.shared.views;

import edu.wpi.zirconium.lettercraze.builder.views.EditLevelTile;
import edu.wpi.zirconium.lettercraze.entities.LevelPack;
import edu.wpi.zirconium.lettercraze.entities.LevelStats;
import javafx.beans.InvalidationListener;
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
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
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

        pack.addListener(_i -> {
            this.clearTiles();
            packList.clear();
            getTitleNode().setText(getPack().getTitle());
            getPack().getLevelStats().forEach(this::addTile);
            getList().getChildren().addAll(this.getStaticTiles());
            getPack().getLevelStats().addListener((InvalidationListener) __i -> {
                this.clearTiles();
                getPack().getLevelStats().forEach(ls -> {
                    if(packList.containsKey(ls)) {
                        getList().getChildren().add(packList.get(ls));
                    } else {
                        addTile(ls);
                    }
                });
                getList().getChildren().addAll(this.getStaticTiles());
            });
        });
    }

    private Map<LevelStats, Node> packList = new HashMap<>();

    private void clearTiles() {
        this.getList().getChildren().clear();
    }

    private VBox getList() {
        return (VBox) ((ScrollPane) this.lookup("#scrollPane")).getContent();
    }

    private Text getTitleNode() {
        return (Text) lookup("#title");
    }

    public Stream<LevelTile> getTiles() {
        return this.getList().getChildren().stream()
            .filter(t -> t instanceof LevelTile)
            .map(t -> (LevelTile) t);
    }

    private Consumer<EditLevelTile> onNewEditTile;
    public void setOnNewEditTile(Consumer<EditLevelTile> onNewEditTile) {
        this.onNewEditTile = onNewEditTile;
    }

    private void addTile(LevelStats levelStats) {
        if (this.isEditable()) {
            EditLevelTile tile = new EditLevelTile();
            tile.setLevel(levelStats.getLevel());
            this.getList().getChildren().add(tile);
            onNewEditTile.accept(tile);
        } else {
            LevelTile tile = new LevelTile(levelStats);
            tile.setUnlocked(getPack().isUnlocked(levelStats));
            this.getList().getChildren().add(tile);
        }
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

    private BooleanProperty editable = new SimpleBooleanProperty(this, "editable", false);

    public boolean isEditable() {
        return editableProperty().get();
    }

    public BooleanProperty editableProperty() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editableProperty().set(editable);
    }

    private ListProperty<Node> staticTiles = new SimpleListProperty<>(this, "staticTiles", FXCollections.observableArrayList());

    public ObservableList<Node> getStaticTiles() {
        return staticTiles.get();
    }
}
