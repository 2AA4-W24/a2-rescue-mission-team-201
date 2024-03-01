package ca.mcmaster.se2aa4.island.team201;

public class LocationTracker {

    private String facing;
    private Coordinate current;

    public Coordinate getCurrent() {
        return current;
    }
    public void moveForward() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveForward'");
    }

    public void setHeading(String newDirection) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setHeading'");
    }
    public String getDirection() {
        return facing;
    }
    public String getRightDirection() {
        switch (facing) {
            
            case "N":
                return "E";
            case "E":
                return "S";
            case "S":
                return "W";
            case "W":
                return "N";
            default:
                return "";
        }
    }
    public String getLeftDirection() {
        switch (facing) {
            
            case "N":
                return "W";
            case "E":
                return "N";
            case "S":
                return "E";
            case "W":
                return "S";
            default:
                return "";
        }
    }
    public Coordinate getFront() {
        switch (facing) {
            
            case "N":
                return current.north();
            case "E":
                return current.east();
            case "S":
                return current.south();
            case "W":
                return current.west();
            default:
                return current;
        }
    }
    
}
