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
            case "N" -> current = current.north();
            case "E" -> current = current.east();
            case "S" -> current = current.south();
            case "W" -> current = current.west();
            default -> {
            }
        }
    }

    public void setHeading(String newDirection) {
        facing = newDirection;
    }
    public String getDirection() {
        return facing;
    }
    public String getRightDirection() {
        return switch (facing) {
            case "N" -> "E";
            case "E" -> "S";
            case "S" -> "W";
            case "W" -> "N";
            default -> "";
        };
    }
    public String getLeftDirection() {
        return switch (facing) {
            case "N" -> "W";
            case "E" -> "N";
            case "S" -> "E";
            case "W" -> "S";
            default -> "";
        };
    }
    public Coordinate getFront() {
        return switch (facing) {
            case "N" -> current.north();
            case "E" -> current.east();
            case "S" -> current.south();
            case "W" -> current.west();
            default -> current;
        };
    }
    public void turn(String direction) {
        current = getFront();

        setHeading(direction);
        switch (direction) {
            case "N" -> current = current.north();
            case "E" -> current = current.east();
            case "S" -> current = current.south();
            case "W" -> current = current.west();
            default -> {
            }
        }
    }
}
