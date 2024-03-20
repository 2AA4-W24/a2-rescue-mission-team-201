package ca.mcmaster.se2aa4.island.team201.phases;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team201.ActionExecutor;
import ca.mcmaster.se2aa4.island.team201.AerialPhase;
import ca.mcmaster.se2aa4.island.team201.Coordinate;
import ca.mcmaster.se2aa4.island.team201.Echo;
import ca.mcmaster.se2aa4.island.team201.Interpreter;
import ca.mcmaster.se2aa4.island.team201.Phase;
import ca.mcmaster.se2aa4.island.team201.Action;

public class GoToIsland extends AerialPhase {
    private final Logger logger = LogManager.getLogger();
    int state = 1;
    int initialDistanceFromIsland;
    String directionToIsland;
    Boolean done = false;
    JSONObject result = new JSONObject();

    public GoToIsland(ActionExecutor executor, Interpreter interpreter) {
        super(executor, interpreter);
    }

    public void turnTo(String direction) {
        actionQueue.add(new Action("heading", direction));
    }

    public void initialize(JSONObject info) {
        initialDistanceFromIsland = info.getInt("rangeOfIslandRelativeToDrone");
        directionToIsland = info.getString("directionOfIslandRelativeToDrone");
    }

    public Boolean notFacingIsland() {
        return !interpreter.facing().equals(directionToIsland);
    }

    public void executeState() {
        switch (state) {
            case 1:
                if (notFacingIsland()) {
                    turnTo(directionToIsland);
                }
                state = 2;
                break;
            case 2:
                flyForwardBy(initialDistanceFromIsland);
                state = 3;
                break;
            case 3:
                scan();
                done = true;
                break;
            default:
                break;
        }
    }

    public JSONObject takeDecision() {
        stopDroneIfNoBatteryLeft();

        while (actionQueue.isEmpty()) {
            executeState();
        }

        return nextAction();
    }

    public Boolean done() {
        return done;
    }

    public JSONObject results() {
        return result;
    }
}
