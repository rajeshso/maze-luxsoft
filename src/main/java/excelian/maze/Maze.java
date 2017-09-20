package excelian.maze;

import java.util.*;

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
        fillNavigationInCells();
    }

    public boolean permitExplorer(Visitor explorer) {
        if (explorerRegistry.size() == MAX_EXPLORERS) {
            return false;
        }
        explorerRegistry.add(explorer);
        return explorer.visit(getStartCell());
    }

    public List<MazeUtils.Direction> availableNextSteps(Visitor visitor) {
        Visitable currentCell= getCell(getCurrentCell(visitor));
        return currentCell.availableNextSteps();
    }

    public boolean moveRight(Visitor visitor) {
        return move(visitor, getVisitorsCurrentCell(visitor).getRight());
    }

    public boolean moveLeft(Visitor visitor) {
        return move(visitor, getVisitorsCurrentCell(visitor).getLeft());
    }

    public boolean moveUp(Visitor visitor) {
        return move(visitor, getVisitorsCurrentCell(visitor).getUp());
    }

    public boolean moveDown(Visitor visitor) {
        return move(visitor, getVisitorsCurrentCell(visitor).getDown());
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

    private void fillNavigationInCells() {
        mazeMap.values().forEach(visitable -> {
            visitable.setDown(this.getDownCell(visitable)); visitable.setLeft(this.getLeftCell(visitable));
            visitable.setUp(this.getUpCell(visitable)); visitable.setRight(this.getRightCell(visitable));
        } );
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
}