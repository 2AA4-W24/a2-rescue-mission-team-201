package ca.mcmaster.se2aa4.island.team201;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class DroneStateController {
    private final Logger logger = LogManager.getLogger();

    Drone drone;
    BatteryInterpreter batteryManager;
    public DroneStateController(Drone drone) {
        this.drone = drone;
        this.batteryManager = new BatteryInterpreter(drone);
    }
    public void handleStateChange(JSONObject response) {
        //handleMapChange();
        handleResults(response);
    }
    private void handleMapChange() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleMapChange'");
    }
    public void useBattery(int cost) {
        int current = drone.getBattery();
        int newBattery = current - cost;
        logger.info("Using {} battery: {} => {}", cost, current, newBattery);
        drone.setBattery(newBattery);
    }
    public void handleAction(JSONObject action) {
        String actionType = action.getString("action");
        if (actionType == "heading") {
            JSONObject parameter  = action.getJSONObject("parameters");
            String direction = parameter.getString("direction");
            drone.changeDirection(direction);
            logger.info("changing the direction to {}", direction);
        }
    }
    public void handleResults(JSONObject response) {
        Integer cost = response.getInt("cost");
        //logger.info("** Response received:\n"+response.toString(2));
        //logger.info("response {}", response.toString());
        //logger.info("The cost was {}", cost);
        useBattery(cost);
        drone.incrementActions();
        JSONObject extraInfo = response.getJSONObject("extras");
        //logger.info("Additional information received: {}", extraInfo);
    }
}
