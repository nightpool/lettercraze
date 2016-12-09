package edu.wpi.zirconium.lettercraze.shared;

import edu.wpi.zirconium.lettercraze.entities.Point;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.IntegerExpression;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

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
            throw new IllegalStateException("Can't load FXML : "+getClass().getSimpleName());
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

    DoubleBinding getTileX(IntegerExpression x) {
        return getSizedTileWidth().multiply(x).add(getSpacingWidth().multiply(x.add(1)));
    }

    DoubleBinding getTileY(IntegerExpression y) {
        return getSizedTileHeight().multiply(y).add(getSpacingHeight().multiply(y.add(1)));
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

    public TileView newTile(Point p) {
        return new TileView(this, p.getRow(), p.getColumn());
    }

}
