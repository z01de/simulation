package geometry;

public record Coordinates(int column, int row) {

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Coordinates that)) return false;
        return column == that.column && row == that.row;
    }
    @Override
    public String toString() {
        return row + "," + column;
    }
}