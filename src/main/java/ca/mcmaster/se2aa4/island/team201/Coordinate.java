package ca.mcmaster.se2aa4.island.team201;

public class Coordinate {
    private int x; 
    private int y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate north() {
        return new Coordinate(x,y+1);
    }
    public Coordinate east() {
        return new Coordinate(x+1,y);
    }
    public Coordinate south() {
        return new Coordinate(x,y-1);
    }
    public Coordinate west() {
        return new Coordinate(x-1,y);
    }
}
