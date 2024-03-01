package ca.mcmaster.se2aa4.island.team201;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LocationTracker {
    private String facing;
    private Coordinate current;
    private final Logger logger = LogManager.getLogger();
    public LocationTracker(String heading) {
        facing = heading;
        current = new Coordinate(0,0);
        logger.info("Coordinates are " + Integer.toString(current.x()) + "," + Integer.toString(current.y()));
    }
    public Coordinate getCurrent() {
        return current;
    }
    public void moveForward() {
        switch (facing) {
            case "N":
                current = current.north();
                break;
            case "E":
                current = current.east();
                break;
            case "S":
                current = current.south();
                break;
            case "W":
                current = current.west();
                break;
            default:
                break;
        }
    }

    public void setHeading(String newDirection) {
        facing = newDirection;
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
    public void turn(String direction) {
        setHeading(direction);
        current = getFront();

        switch (direction) {
            case "N":
                current = current.north();
                break;
            case "E":
                current = current.east();
                break;
            case "S":
                current = current.south();
                break;
            case "W":
                current = current.west();
                break;
            default:
                break;
        }
    }
    
}
