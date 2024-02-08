package ca.mcmaster.se2aa4.island.team201;
import org.json.JSONObject;

public class Navigator {
    private DroneController droneController;
    private EnvironmentScanner environmentScanner = new EnvironmentScanner();

    public Navigator(DroneController droneController) {
        this.droneController = droneController;
    }

    public JSONObject decideNextAction() {
        // Simplified logic to decide the next action
        return new JSONObject().put("action", "fly");
    }
}

