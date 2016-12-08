package edu.wpi.zirconium.components;

import javafx.application.Platform;
import javafx.beans.DefaultProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sun.plugin.dom.exception.InvalidStateException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@DefaultProperty("items")
public class HamburgerMenu extends Pane implements Initializable {

    @FXML private Pane hamburger;
    @FXML private VBox hamburgerMenu;

    public HamburgerMenu() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HamburgerMenu.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        this.items.addListener((ListChangeListener<? super Item>) c -> {
            while (c.next()) {
                c.getRemoved().stream()
                    .filter(item -> item != null && !getItems().contains(item))
                    .forEach(hamburgerMenu.getChildren()::remove);

                c.getAddedSubList().stream()
                    .filter(item -> item != null)
                    .forEach(hamburgerMenu.getChildren()::add);
            }
        });

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new InvalidStateException("Can't load FXML : Hamburger Menu");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hamburger.setOnMouseClicked(this::openHamburger);
    }

    private void openHamburger(MouseEvent mouseEvent) {
        if (!hamburgerMenu.isVisible()) {
            hamburgerMenu.setVisible(true);
            hamburger.getStyleClass().add("active");

            Platform.runLater(() -> {
                hamburger.getScene().setOnMouseClicked(me -> {
                    hamburger.getStyleClass().remove("active");
                    hamburgerMenu.setVisible(false);
                    hamburger.getScene().setOnMouseClicked(null);
                });
            });
        }
    }

    private ObservableList<Item> items = FXCollections.observableArrayList();

    public ObservableList<Item> getItems() {
        return items;
    }

    public static class Item extends StackPane implements Initializable {
        public Item() {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HamburgerMenuItem.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
                throw new InvalidStateException("Can't load FXML : Hamburger Menu Item");
            }
        }

        public EventHandler<ActionEvent> getOnAction() {
            return onActionProperty().get();
        }

        public ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {
            if (onAction == null) {
                this.onAction = new SimpleObjectProperty<>(this, "onAction", _ae -> {});
            }
            return onAction;
        }

        public void setOnAction(EventHandler<ActionEvent> onAction) {
            onActionProperty().set(onAction);
        }

        ObjectProperty<EventHandler<ActionEvent>> onAction;

        public String getLabel() {
            return label.get();
        }

        public StringProperty labelProperty() {
            if (label == null) {
                label = new SimpleStringProperty(this, "label");
            }
            return label;
        }

        public void setLabel(String label) {
            this.label.set(label);
        }

        StringProperty label;

        @FXML StackPane root;
        @FXML Text text;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            this.text.textProperty().bind(labelProperty());
            this.root.setOnMouseClicked(_me -> onAction.get().handle(new ActionEvent(null, this)));
        }
    }
}
