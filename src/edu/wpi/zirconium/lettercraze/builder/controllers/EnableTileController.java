package edu.wpi.zirconium.lettercraze.builder.controllers;

import edu.wpi.zirconium.lettercraze.entities.Board;
import edu.wpi.zirconium.lettercraze.entities.Round;
import edu.wpi.zirconium.lettercraze.entities.Tile;
import edu.wpi.zirconium.lettercraze.shared.views.TileView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class EnableTileController implements EventHandler<MouseEvent> {
    private final Tile tile;
    private final TileView tileView;
    private final Board board;

    public EnableTileController(Board board, Tile tile, TileView tileView) {
        this.board = board;
        this.tile = tile;
        this.tileView = tileView;
    }

    @Override
    public void handle(MouseEvent event) {
    	if (board.getShape().isTile(tile.getPos())){
    		board.getShape().setTile(tile.getPos(), false);
    	}
    	else
    		board.getShape().setTile(tile.getPos(), true);
    }
}
