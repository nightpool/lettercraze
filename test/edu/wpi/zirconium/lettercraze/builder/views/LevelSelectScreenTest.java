package edu.wpi.zirconium.lettercraze.builder.views;

import edu.wpi.zirconium.lettercraze.entities.*;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.service.query.NodeQuery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;

public class LevelSelectScreenTest extends ApplicationTest {

    @Test
    public void testDisplay() throws Exception {
        verifyThat(lookup("#puzzlePack"), NodeMatchers.hasChildren(5, ".edit-tile"));
        verifyThat(lookup("#lightningPack"), NodeMatchers.hasChildren(5, ".edit-tile"));
        verifyThat(lookup("#themePack"), NodeMatchers.hasChildren(5, ".edit-tile"));
    }

    @Test
    public void testBuilder() throws Exception {
        clickOn(".edit-tile");

        verifyThat(".root", Matchers.<Node>instanceOf(BuilderScreen.class));
    }

    @Test
    public void testNewPuzzle() throws Exception {
        clickOn("#newPuzzle");

        verifyThat(".root", Matchers.<Node>instanceOf(BuilderScreen.class));

        BuilderScreen screen = lookup(".root").query();
        assertTrue(screen.getLevel().canSave());
        assertThat(screen.getLevel(), Matchers.instanceOf(PuzzleLevel.class));
    }

    @Test
    public void testNewLightning() throws Exception {
        clickOn("#newLightning");

        verifyThat(".root", Matchers.<Node>instanceOf(BuilderScreen.class));

        BuilderScreen screen = lookup(".root").query();
        assertTrue(screen.getLevel().canSave());
        assertThat(screen.getLevel(), Matchers.instanceOf(LightningLevel.class));
    }

    @Test
    public void testNewTheme() throws Exception {
        clickOn("#newTheme");

        verifyThat(".root", Matchers.<Node>instanceOf(BuilderScreen.class));

        BuilderScreen screen = lookup(".root").query();
        assertTrue(screen.getLevel().canSave());
        assertThat(screen.getLevel(), Matchers.instanceOf(ThemeLevel.class));
    }

    @Test
    public void testRemove() throws Exception {
        NodeQuery query = lookup("#puzzlePack *").match(Matchers.instanceOf(EditLevelTile.class));
        EditLevelTile tile = query.query();
        assertTrue(tile.getLevel().getPack().isPresent());
        LevelPack lp = tile.getLevel().getPack().get();
        assertTrue(lp.statsForLevel(tile.getLevel()).isPresent());
        LevelStats ls = lp.statsForLevel(tile.getLevel()).get();

        assertThat(lp.getLevelStats(), Matchers.hasItem(ls));

        clickOn(query.lookup("#removeControl").<Node>query());

        assertThat(lp.getLevelStats(), Matchers.not(Matchers.hasItem(ls)));
    }

    @Test
    public void testMoveUp() throws Exception {
        NodeQuery query = lookup("#puzzlePack *")
            .match(Matchers.instanceOf(EditLevelTile.class))
            .nth(1);
        EditLevelTile tile = query.query();

        assertTrue(tile.getLevel().getPack().isPresent());
        LevelPack lp = tile.getLevel().getPack().get();
        assertTrue(lp.statsForLevel(tile.getLevel()).isPresent());
        LevelStats ls = lp.statsForLevel(tile.getLevel()).get();

        int index = lp.getLevelStats().indexOf(ls);

        clickOn(query.lookup("#upControl").<Node>query());

        assertEquals(index - 1, lp.getLevelStats().indexOf(ls));
    }

    @Test
    public void testCantMoveUp() throws Exception {
        NodeQuery query = lookup("#puzzlePack *")
            .match(Matchers.instanceOf(EditLevelTile.class))
            .nth(0);
        EditLevelTile tile = query.query();

        assertTrue(tile.getLevel().getPack().isPresent());
        LevelPack lp = tile.getLevel().getPack().get();
        assertTrue(lp.statsForLevel(tile.getLevel()).isPresent());
        LevelStats ls = lp.statsForLevel(tile.getLevel()).get();

        int index = lp.getLevelStats().indexOf(ls);

        clickOn(query.lookup("#upControl").<Node>query());

        assertEquals(index, lp.getLevelStats().indexOf(ls));
    }

    @Test
    public void testMoveDown() throws Exception {
        NodeQuery query = lookup("#puzzlePack *")
            .match(Matchers.instanceOf(EditLevelTile.class));
        EditLevelTile tile = query.query();

        assertTrue(tile.getLevel().getPack().isPresent());
        LevelPack lp = tile.getLevel().getPack().get();
        assertTrue(lp.statsForLevel(tile.getLevel()).isPresent());
        LevelStats ls = lp.statsForLevel(tile.getLevel()).get();

        int index = lp.getLevelStats().indexOf(ls);

        clickOn(query.lookup("#downControl").<Node>query());

        assertEquals(index + 1, lp.getLevelStats().indexOf(ls));
    }

    @Override
    public void start(Stage stage) throws Exception {
        LevelSelectScreen screen = new LevelSelectScreen(this::mockPack);
        stage.setScene(new Scene(screen, 1040, 940));
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