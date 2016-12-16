package edu.wpi.zirconium.lettercraze.player.views;

import edu.wpi.zirconium.lettercraze.entities.Level;
import edu.wpi.zirconium.lettercraze.entities.LevelPack;
import edu.wpi.zirconium.lettercraze.entities.PuzzleLevelStats;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class LevelScreenSelectTest extends ApplicationTest {

    /**
     * Test method for {@link LevelSelectScreen#LevelSelectScreen()}
     */
    @Test
    public void testLevelSelectScreen() {
        verifyThat("#root", NodeMatchers.isNotNull());
    }

    @Override
    public void start(Stage stage) throws Exception {
        LevelSelectScreen screen = new LevelSelectScreen(this::mockPack);
        stage.setScene(new Scene(screen, 1040, 712));
        stage.show();
    }

    private LevelPack mockPack(String s) {
        return new LevelPack(s) {
            {
                levelStats = FXCollections.observableArrayList(
                    new PuzzleLevelStats(Level.dummy(6), 1),
                    new PuzzleLevelStats(Level.dummy(6), 2),
                    new PuzzleLevelStats(Level.dummy(6), 3),
                    new PuzzleLevelStats(Level.dummy(6), 0),
                    new PuzzleLevelStats(Level.dummy(6), 0));
                levelStats.forEach(ls -> ls.getLevel().setPack(this));
            }

            @Override
            public void saveStats() {
                // noop
            }

            @Override
            public String getTitle() {
                return "Mock Title";
            }
        };
    }
}