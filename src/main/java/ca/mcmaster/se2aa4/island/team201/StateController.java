package ca.mcmaster.se2aa4.island.team201;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
public class StateController {
    
    private final Logger logger = LogManager.getLogger();
    private LocationTracker locationTracker;
    private Extras extras;
    private Map map;
    private Battery battery;
    private String mostRecentAction;
    private ActionTracker actionTracker; 
    public StateController(ActionTracker actionTracker, LocationTracker locationTracker, Map map, Extras extras, Battery battery){ 
        this.locationTracker = locationTracker;
        this.actionTracker = actionTracker;
        this.extras = extras;
        this.map = map;
        this.battery = battery;
    }

    public void handleStateChange(JSONObject response) {
        if (response.has("cost")) {
            int cost = response.getInt("cost");
            battery.decreaseLevelBy(cost);
        }
        if (response.has("extras")) {
            JSONObject extras = response.getJSONObject("extras");
            if (extras.has("direction")) {
                String newDirection = extras.getString("direction");
                locationTracker.setHeading(newDirection);
            }
        }
    }

    public void fly() {
        locationTracker.moveForward();
        actionTracker.incrementActionsCompleted();
        actionTracker.setLastAction("fly");
    }

    public void handleResults(JSONObject response) {
        if (response.has("cost")) {
            Integer cost = response.getInt("cost");
            logger.info("The cost was {}", cost);
            battery.decreaseLevelBy(cost);
        }
        logger.info("** Response received:\n"+response.toString(2));
        logger.info("response {}", response.toString());

        JSONObject extraInfo = response.getJSONObject("extras");
        extras.updateState(extraInfo);
        //logger.info("Additional information received: {}", extraInfo);
    }
}
