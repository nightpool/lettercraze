package edu.wpi.zirconium.lettercraze.builder.views;

import edu.wpi.zirconium.lettercraze.entities.*;
import edu.wpi.zirconium.lettercraze.player.views.LevelScreen;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.testfx.api.FxAssert.verifyThat;

@RunWith(Enclosed.class)
public class BuilderScreenTest {

    public static class BuilderLevelTest extends ApplicationTest {

        private Level level;

        @Test
        public void testPreview() {
            clickOn("#previewButton");
            verifyThat(window(w -> w != null && w.getScene().getRoot() instanceof LevelScreen), new CustomTypeSafeMatcher<Window>("is showing") {
                @Override
                protected boolean matchesSafely(Window window) {
                    return window.isShowing();
                }
            });
            interact(() -> ((Stage)window(1)).close());
        }

        @Test
        public void testShape() {
            verifyThat("#board", NodeMatchers.hasChildren(36, ".board--tile"));
            clickOn(lookup(".board--tile").nth(0).<Node>query());
            clickOn(lookup(".board--tile").nth(1).<Node>query());
            clickOn(lookup(".board--tile").nth(2).<Node>query());
            clickOn(lookup(".board--tile").nth(3).<Node>query());
            clickOn(lookup(".board--tile").nth(4).<Node>query());
            assertThat(level.getShape().unblockedPoints().collect(Collectors.toList()), Matchers.hasSize(31));
        }

        @Test
        public void makeTheme() {
            clickOn(NodeMatchers.hasText("Theme"));
            verifyThat("#themeWords", NodeMatchers.isVisible());
        }

        @Test
        public void makeLightning() {
            clickOn(NodeMatchers.hasText("Lightning"));
            verifyThat("#lightningTime", NodeMatchers.isVisible());
        }

        @Override
        public void start(Stage stage) throws Exception {
            level = new Level(6) {
                {
                    setTitle("Mock Level");
                    setShape(LevelShape.all(6));
                }
            };
            BuilderScreen builder = new BuilderScreen(level);
            Scene scene = new Scene(builder, 1024, 712);
            stage.setScene(scene);
            stage.show();
        }
    }


    public static class BuilderPuzzleTest extends ApplicationTest {

        private PuzzleLevel puzzleLevel;

        @Test
        public void testBoard() throws Exception {
            verifyThat("#board", NodeMatchers.isNotNull());
            verifyThat("#board", NodeMatchers.hasChildren(36, ".board--tile"));
        }

        @Test
        public void testPuzzle() throws Exception {
            verifyThat("#puzzleWords", NodeMatchers.hasText("10"));
            interact(() -> lookup("#puzzleWords").<TextField>query().setText("15"));
            assertEquals(15, puzzleLevel.getWordLimit());
        }

        @Test
        public void testThresholds() throws Exception {
            verifyThat("#puzzleStar1", NodeMatchers.hasText("1"));
            verifyThat("#puzzleStar2", NodeMatchers.hasText("2"));
            verifyThat("#puzzleStar3", NodeMatchers.hasText("3"));
        }

        @Test
        public void testInvalid() throws Exception {
            verifyThat("#puzzleStar1", NodeMatchers.hasText("1"));
            interact(() -> lookup("#puzzleStar1").<TextField>query().setText(""));
            assertEquals(1, puzzleLevel.getThreshold(0));
            verifyThat("#puzzleStar1.invalid", NodeMatchers.isNotNull());

            interact(() -> lookup("#puzzleStar2").<TextField>query().setText("abc"));
            assertEquals(2, puzzleLevel.getThreshold(1));
            interact(() -> lookup("#puzzleStar3").<TextField>query().setText("---"));
            assertEquals(3, puzzleLevel.getThreshold(2));
        }

        @Override
        public void start(Stage stage) throws Exception {
            puzzleLevel = new PuzzleLevel(6) {
                {
                    setShape(LevelShape.all(6));
                    setTitle("Mock Level");
                    setWordLimit(10);
                }
            };
            BuilderScreen builder = new BuilderScreen(puzzleLevel);
            Scene scene = new Scene(builder, 1024, 712);
            stage.setScene(scene);
            stage.show();
        }
    }

    public static class BuilderLightningTest extends ApplicationTest {
        @Test
        public void testLightning() throws Exception {
            verifyThat("#lightningTime", NodeMatchers.hasText("10"));
        }

        @Override
        public void start(Stage stage) throws Exception {
            BuilderScreen builder = new BuilderScreen(new LightningLevel(6) {
                {
                    setShape(LevelShape.all(6));
                    setTitle("Mock Level");
                    setMaxTime(10);
                }
            });
            Scene scene = new Scene(builder, 1024, 712);
            stage.setScene(scene);
            stage.show();
        }
    }

    public static class BuilderThemeTest extends ApplicationTest {

        private ThemeLevel themeLevel;

        @Test
        public void testTheme() throws Exception {
            verifyThat("#themeWords", NodeMatchers.hasText(new StringContains("alpha")));
            verifyThat("#themeWords", NodeMatchers.hasText(new StringContains("bravo")));
            verifyThat("#themeWords", NodeMatchers.hasText(new StringContains("charlie")));
        }

        @Test
        public void modWords() throws Exception {
            interact(() -> {
                TextArea words = lookup("#themeWords").<TextArea>query();
                String text = words.getText();
                words.setText(text + "\ndelta");
                assertThat(themeLevel.getWords(), Matchers.hasSize(4));
                assertThat(themeLevel.getWords(), Matchers.hasItem("delta"));
            });
        }

        @Override
        public void start(Stage stage) throws Exception {
            themeLevel = new ThemeLevel(6) {
                {
                    setShape(LevelShape.all(6));
                    setTitle("Mock Level");
                    setWords(Arrays.asList("alpha", "bravo", "charlie"));
                }
            };
            BuilderScreen builder = new BuilderScreen(themeLevel);
            Scene scene = new Scene(builder, 1024, 712);
            stage.setScene(scene);
            stage.show();
        }
    }
}