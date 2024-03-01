package ca.mcmaster.se2aa4.island.team201;

import org.json.JSONObject;

public class ActionExecutor {
    private StateController stateController;

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
    private JSONObject createActionObject(String action, String direction) {

        JSONObject decision = new JSONObject();
        decision.put("action", action);
        JSONObject parameters = new JSONObject();
        parameters.put("direction", direction);
        decision.put("parameters", parameters);
        return decision;
    }
}
