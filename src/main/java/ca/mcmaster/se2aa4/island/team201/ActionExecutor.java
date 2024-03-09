package ca.mcmaster.se2aa4.island.team201;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class ActionExecutor {
    private final Logger logger = LogManager.getLogger();
  
    private StateController stateController;
    private LocationTracker locationTracker;
    public ActionExecutor(StateController stateController){
        this.stateController = stateController;
    }

    public String takeAction() {
        return "{action}";
    }
    private JSONObject createActionObject(String action) {

        JSONObject decision = new JSONObject();
        decision.put("action", action);
        return decision;
    }
    public JSONObject stop() {
        return createActionObject("stop");
    }
    public JSONObject fly() {
        stateController.fly();
        return createActionObject("fly");
    }
    public JSONObject turn(String direction) {
        stateController.turn(direction);
        return createActionObject("heading",direction);
    }
    public JSONObject echo(String direction) {
        stateController.echo(direction);
        return createActionObject("echo", direction);
    }
    public JSONObject scan() {
        stateController.scan();
        return createActionObject("scan");
    }
    private JSONObject createActionObject(String action, String direction) {

        JSONObject decision = new JSONObject();
        decision.put("action", action);
        JSONObject parameters = new JSONObject();
        parameters.put("direction", direction);
        decision.put("parameters", parameters);
        return decision;
    }
}
