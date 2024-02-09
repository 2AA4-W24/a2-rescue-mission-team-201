package ca.mcmaster.se2aa4.island.team201;

import org.json.JSONObject;

public class Navigator {
    private Map map;
    private DroneState droneState;

    public Navigator(Map map, DroneState droneState) {
        this.map = map;
        this.droneState = droneState;
    }

    public JSONObject decideNextAction() {
        // need to add the logic to decide the next action based on the drone's state and the map.
        return new JSONObject().put("action", "fly"); //placeholder
    }

    public void updateMap(JSONObject results) {
        if (results.has("extras")) {
            JSONObject extras = results.getJSONObject("extras");
            if (extras.has("biomes") || extras.has("creeks") || extras.has("sites")) {
                map.updateMapBasedOnScan(droneState.getPosition(), extras);
            }
        }
    }
}
