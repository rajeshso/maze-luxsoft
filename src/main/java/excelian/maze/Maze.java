package excelian.maze;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    public String getCurrentCell(Visitor visitor) {
        return visitor.getCurrentCell();
    }


}