package edu.wpi.zirconium.lettercraze.builder.views;

import edu.wpi.zirconium.lettercraze.entities.Level;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.fxmisc.easybind.EasyBind;

import java.io.IOException;

public class EditLevelTile extends AnchorPane {
    private SimpleObjectProperty<Level> level = new SimpleObjectProperty<>(this, "level");

    public EditLevelTile() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditLevelTile.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't load FXML : " + getClass().getSimpleName());
        }

        titleProperty().bind(EasyBind.select(levelProperty()).selectObject(Level::titleProperty));
    }

    public ObjectProperty<EventHandler<? super MouseEvent>> onTileClickProperty() {
        return this.lookup(".edit-tile").onMouseClickedProperty();
    }

    public void setOnTileClicked(EventHandler<? super MouseEvent> value) {
        onTileClickProperty().set(value);
    }

    public Node getUpControl() {
        return this.lookup("#upControl");
    }

    public Node getDownControl() {
        return this.lookup("#downControl");
    }

    public Node getRemoveControl() {
        return this.lookup("#removeControl");
    }

    public Level getLevel() {
        return level.get();
    }

    public SimpleObjectProperty<Level> levelProperty() {
        return level;
    }

    public void setLevel(Level level) {
        this.level.set(level);
    }

    private Text titleNode () {
        return (Text) this.lookup("#name");
    }

    public StringProperty titleProperty() {
        return titleNode().textProperty();
    }
}
