package geometry;

public record Perpendicular(Coordinates start, Coordinates end) implements Comparable<Perpendicular> {

    public double getLength() {
        return Math.sqrt(Math.pow(start.column() - end.column(),2) + Math.pow(start.row() - end.row(),2));
    }

    @Override
    public int compareTo(Perpendicular other) {
        return Double.compare(this.getLength(), other.getLength());
    }
}
