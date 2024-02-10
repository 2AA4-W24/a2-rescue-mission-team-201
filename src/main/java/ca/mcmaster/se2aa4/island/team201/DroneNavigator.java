package ca.mcmaster.se2aa4.island.team201;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team201.tactics.IslandFinder;

public class DroneNavigator {
    private final Logger logger = LogManager.getLogger();
    Drone drone;
    String lastAction;
    Tactic tactic;
    public DroneNavigator(Drone drone) {
        this.drone = drone;
        this.tactic = new IslandFinder(drone);
    }
    
    public String takeDecision() {
        JSONObject decision = new JSONObject();

        String action = tactic.decideAction();
        lastAction = action;
        logger.info("Taking action #{}: {}",drone.getActionCount(),action);
        decision.put("action", action); 
        return decision.toString();
    }
}
