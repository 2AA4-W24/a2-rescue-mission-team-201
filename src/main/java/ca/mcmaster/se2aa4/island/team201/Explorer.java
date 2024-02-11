package ca.mcmaster.se2aa4.island.team201;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    Drone drone = Drone.getInstance();
    DroneStateController stateController = new DroneStateController(drone);
    DroneNavigator navigator = new DroneNavigator(drone, stateController);
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        drone.setBattery(batteryLevel);
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }
    int actionCount = 0;
    @Override
    public String takeDecision() {
        logger.info("==============START==========");
        return navigator.takeDecision();
        // JSONObject decision = new JSONObject();
        // if (actionCount == 0) {

        //     decision.put("action", "scan");
        // } else {
        //     decision.put("action","stop");
        // }
        // actionCount++; 
        // return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        stateController.handleStateChange(response);
        logger.info("==============END==========");
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
