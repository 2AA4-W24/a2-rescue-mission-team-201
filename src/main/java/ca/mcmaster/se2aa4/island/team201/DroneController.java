package ca.mcmaster.se2aa4.island.team201;

import org.json.JSONObject;

public class DroneController {
    private int batteryLevel;
    private int x, y; // Coordinates
    private String heading;

    // Constructor to set all initial conditions
    public DroneController(int initialBatteryLevel, int x, int y, String heading) {
        this.batteryLevel = initialBatteryLevel;
        this.x = x;
        this.y = y;
        this.heading = heading;
    }

    public void updateState(JSONObject results) {
        // Implementation to update state based on action results
    }

    // Getters for battery level, coordinates, and heading
    public int getBatteryLevel() {
        return batteryLevel;
    }

    public boolean canProceed() {
        return batteryLevel>100;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getHeading() {
        return heading;
    }


}
