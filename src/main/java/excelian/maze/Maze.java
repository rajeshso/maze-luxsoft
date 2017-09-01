package excelian.maze;

import java.util.Map;

public class Maze {
    private final Map<String, Visitable> mazeMap;

    private Visitable startCell;
    private Visitable endCell;

    public Maze(final Map<String, Visitable> mazeMap, Visitable startCell, Visitable endCell) {
        this.mazeMap = mazeMap;
        this.startCell = startCell;
        this.endCell = endCell;
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
}