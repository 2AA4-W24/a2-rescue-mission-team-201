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
    DroneStateController state;
    public DroneNavigator(Drone drone, DroneStateController state) {
        this.drone = drone;
        this.state = state;
        this.tactic = new IslandFinder(drone);
    }
    
    public String takeDecision() {
        JSONObject action = tactic.decideAction();
        state.handleAction(action);
        logger.info("Taking action #{}: {}",drone.getActionCount(),action.getString("action"));
        
        return action.toString();
    }
}
