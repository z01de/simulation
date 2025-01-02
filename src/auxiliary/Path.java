package auxiliary;

public class Path implements Comparable<Path> {
    private final Coordinates pathStart;
    private final Coordinates pathEnd;
    private final double pathLength;
    public Path(Coordinates pathStart, Coordinates pathEnd) {
        this.pathStart = pathStart;
        this.pathEnd = pathEnd;
        this.pathLength = Math.sqrt(Math.pow(pathStart.getColumn() - pathEnd.getColumn(),2) + Math.pow(pathStart.getRow() - pathEnd.getRow(),2));
    }

    public Coordinates getPathStart() {
        return pathStart;
    }

    public Coordinates getPathEnd() {
        return pathEnd;
    }

    public double getPathLength() {
        return pathLength;
    }

    @Override
    public int compareTo(Path o) {
        return Double.compare(this.pathLength, o.pathLength);
    }
}
