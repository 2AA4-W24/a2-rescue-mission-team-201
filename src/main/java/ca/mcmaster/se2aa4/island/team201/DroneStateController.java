package ca.mcmaster.se2aa4.island.team201;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class DroneStateController {
    private final Logger logger = LogManager.getLogger();

    Drone drone;
    BatteryManager batteryManager;
    public DroneStateController(Drone drone) {
        this.drone = drone;
        this.batteryManager = new BatteryManager(drone);
    }
    public void handleStateChange(JSONObject response) {
        handleMapChange();
        handleResults(response);
    }
    private void handleMapChange() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleMapChange'");
    }
    public void handleResults(JSONObject response) {
        Integer cost = response.getInt("cost");
        //logger.info("** Response received:\n"+response.toString(2));
        
        logger.info("The cost was {}", cost);
        batteryManager.useBattery(cost);
        drone.incrementActions();
        JSONObject extraInfo = response.getJSONObject("extras");
        //logger.info("Additional information received: {}", extraInfo);
    }
}
