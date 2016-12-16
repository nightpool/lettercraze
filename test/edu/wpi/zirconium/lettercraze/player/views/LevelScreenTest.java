package edu.wpi.zirconium.lettercraze.player.views;

import edu.wpi.zirconium.lettercraze.entities.*;
import edu.wpi.zirconium.lettercraze.shared.views.TileView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.Assert.assertFalse;
import static org.testfx.api.FxAssert.verifyThat;

public class LevelScreenTest extends ApplicationTest {
    @Test
    public void testBoard() {
        verifyThat("#board", NodeMatchers.isNotNull());
        verifyThat("#board", NodeMatchers.hasChildren(36, ".board--tile"));
    }

    @Test
    public void testTitle() {
        verifyThat("#title", NodeMatchers.hasText("Mock Level"));
    }

    @Test
    public void selectTile() {
        clickOn(".board--tile");

        verifyThat("#board", NodeMatchers.hasChildren(1, ".selected"));
    }

    @Test
    public void selectWord() {
        TileView A = queryTile(new Point(0,1));
        TileView B = queryTile(new Point(0,2));
        TileView C = queryTile(new Point(0,3));

        clickOn(A);
        clickOn(B);
        clickOn(C);

        verifyThat("#wordPreview", NodeMatchers.hasText(A.getLetter()+B.getLetter()+C.getLetter()));
    }

    private TileView queryTile(Point pos) {
        return lookup((TileView a) -> a != null && a.getPos().equals(pos)).query();
    }

    @Test
    public void makeMove() {
        TileView A = queryTile(new Point(1,0));
        TileView B = queryTile(new Point(2,0));
        TileView C = queryTile(new Point(3,0));

        Word word = new Word(Letter.valueOf(A.getLetter()),
            Letter.valueOf(B.getLetter()), Letter.valueOf(C.getLetter()));

        clickOn(A);
        clickOn(B);
        clickOn(C);

        verifyThat("#submit", NodeMatchers.hasText(new StringContains(word.getScore()+"")));

        clickOn("#submit");

        verifyThat("#previousMovesDisplay", NodeMatchers.hasText(new StringContains(word.asString())));
    }

    @Test
    public void testExit() throws Exception {
        clickOn("#exitLevel");
        assertFalse(targetWindow().isShowing());
    }

    @Override
    public void start(Stage stage) throws Exception {
        LevelScreen ls = new LevelScreen(new Level(6){
            {
                setShape(LevelShape.all(6));
                setTitle("Mock Level");
            }
            @Override
            public boolean isWordValid(String word) {
                return true;
            }
        });
        ls.setExitHandler(stage::hide);
        Scene scene = new Scene(ls, 1024, 712);
        stage.setScene(scene);
        stage.show();
    }
}