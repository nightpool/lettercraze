package edu.wpi.zirconium.utils;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StyleClassPropertyTest {

    private Node testNode;
    private BooleanProperty testProperty;


    @Before
    public void setUp() throws Exception {
        testNode = new Group();
        testProperty = new SimpleBooleanProperty(false);
        testProperty.addListener(new StyleClassProperty(testNode, "testProperty"));
    }

    @Test
    public void initial() {
        assertFalse(testNode.getStyleClass().contains("testProperty"));
    }

    @Test
    public void afterChange() {
        testProperty.set(true);
        assertTrue(testNode.getStyleClass().contains("testProperty"));
    }

    @Test
    public void afterChangeFalse() {
        testProperty.set(false);
        assertFalse(testNode.getStyleClass().contains("testProperty"));
    }

    @Test
    public void changeTwice() {
        testProperty.set(true);
        assertTrue(testNode.getStyleClass().contains("testProperty"));
        testProperty.set(false);
        assertFalse(testNode.getStyleClass().contains("testProperty"));
        testProperty.set(true);
        assertTrue(testNode.getStyleClass().contains("testProperty"));
    }
}