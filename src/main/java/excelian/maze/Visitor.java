package excelian.maze;

import java.util.List;

public interface Visitor {
    boolean visit(Visitable maze);

    List<String> getPreviousMoves();

    String getCurrentCell();

    int getExplorerId();
}