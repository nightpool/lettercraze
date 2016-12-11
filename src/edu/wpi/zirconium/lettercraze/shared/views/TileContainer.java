package edu.wpi.zirconium.lettercraze.shared.views;

import javafx.beans.binding.IntegerExpression;
import javafx.beans.value.ObservableValue;

interface TileContainer {
    ObservableValue<? extends Number> getSizedTileWidth();

    ObservableValue<? extends Number> getSizedTileHeight();

    ObservableValue<? extends Number> getTileX(IntegerExpression row);

    ObservableValue<? extends Number> getTileY(IntegerExpression column);
}
