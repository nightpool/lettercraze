package edu.wpi.zirconium.lettercraze.builder.controllers;

import edu.wpi.zirconium.lettercraze.builder.LetterCrazeBuilder;
import edu.wpi.zirconium.lettercraze.entities.Board;
import edu.wpi.zirconium.lettercraze.entities.Level;
import edu.wpi.zirconium.lettercraze.entities.LevelShape;
import edu.wpi.zirconium.lettercraze.entities.Tile;
import edu.wpi.zirconium.lettercraze.player.controllers.SelectTileController;
import edu.wpi.zirconium.lettercraze.shared.views.BoardView;
import edu.wpi.zirconium.lettercraze.shared.views.TileView;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class BuilderControllers implements Initializable {

    @FXML private Pane backButton;
    @FXML private Button saveButton;
    @FXML private Button newButton;
    @FXML private Button loadButton;
    @FXML private Button previewButton;

    @FXML private BoardView board;
    
    private Board realBoard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	backButton.setOnMouseClicked(_me -> LetterCrazeBuilder.showMenuScreen());

    	realBoard = Board.dummy(6);

    	realBoard.observableTiles().addListener(
    			(ListChangeListener<? super Tile>) l -> {
    				while (l.next()) {
    					for (Tile t : l.getAddedSubList()) {
    						TileView v = board.newTile(t.getPos());
    						this.bindTile(v, t);
    						board.getTiles().add(v);
    					}

    					for (Tile t : l.getRemoved()) {
    						board.getTiles()
    						.removeIf(v -> v.getPos().equals(t.getPos()));
    					}
    				}
    			}
    			);

    	System.out.println(board.getTiles().size());

    	//board.getTiles().forEach(t -> t.setOnMouseClicked(c -> t.toggleBlocked()));
    	realBoard.clear();

    	//realBoard = new Board(new LevelShape(6));


    }
    
    private void bindTile(TileView v, Tile t) {
        v.valueProperty().set(t.getLetter().getCharacter());
//        currentRound.moveInProgressProperty().addListener((_m, _o, newMove) -> {
//            v.selectedProperty().bind(Bindings.createBooleanBinding(
//                () -> newMove.getSelectedTiles().contains(t),
//                newMove.getSelectedTiles()));
//        });
        t.positionProperty().addListener((_p, oldP, newP) -> {
            v.setRow(newP.getRow());
            v.setColumn(newP.getColumn());
        });
        v.setOnMouseClicked(new EnableTileController(realBoard, t, v));
    }
}
