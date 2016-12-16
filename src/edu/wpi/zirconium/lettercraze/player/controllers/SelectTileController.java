package edu.wpi.zirconium.lettercraze.player.controllers;

import edu.wpi.zirconium.lettercraze.entities.Round;
import edu.wpi.zirconium.lettercraze.entities.Tile;
import edu.wpi.zirconium.lettercraze.shared.views.TileView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SelectTileController implements EventHandler<MouseEvent> {
    private final Tile tile;
    private final TileView tileView;
    private final Round round;
    
    /**
     * Creates a SelectTileController given a Round, Tile, and TileView.
     * @param round Round to bind controller to.
     * @param tile Tile to bind controller to.
     * @param tileView TileView to bind controller to.
     */
    public SelectTileController(Round round, Tile tile, TileView tileView) {
        this.round = round;
        this.tile = tile;
        this.tileView = tileView;
    }

    @Override
    public void handle(MouseEvent event) {
        if (round.canSelectTile(tile)) {
            boolean success = round.selectTile(tile);
        } else if (round.canDeselectTile(tile)) {
            boolean success = round.deselectTile(tile);
        }
    }
}
