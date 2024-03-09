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
    private ActionTracker actionTracker; 
    public StateController(ActionTracker actionTracker, LocationTracker locationTracker, Map map, Extras extras, Battery battery){ 
        this.locationTracker = locationTracker;
        this.actionTracker = actionTracker;
        this.battery = battery;
        this.map = map;
        this.extras = extras;
    }
    public void fly() {
        locationTracker.moveForward();
        actionTracker.incrementActionsCompleted();
        actionTracker.setLastAction("fly");
    }
    public void echo(String direction) {
        logger.info("here");
        actionTracker.incrementActionsCompleted();
        actionTracker.setLastAction("echo",direction);
    }
    public void turn(String direction) {
        locationTracker.turn(direction);
        actionTracker.incrementActionsCompleted();
        actionTracker.setLastAction("heading",direction);
    }
    public void scan() {
        actionTracker.incrementActionsCompleted();
        actionTracker.setLastAction("scan");
    }

    public void handleResults(JSONObject response) {
        if (response.has("cost")) {
            Integer cost = response.getInt("cost");
            logger.info("The cost was {}", cost);
            battery.decreaseLevelBy(cost);
        }
        //logger.info("** Response received:\n"+response.toString(2));
        //logger.info("response {}", response.toString());

        JSONObject extraInfo = response.getJSONObject("extras");
        if (!(extraInfo.length() == 0)) {
            extras.updateState(extraInfo, actionTracker.lastAction());
        }
        //logger.info("Additional information received: {}", extraInfo);
    }

}
