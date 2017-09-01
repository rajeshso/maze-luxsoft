package excelian.maze;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MovementTest {
    @Test
    public final void testAvailableNextSteps() throws MazeCreationException {
        Maze mazeRight = MazeAndExplorerTest.createMaze("/src/test/resources/TestMaze4.txt");
        Visitor visitorRight = MazeUtils.createExplorer(1);
        mazeRight.permitExplorer(visitorRight);
        List<MazeUtils.Direction> availableStepsList = mazeRight.availableNextSteps(visitorRight);
        assertThat(availableStepsList).hasSize(3);
        assertThat(availableStepsList).containsOnly(MazeUtils.Direction.UP, MazeUtils.Direction.DOWN, MazeUtils.Direction.RIGHT);
    }

    @Test
    public final void testMoveRight() throws MazeCreationException {
        Maze mazeRight = MazeAndExplorerTest.createMaze("/src/test/resources/TestMaze4.txt");
        Visitor visitorRight = MazeUtils.createExplorer(1);
        mazeRight.permitExplorer(visitorRight);
        mazeRight.moveRight(visitorRight);
        List<MazeUtils.Direction> availableStepsList = mazeRight.availableNextSteps(visitorRight);
        assertThat(availableStepsList).hasSize(4);
        assertThat(availableStepsList).containsOnly(MazeUtils.Direction.values());
        mazeRight.moveRight(visitorRight);
        availableStepsList = mazeRight.availableNextSteps(visitorRight);
        assertThat(availableStepsList).hasSize(2);
        assertThat(availableStepsList).containsOnly(MazeUtils.Direction.DOWN, MazeUtils.Direction.LEFT);
    }

    @Test
    public final void testUnableToMoveFurtherRight() throws MazeCreationException {
        Maze mazeRight = MazeAndExplorerTest.createMaze("/src/test/resources/TestMaze4.txt");
        Visitor visitorRight = MazeUtils.createExplorer(1);
        mazeRight.permitExplorer(visitorRight);
        mazeRight.moveRight(visitorRight);
        List<MazeUtils.Direction> availableStepsList = mazeRight.availableNextSteps(visitorRight);
        assertThat(availableStepsList).hasSize(4);
        assertThat(availableStepsList).containsOnly(MazeUtils.Direction.values());
        mazeRight.moveRight(visitorRight);
        availableStepsList = mazeRight.availableNextSteps(visitorRight);
        assertThat(availableStepsList).hasSize(2);
        assertThat(availableStepsList).containsOnly(MazeUtils.Direction.DOWN, MazeUtils.Direction.LEFT);
        String lastEndCellIdBefore = mazeRight.getCurrentCell(visitorRight);
        mazeRight.moveRight(visitorRight);
        String lastEndCellIdAfter = mazeRight.getCurrentCell(visitorRight);
        availableStepsList = mazeRight.availableNextSteps(visitorRight);
        assertThat(availableStepsList).hasSize(2);
        assertThat(availableStepsList).containsOnly(MazeUtils.Direction.DOWN, MazeUtils.Direction.LEFT);
        assertThat(lastEndCellIdBefore).isEqualTo(lastEndCellIdAfter);
    }

    @Test
    public final void testStartToEnd() throws MazeCreationException {
        Maze mazeRight = MazeAndExplorerTest.createMaze("/src/test/resources/TestMaze4.txt");
        Visitor visitorRight = MazeUtils.createExplorer(1);
        mazeRight.permitExplorer(visitorRight);
        mazeRight.moveDown(visitorRight);
        mazeRight.moveRight(visitorRight);
        mazeRight.moveRight(visitorRight);
        mazeRight.moveRight(visitorRight);
        mazeRight.moveDown(visitorRight);
        boolean reachedDestination = mazeRight.reachedDestination(visitorRight);
        assertThat(reachedDestination).isTrue();
        mazeRight.sayByeToVisitor(visitorRight);
    }
}
