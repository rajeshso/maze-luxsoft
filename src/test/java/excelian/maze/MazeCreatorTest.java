package excelian.maze;

import org.junit.Test;

import java.io.File;
import java.util.Map;

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
    public final void testNegativeCreateMazeWithNoStart() {
        try {
            createMaze("/src/test/resources/TestIncorrectMaze5.txt");
            fail("Exception expected frz");
        } catch (MazeCreationException e) {
            assertThat(e.getMessage()).isEqualTo("Starting position is not mentioned in the Maze");
        }
    }
    @Test
    public final void testNegativeCreateMazeWithNoEnd() {
        try {
            createMaze("/src/test/resources/TestIncorrectMaze6.txt");
            fail("The test case failed for incorrect file");
        } catch (MazeCreationException e) {
            assertThat(e.getMessage()).isEqualTo("Finishing position is not mentioned in the Maze");
        }
    }

    @Test
    public final void testCreateMazeSetWithStartEndCells() throws MazeCreationException {
        Maze maze = createMaze("/src/test/resources/TestMaze2.txt");
        assertThat(maze.getStartCell()).isNotNull();
        assertThat(maze.getEndCell()).isNotNull();
        assertThat(maze.getStartCell().getVisitableUniqueID()).isEqualTo("3-4");
        assertThat(maze.getEndCell().getVisitableUniqueID()).isEqualTo("6-48");
    }

    @Test
    public final void testCreateCells() throws MazeCreationException {
        Maze maze = null;
        maze = createMaze("/src/main/resources/ExampleMaze.txt");
        Map<String, Visitable> cellMap = maze.getMazeMap();
        assertThat(cellMap.size()).isEqualTo(225);
        assertThat(cellMap.get("1-1").isBlocked()).isEqualTo(true);
        assertThat(cellMap.get("2-2").isBlocked()).isEqualTo(false);
        assertThat(cellMap.get("4-10").isBlocked()).isEqualTo(false);
        assertThat(cellMap.get("4-13").isBlocked()).isEqualTo(true);
    }
}
