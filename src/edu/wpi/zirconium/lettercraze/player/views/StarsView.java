package edu.wpi.zirconium.lettercraze.player.views;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

public class StarsView extends HBox {

    public StarsView() {
        StarView firstStar = new StarView();
        firstStar.activeProperty().bind(starsActive.greaterThan(0));
        StarView secondStar = new StarView();
        secondStar.activeProperty().bind(starsActive.greaterThan(1));
        StarView thirdStar = new StarView();
        thirdStar.activeProperty().bind(starsActive.greaterThan(2));
        this.getChildren().addAll(firstStar, secondStar, thirdStar);
    }

    private IntegerProperty starsActive = new SimpleIntegerProperty(this, "starsActive", 0);

    public int getStarsActive() {
        return starsActive.get();
    }

    public IntegerProperty starsActiveProperty() {
        return starsActive;
    }

    public void setStarsActive(int starsActive) {
        this.starsActive.set(starsActive);
    }

    private static class StarView extends SVGPath {
        public StarView() {
            this.setContent("M 192.000 212.000L 215.511 224.361L 211.021 "+
                "198.180L 230.042 179.639L 203.756 175.820L 192.000 152.000L "+
                "180.244 175.820L 153.958 179.639L 172.979 198.180L 168.489 "+
                "224.361L 192.000 212.000");
            this.setStroke(Paint.valueOf("#00000033"));
            this.setStrokeWidth(2);
            this.fillProperty().bind(
                Bindings.createObjectBinding(() -> isActive() ?
                        Paint.valueOf("#ffe1002d") : Paint.valueOf("#ffe10000"),
                this.activeProperty()));
        }
        BooleanProperty active = new SimpleBooleanProperty(this, "active", false);

        public boolean isActive() {
            return active.get();
        }

        public BooleanProperty activeProperty() {
            return active;
        }

        public void setActive(boolean active) {
            this.active.set(active);
        }
    }
}
