package ca.mcmaster.se2aa4.island.team201;
import org.json.JSONObject;

public class DroneState {
    private Position position;
    private String heading;
    private int batteryLevel;

    // Constructor
    public DroneState(Position position, String heading, int batteryLevel) {
        this.position = position;
        this.heading = heading;
        this.batteryLevel = batteryLevel;
    }

    public void updateState(JSONObject results) {
        int cost = results.getInt("cost");
        this.batteryLevel -= cost; // Deduct the cost from the battery level

        String status = results.getString("status");
        if ("OK".equals(status)) {
            if (results.has("extras")) {
                JSONObject extras = results.getJSONObject("extras");
                // Update heading if the action was 'heading'
                if (extras.has("newHeading")) {
                    this.heading = extras.getString("newHeading");
                }
                // The position update would need specific logic not shown here
            }
        }
    }

    // Getters
    public Position getPosition() {
        return position;
    }

    public String getHeading() {
        return heading;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }


}
