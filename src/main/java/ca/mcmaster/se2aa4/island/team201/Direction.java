package ca.mcmaster.se2aa4.island.team201;

public class Direction {
    private String heading;

    public Direction(String heading) {
        this.heading = heading;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String newDirection){
        this.heading = newDirection;
    }
};
