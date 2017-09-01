package excelian.maze;

import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class MazeCreatorTest {
    private Maze createMaze(String mazeFileStr) {
        File file = new File(new File("").getAbsolutePath() + mazeFileStr);
        Maze maze = null;
        try {
            maze = MazeCreator.createMaze(file);
        } catch (MazeCreationException e) {
            fail("The test case failed - incorrect maze, while using " + mazeFileStr);
        }
        return maze;
    }

    @Test
    public final void testCreateMaze() {
        Maze maze = createMaze("/src/test/resources/TestMaze1.txt");
        assertThat(maze).isNotNull();
    }
}
