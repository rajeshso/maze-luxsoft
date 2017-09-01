package excelian.maze;

import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class MazeCreatorTest {
    private Maze createMaze(String mazeFileStr) throws MazeCreationException {
        File file = new File(new File("").getAbsolutePath() + mazeFileStr);
        return MazeCreator.createMaze(file);
    }

    @Test
    public final void testCreateMaze() throws MazeCreationException {
        Maze maze = createMaze("/src/test/resources/TestMaze1.txt");
        assertThat(maze).isNotNull();
    }

    @Test
    public final void testCreateMazeWithCellMap() throws MazeCreationException {
        Maze maze = createMaze("/src/test/resources/TestMaze2.txt");
        assertThat(maze.getCell("2-10")).isNotNull();
    }

    @Test
    public final void testNegativeCreateMazeWithCellMap() throws MazeCreationException {
        Maze maze = createMaze("/src/test/resources/TestMaze2.txt");
        assertThat(maze.getCell("200-1000")).isNull();
    }

    @Test
    public final void testNegativeCreateMazeWithNoStart() {// throws MazeCreationException {
        try {
            createMaze("/src/test/resources/TestIncorrectMaze5.txt");
            fail("Exception expected frz");
        } catch (MazeCreationException e) {
            assertThat(e.getMessage()).isEqualTo("Starting position is not mentioned in the Maze");
        }
    }
}
