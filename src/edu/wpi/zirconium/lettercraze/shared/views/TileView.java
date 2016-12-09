package edu.wpi.zirconium.lettercraze.shared.views;

import edu.wpi.zirconium.utils.StyleClassProperty;
import javafx.beans.property.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;

public class TileView extends StackPane {
    private BoardView board;

    TileView(BoardView board, int x, int y) {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Tile.fxml"));
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't load FXML : "+getClass().getSimpleName());
        }

        getRectangle().widthProperty().bind(board.getSizedTileWidth());
        getRectangle().heightProperty().bind(board.getSizedTileHeight());

        this.row.set(x);
        this.column.set(y);
        layoutXProperty().bind(board.getTileX(this.row));
        layoutYProperty().bind(board.getTileY(this.column));

        blockedProperty().addListener(new StyleClassProperty(this, "blocked"));
        selectedProperty().addListener(new StyleClassProperty(this, "selected"));
        this.board = board;
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

    private Rectangle getRectangle() {
        return (Rectangle) this.lookup(".board--tile-shape");
    }
    private Text getText() {
        return (Text) this.lookup(".board--letter");
    }

    private BooleanProperty blocked = new SimpleBooleanProperty(this, "blocked", false);

    public boolean getBlocked() {
        return blocked.get();
    }

    public BooleanProperty blockedProperty() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked.set(blocked);
    }

    public void toggleBlocked() {
        setBlocked(!getBlocked());
    }

    private BooleanProperty selected = new SimpleBooleanProperty(this, "selected", false);

    public boolean isSelected() {
        return selected.get();
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
}
