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
import ca.mcmaster.se2aa4.island.team201.Map;
import ca.mcmaster.se2aa4.island.team201.Phase;
import ca.mcmaster.se2aa4.island.team201.Action;

public class BasicGridSearch extends AerialPhase {
    private final Logger logger = LogManager.getLogger();
    ActionExecutor executor;
    int state = 1;
    String directionToTurn;

    Boolean done = false;
    int mapWidth = 0;
    JSONObject result = new JSONObject();

    public BasicGridSearch(ActionExecutor executor, Interpreter interpreter) {
        super(executor, interpreter);
    }

    public void initialize(JSONObject info) {
        interpreter.setDirectionOfLines(interpreter.facing());
    }

    public void turnAroundInOppositeDirection() {
        if (directionToTurn.equals("left")) {
            turnRight();
            fly();
            turnRight();
        } else if (directionToTurn.equals("right")) {
            turnLeft();
            fly();
            turnLeft();
        }
    }

    public void turnAround() {
        if (directionToTurn != null && directionToTurn.equals("left")) {
            logger.info("turns left");
            turnLeft();
            turnLeft();
            directionToTurn = "right";
            // If direction to turn is right or its the first turn
        } else {
            logger.info("does this RIGHT");
            turnRight();
            turnRight();
            directionToTurn = "left";

        }
    }

    public void flyForwardUntilGround() {
        flyForwardBy(interpreter.lastEcho().range() + 1);
    }

    public void setLineAsSearched() {
        interpreter.setLineSearched(interpreter.getCurrent());
    }

    public void checkIfGridSearchIsDone() {
        Coordinate current = interpreter.getCurrent();
        if (interpreter.hasAlreadySearched(current)) {
            done = true;
        }
    }

    public Boolean lastScanHadLand() {
        return interpreter.lastScanHadLand();
    }

    public boolean groundSeen() {
        return interpreter.lastEcho().found().equals("GROUND");
    }

    public void stopDroneIfGridSearchDone() {
        if (done == true) {
            actionQueue.clear();
            stop();
        }
    }

    public void checkForLandInDirectionOfIsland() {
        if (directionToTurn.equals("left")) {
            fly();
            fly();
            fly();
            echoRight();
        } else if (directionToTurn.equals("right")) {
            fly();
            fly();
            fly();
            echoLeft();
        }
    }

    public void turnAroundIfLandIsntNextToDrone() {
        Echo lastEchoState7 = interpreter.lastEcho();
        if (lastEchoState7.range() > 2) {
            turnAroundInOppositeDirection();
            echo();
            state = 5;
        } else {
            state = 6;
        }
    }

    public void executeState() {
        switch (state) {
            case 1:
                scan();
                checkIfGridSearchIsDone();
                state = 2;
                break;
            case 2:
                if (lastScanHadLand()) {
                    fly();
                    state = 1;
                } else {
                    echo();
                    state = 4;
                }
                break;
            case 4:
                if (groundSeen()) {
                    flyForwardUntilGround();
                    state = 1;
                } else {
                    turnAround();
                    echo();
                    setLineAsSearched();
                    state = 5;
                }
                break;
            case 5:
                if (groundSeen()) {
                    flyForwardUntilGround();
                    state = 1;
                } else {
                    state = 6;
                }
                break;
            case 6:
                checkForLandInDirectionOfIsland();
                state = 7;
                break;
            case 7:
                turnAroundIfLandIsntNextToDrone();
                break;
            default:
                break;
        }
    }

    public JSONObject takeDecision() {
        stopDroneIfNoBatteryLeft();
        stopDroneIfGridSearchDone();

        while (actionQueue.isEmpty()) {
            
            executeState();
        }

        if (actionQueue.isEmpty()) {
            logger.info("empty");
        }
        Coordinate current = interpreter.getCurrent();

        logger.info("Coordinates (based on action queue): {} {}", current.x(), current.y());

        return nextAction();
    }

    public Boolean done() {
        return done;
    }

    public JSONObject results() {
        return result;
    }
}
