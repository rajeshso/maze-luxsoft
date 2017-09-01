package excelian.maze;

import java.util.Map;

public class Maze {
    private final Map<String, Visitable> mazeMap;

    private Visitable startCell;
    private Visitable endCell;

    public Maze(final Map<String, Visitable> mazeMap) {
        this.mazeMap = mazeMap;
    }

    public Maze(final Map<String, Visitable> mazeMap, Visitable startCell, Visitable endCell) {
        this.mazeMap = mazeMap;
        this.startCell = startCell;
        this.endCell = endCell;
    }
}