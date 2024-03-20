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

public class FindIsland extends AerialPhase {
    private final Logger logger = LogManager.getLogger();
    int state = 1;
    String doNotEchoSide = "none";
    Boolean done = false;
    JSONObject result = new JSONObject();
    
    public FindIsland(ActionExecutor executor, Interpreter interpreter) {
        super(executor,interpreter);
    }
    
    public void detectIfDroneIsNextToBorder() {
        Echo[] last2echos = interpreter.lastNumEchos(2);
                    for (int i=0; i < last2echos.length; i++) {
                        if (last2echos[i].range() < 8) {
                            if (i == 0) {
                                doNotEchoSide = "left";
                            } else if (i == 1) {
                                doNotEchoSide = "right";
                            }
                        }
                    }
    }
    public boolean groundSeen() {
        return interpreter.lastEcho().found().equals("GROUND");
    }
    public void echoValidDirections() {
        if (doNotEchoSide.equals("left")) {
            echoRight();
        } else if (doNotEchoSide.equals("right")) {
            echoLeft();
        } else {
            echoLeft();
            echoRight();
        }
    }

    public void executeState() {
        switch (state) {
            case 1:
                echo();
                state = 2;
                break;
            case 2:
                fly();
                echoLeft();
                echoRight();
                state = 3;
                break;
            case 3:
                detectIfDroneIsNextToBorder();
                state = 4;
                break;
            case 4:
                fly();
                echoValidDirections();
                state = 4;
                break;
            default:
                stop();
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
        Echo lastEcho = interpreter.lastEcho();

        if (lastEcho == null) {
            return false;
        }
        
        if (groundSeen()) {
            done = true;
            result.put("rangeOfIslandRelativeToDrone", lastEcho.range());
            result.put("directionOfIslandRelativeToDrone", lastEcho.direction());
        }


        return done;
    }
    public void initialize(JSONObject info) {
        // Does not require info
    }
    public JSONObject results() {
        return result;
    }
}
