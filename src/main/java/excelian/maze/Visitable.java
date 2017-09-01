package excelian.maze;

interface Visitable {
    boolean isBlocked();
    String getVisitableUniqueID();
    int getY();
    int getX();
}
