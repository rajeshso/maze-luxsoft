package excelian.maze;

public class Cell implements Visitable {

    private final String cellId;

    private final boolean blocked;

    private final int x;

    private final int y;

    public Cell(String cellId, int x, int y, boolean blocked) {
        this.cellId = cellId;
        this.x = x;
        this.y = y;
        this.blocked = blocked;
    }

    @Override
    public String getVisitableUniqueID() {
        return cellId;
    }

    @Override
    public boolean isBlocked() {
        return blocked;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cell ");
        builder.append(" x axis position is ");
        builder.append(x);
        builder.append(" y axis position is ");
        builder.append(y);
        builder.append(" .The cell is ");
        if (isBlocked())
            builder.append(" blocked");
        else
            builder.append(" empty");
        return builder.toString();
    }
}
