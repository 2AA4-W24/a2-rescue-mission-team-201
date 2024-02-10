package ca.mcmaster.se2aa4.island.team201;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class DroneNavigator {
    private final Logger logger = LogManager.getLogger();
    Drone drone;
    String lastAction;
    BatteryManager battery;
    public DroneNavigator(Drone drone) {
        this.drone = drone;
        this.battery = new BatteryManager(drone);
    }
    public String decideAction() {
        int actionNum = drone.getActionCount();
        logger.info("action num is " + Integer.toString(actionNum));
        if (actionNum < 10) {
            if (battery.hasEnoughBatteryFor("fly")) {
                return "fly";
            } else {
                return "stop";
            }
        } else {
            return "stop";
        }
    }
    public String takeDecision() {
        JSONObject decision = new JSONObject();

        String action = decideAction();
        lastAction = action;
        logger.info("Taking action #{}: {}",drone.getActionCount(),action);
        decision.put("action", action); 
        return decision.toString();
    }
}
