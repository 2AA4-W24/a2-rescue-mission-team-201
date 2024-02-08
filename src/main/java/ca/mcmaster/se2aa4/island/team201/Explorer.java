package ca.mcmaster.se2aa4.island.team201;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();

    private DroneController droneController;
    private final Navigator navigator = new Navigator(droneController);
    @Override
    public void initialize(String s) {
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        int batteryLevel = info.getInt("budget");
        int x = info.getInt("x");
        int y = info.getInt("y");
        String heading = info.getString("heading");

        // Directly constructing DroneController with initial properties
        this.droneController = new DroneController(batteryLevel, x, y, heading);

        logger.info("** Initializing with Heading: {}, Battery Level: {}, Coordinates: ({}, {})", heading, batteryLevel, x, y);
    }


    @Override
    public String takeDecision() {
        if (droneController.canProceed()) {
            JSONObject decision = navigator.decideNextAction();
            // Log the decision taken
            logger.info("** Decision taken: {}", decision.toString());
            return decision.toString();
        } else {
            // Log the decision to stop due to battery constraints
            logger.info("** Decision taken: stop (Battery constraints)");
            return new JSONObject().put("action", "stop").toString();
        }
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject results = new JSONObject(new JSONTokener(new StringReader(s)));
        // Update state based on results and log the action's outcome
        droneController.updateState(results);
        logger.info("** Response received: {}", results.toString(2));
        logger.info("** Action cost: {}, Remaining battery: {}", results.getInt("cost"), droneController.getBatteryLevel());
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
