package edu.wpi.zirconium.lettercraze.shared.views;

import edu.wpi.zirconium.lettercraze.entities.Letter;
import edu.wpi.zirconium.lettercraze.entities.Point;
import edu.wpi.zirconium.utils.StyleClassProperty;
import javafx.beans.property.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.io.IOException;

public class TileView extends StackPane {

    public TileView () {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Tile.fxml"));
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't load FXML : "+getClass().getSimpleName());
        }

        blockedProperty().addListener(new StyleClassProperty(this, "blocked"));
        selectedProperty().addListener(new StyleClassProperty(this, "selected"));
        editableProperty().addListener(new StyleClassProperty(this, "editable"));

        editNode().prefWidthProperty().bind(this.widthProperty().multiply(.5));
        editNode().prefHeightProperty().bind(this.heightProperty().multiply(.5));

        editNode().setTextFormatter(new TextFormatter<Letter>(new StringConverter<Letter>() {
            @Override
            public String toString(Letter letter) {
                return letter == null ? "" : letter.toString();
            }
            @Override
            public Letter fromString(String string) {
                return Letter.forString(string);
            }
        }));
    }

    public void layoutIn(TileContainer container) {
        getRectangle().widthProperty().bind(container.getSizedTileWidth());
        getRectangle().heightProperty().bind(container.getSizedTileHeight());

        layoutXProperty().bind(container.getTileX(columnProperty()));
        layoutYProperty().bind(container.getTileY(rowProperty()));
    }

    public void setPos(Point pos) {
        this.setColumn(pos.getColumn());
        this.setRow(pos.getRow());
    }

    public TextField editNode() {
        return (TextField) this.lookup(".board--letter-edit");
    }

    public StringProperty editProperty() {
        return editNode().textProperty();
    }

    public Letter editValue() {
        return (Letter) editNode().getTextFormatter().getValue();
    }

    public Point getPos() {
        return new Point(this.getRow(), this.getColumn());
    }

    public int getRow() {
        return row.get();
    }

    public IntegerProperty rowProperty() {
        return row;
    }

    public void setRow(int row) {
        this.row.set(row);
    }

    public int getColumn() {
        return column.get();
    }

    public IntegerProperty columnProperty() {
        return column;
    }

    public void setColumn(int column) {
        this.column.set(column);
    }

    private IntegerProperty row = new SimpleIntegerProperty(this, "row");
    private IntegerProperty column = new SimpleIntegerProperty(this, "column");

    public StringProperty valueProperty() {
        return getText().textProperty();
    }

    public String getLetter() {
        return getText().getText();
    }

    private Rectangle getRectangle() {
        return (Rectangle) this.lookup(".board--tile-shape");
    }
    private Text getText() {
        return (Text) this.lookup(".board--letter");
    }

    private BooleanProperty blocked = new SimpleBooleanProperty(this, "blocked", false);
    public BooleanProperty blockedProperty() {
        return blocked;
    }

    public boolean isBlocked() {
        return blocked.get();
    }

    private BooleanProperty selected = new SimpleBooleanProperty(this, "selected", false);
    public BooleanProperty selectedProperty() {
        return selected;
    }

    private BooleanProperty editable = new SimpleBooleanProperty(this, "editable", false);
    public BooleanProperty editableProperty() {
        return editable;
    }
}
