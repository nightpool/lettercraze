package edu.wpi.zirconium.lettercraze.player.views;

import javafx.scene.Scene;
import javafx.stage.Stage;

import static org.testfx.api.FxAssert.verifyThat;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

public class LevelScreenSelectTest extends ApplicationTest {

    @Test
    public void testSomething() {
        verifyThat("#root", NodeMatchers.isNotNull());
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        LevelSelectScreen lss = new LevelSelectScreen();
        Scene scene = new Scene(lss, 1024, 712);
        stage.setScene(scene);
        stage.show();
    }
}