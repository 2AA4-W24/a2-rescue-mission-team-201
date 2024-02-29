package ca.mcmaster.se2aa4.island.team201;
import org.json.JSONObject;
public class StateController {
    
    private Location map;
    private Battery battery;

    public StateController(Location map, Battery battery){
        this.map = map;
        this.battery = battery;
    }

    public void handleStateChange(JSONObject response) {
        if (response.has("cost")) {
            int cost = response.getInt("cost");
            battery.decreaseLevel(cost);
        }
        if (response.has("extras")) {
            JSONObject extras = response.getJSONObject("extras");
            if (extras.has("direction")) {
                String newDirection = extras.getString("direction");
            }
        }
    }

    public void fly() {
        map.moveForward();
    }
}
