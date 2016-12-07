package edu.wpi.zirconium.lettercraze.shared;

import edu.wpi.zirconium.utils.StyleClassProperty;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import sun.plugin.dom.exception.InvalidStateException;

import java.io.IOException;

public class BoardView extends Pane {
    private ObservableList<TileView> tiles = FXCollections.observableArrayList();

    public BoardView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Board.fxml"));
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new InvalidStateException("Can't load FXML : "+getClass().getSimpleName());
        }

        this.prefHeightProperty().bind(this.widthProperty());

        tiles.addListener((ListChangeListener<? super TileView>) c -> {
            while (c.next()) {
                for (TileView remitem : c.getRemoved()) {
                    this.getChildren().remove(remitem);
                }
                for (TileView additem : c.getAddedSubList()) {
                    this.getChildren().add(additem);
                }
            }
        });
    }

    public ObservableList<TileView> getTiles() {
        return tiles;
    }

    private DoubleBinding getTileX(int x) {
        return getSizedTileWidth().multiply(x).add(getSpacingWidth().multiply(x+1));
    }

    private DoubleBinding getTileY(int y) {
        return getSizedTileHeight().multiply(y).add(getSpacingHeight().multiply(y+1));
    }


    private DoubleBinding tileWidth;
    protected DoubleBinding getSizedTileWidth() {
        if (tileWidth == null) {
            tileWidth = widthProperty().divide(boardWidthProperty().multiply(8).add(3)).multiply(5);
        }
        return tileWidth;
    }

    private DoubleBinding tileHeight;
    protected DoubleBinding getSizedTileHeight() {
        if (tileHeight == null) {
            tileHeight = heightProperty().divide(boardHeightProperty().multiply(8).add(3)).multiply(5);
        }
        return tileHeight;
    }

    private DoubleBinding spacingWidth;
    protected DoubleBinding getSpacingWidth() {
        if (spacingWidth == null) {
            spacingWidth = widthProperty().divide(boardWidthProperty().multiply(8).add(3)).multiply(3);
        }
        return spacingWidth;
    }

    private DoubleBinding spacingHeight;
    protected DoubleBinding getSpacingHeight() {
        if (spacingHeight == null) {
            spacingHeight = heightProperty().divide(boardHeightProperty().multiply(8).add(3)).multiply(3);
        }
        return spacingHeight;
    }

    private IntegerProperty boardWidth = new SimpleIntegerProperty(this, "boardWidth", 6);
    private IntegerProperty boardHeight = new SimpleIntegerProperty(this, "boardHeight", 6);

    public int getBoardWidth() {
        return boardWidth.get();
    }

    public IntegerProperty boardWidthProperty() {
        return boardWidth;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth.set(boardWidth);
    }

    public int getBoardHeight() {
        return boardHeight.get();
    }

    public IntegerProperty boardHeightProperty() {
        return boardHeight;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight.set(boardHeight);
    }

    public TileView newTile(int x, int y) {
        return new TileView(x, y);
    }

    public class TileView extends StackPane {
        TileView(int x, int y) {
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Tile.fxml"));
            fxmlLoader.setRoot(this);

            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
                throw new InvalidStateException("Can't load FXML : "+getClass().getSimpleName());
            }

            getRectangle().widthProperty().bind(getSizedTileWidth());
            getRectangle().heightProperty().bind(getSizedTileHeight());

            layoutXProperty().bind(getTileX(x));
            layoutYProperty().bind(getTileY(y));

            blockedProperty().addListener(new StyleClassProperty(this, "blocked"));
            selectedProperty().addListener(new StyleClassProperty(this, "selected"));
        }

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

        public void toggleInactive() {
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
}
