package ca.mcmaster.se2aa4.island.team201.tactics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team201.BatteryInterpreter;
import ca.mcmaster.se2aa4.island.team201.Drone;
import ca.mcmaster.se2aa4.island.team201.Tactic;

public class IslandFinder implements Tactic {
    private final static Logger logger = LogManager.getLogger();
    BatteryInterpreter battery;
    Drone drone;
    Boolean islandFound;

    public IslandFinder(Drone drone) {
        this.drone = drone;
        this.battery = new BatteryInterpreter(drone);
    }

    // Should be able to find the island
    @Override
    public Boolean tacticComplete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tacticComplete'");
    }

    public JSONObject attemptTo(String action) {
        if (battery.hasEnoughBatteryFor(action)) {
            return createActionObject(action);
        } else {
            return createActionObject("stop");
        }
    }

    public JSONObject attemptTo(String action, String direction) {
        if (battery.hasEnoughBatteryFor(action)) {
            return createActionObject(action, direction);
        } else {
            return createActionObject("stop");
        }
    }

    private JSONObject createActionObject(String action) {

        JSONObject decision = new JSONObject();
        decision.put("action", action);
        return decision;
    }

    private JSONObject createActionObject(String action, String direction) {

        JSONObject decision = new JSONObject();
        decision.put("action", action);
        JSONObject parameters = new JSONObject();
        parameters.put("direction", direction);
        decision.put("parameters", parameters);
        return decision;
    }

    public JSONObject decideAction() {
        int actionNum = drone.getActionCount();
        
        if (actionNum == 25) {
            return attemptTo("heading", "S");
        } else if (actionNum == 27) {
            return attemptTo("echo", "S");
        } else if (actionNum > 37 && actionNum % 2 == 0) {
            return attemptTo("scan");
        } else if (actionNum == 49) {
            return attemptTo("echo","S");
        }else if (actionNum < 110) {
            return createActionObject("fly");
        } else {
            return createActionObject("stop");

        }
    }
}