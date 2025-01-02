package auxiliary;

import java.util.Objects;

public class Coordinates {
    private int column;
    private int row;

    public Coordinates(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public Coordinates getCoordinates() {
        return new Coordinates(column, row);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Coordinates that)) return false;
        return column == that.column && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return "[" + row + ";" + column + "]";
    }
}
