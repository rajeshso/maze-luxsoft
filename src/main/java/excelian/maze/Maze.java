package excelian.maze;

import java.util.*;

import static excelian.maze.MazeUtils.Direction.*;

public class Maze {
    private static final int MAX_EXPLORERS = 1;
    private final Map<String, Visitable> mazeMap;
    private final Set<Visitor> explorerRegistry;
    private final Visitable startCell;
    private final Visitable endCell;

    public Maze(final Map<String, Visitable> mazeMap, Visitable startCell, Visitable endCell) {
        this.mazeMap = mazeMap;
        this.startCell = startCell;
        this.endCell = endCell;
        explorerRegistry = new HashSet<>(1);
    }

    public boolean permitExplorer(Visitor explorer) {
        if (explorerRegistry.size() == MAX_EXPLORERS) {
            return false;
        }
        explorerRegistry.add(explorer);
        return explorer.visit(getStartCell());
    }

    public List<MazeUtils.Direction> availableNextSteps(Visitor visitor) {
        List<MazeUtils.Direction> resultStringList = new ArrayList<>();
        Visitable currentCell;
        currentCell = getCell(getCurrentCell(visitor));
        if (getUpCell(currentCell) != null) resultStringList.add(UP);
        if (getDownCell(currentCell) != null) resultStringList.add(DOWN);
        if (getLeftCell(currentCell) != null) resultStringList.add(LEFT);
        if (getRightCell(currentCell) != null) resultStringList.add(RIGHT);
        return resultStringList;
    }

    private Visitable getUpCell(Visitable currentCell) {
        String cellIdUp = MazeUtils.makeCellId(currentCell.getX() - 1, currentCell.getY());
        return getOpenCellIfAvailable(cellIdUp);
    }

    private Visitable getDownCell(Visitable currentCell) {
        String cellIdDown = MazeUtils.makeCellId(currentCell.getX() + 1, currentCell.getY());
        return getOpenCellIfAvailable(cellIdDown);
    }

    private Visitable getLeftCell(Visitable currentCell) {
        String cellIdLeft = MazeUtils.makeCellId(currentCell.getX(), currentCell.getY() - 1);
        return getOpenCellIfAvailable(cellIdLeft);
    }

    private Visitable getRightCell(Visitable currentCell) {
        String cellIdRight = MazeUtils.makeCellId(currentCell.getX(), currentCell.getY() + 1);
        return getOpenCellIfAvailable(cellIdRight);
    }

    private Visitable getOpenCellIfAvailable(String cellId) {
        Visitable upCell = this.getCell(cellId);
        if (upCell != null && !upCell.isBlocked()) {
            return upCell;
        }
        return null;
    }

    public boolean moveRight(Visitor visitor) {
        Visitable rightCell = this.getRightCell(getVisitorsCurrentCell(visitor));
        return move(visitor, rightCell);
    }

    public boolean moveLeft(Visitor visitor) {
        Visitable leftCell = this.getLeftCell(getVisitorsCurrentCell(visitor));
        return move(visitor, leftCell);
    }

    public boolean moveUp(Visitor visitor) {
        Visitable upCell = this.getUpCell(getVisitorsCurrentCell(visitor));
        return move(visitor, upCell);
    }

    public boolean moveDown(Visitor visitor) {
        Visitable downCell = this.getDownCell(getVisitorsCurrentCell(visitor));
        return move(visitor, downCell);
    }

    private Visitable getVisitorsCurrentCell(Visitor visitor) {
        return mazeMap.get(visitor.getCurrentCell());
    }

    private boolean move(Visitor visitor, Visitable newCell) {
        boolean visitSuccessful = false;
        if (newCell != null) {
            visitSuccessful = visitor.visit(newCell);
        }
        return visitSuccessful;
    }

    public boolean reachedDestination(Visitor visitor) {
        return visitor.getCurrentCell().equals(endCell.getVisitableUniqueID());
    }

    public void sayByeToVisitor(Visitor visitor) {
        explorerRegistry.remove(visitor);
    }

    public Visitable getCell(String cellId) {
        return mazeMap.get(cellId);
    }

    public Visitable getStartCell() {
        return startCell;
    }

    public Visitable getEndCell() {
        return endCell;
    }

    public Map<String, Visitable> getMazeMap() {
        return mazeMap;
    }

    String getCurrentCell(Visitor visitor) {
        return visitor.getCurrentCell();
    }


}