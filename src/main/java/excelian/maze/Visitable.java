package excelian.maze;

import java.util.List;

interface Visitable {
    boolean isBlocked();

    String getVisitableUniqueID();

    int getY();

    int getX();

    Visitable getLeft();

    void setLeft(Visitable left);

    Visitable getRight();

    void setRight(Visitable right);

    Visitable getUp();

    void setUp(Visitable up);

    Visitable getDown();

    void setDown(Visitable down);

    List<MazeUtils.Direction> availableNextSteps();
}
