package ca.mcmaster.se2aa4.island.team201.phases;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team201.ActionExecutor;
import ca.mcmaster.se2aa4.island.team201.Coordinate;
import ca.mcmaster.se2aa4.island.team201.Echo;
import ca.mcmaster.se2aa4.island.team201.Interpreter;
import ca.mcmaster.se2aa4.island.team201.Phase;
import ca.mcmaster.se2aa4.island.team201.Action;

public class BasicGridSearch implements Phase {
    private final Logger logger = LogManager.getLogger();
    int actionCount =0;
    ActionExecutor executor; 
    int state = 1;
    int initialDistanceFromIsland;
    String directionToIsland; 
    String initialDirection;
    String doNotEchoSide = "none";
    String actionLog = "";
    int mapWidth = 0;
    Boolean done = false;
    JSONObject result = new JSONObject();
    Interpreter interpreter;
    Queue<JSONObject> actionQueue = new LinkedList<JSONObject>();
    public BasicGridSearch(ActionExecutor executor, Interpreter interpreter) {
        this.executor = executor;
        this.interpreter = interpreter;
    }
    public JSONObject echoRight() {
        String right = interpreter.getRightDirection();
        return executor.echo(right);
    }
    public JSONObject echo() {
        String front = interpreter.facing();
        return executor.echo(front);
    }
    public JSONObject turnRight() {
        String right = interpreter.getRightDirection();
        return executor.turn(right);
    }
    public JSONObject turnLeft() {
        String left = interpreter.getLeftDirection();

        return executor.turn(left);
    }
    public JSONObject echoLeft() {
        String left = interpreter.getLeftDirection();
        return executor.echo(left);
    }
    public void flyForwardBy(int blocks) {
        for (int i=0; i<blocks; i++) {
            actionQueue.add(executor.fly());
        }
    }
    public void setInfoNeeded(JSONObject info) {
        initialDirection = info.getString("initialDirection");
    } 
    public JSONObject takeDecision() {
        logger.info(interpreter.numberOfActions());
        logger.info("battery {}", interpreter.getBattery());
        if (interpreter.getBattery() < 50 || interpreter.numberOfActions() > 1220) {
            logger.info(actionLog);
            return executor.stop();
        }
        while (actionQueue.isEmpty()) {
            switch (state) {
                case 1:
                logger.info("the intiial direction was " + initialDirection);
                    actionQueue.add(executor.scan());
                    state = 2;
                    break;
                case 2:
                logger.info("the last scan biome is " + interpreter.lastScan().biomes()[0]);    
                actionQueue.add(executor.stop());
                break;
                default: 
                    break;
            }
        }

        if (actionQueue.isEmpty()) {
            logger.info("empty");
        }
        logger.info(actionLog);
        Coordinate current =  interpreter.getCurrent();
        logger.info("Coordinates (based on action queue): {} {}", current.x(), current.y());
        JSONObject actionToDo = actionQueue.remove();
        logger.info("doing {}", actionToDo.getString("action"));
        return actionToDo;
    }

    public Boolean done() {
        return done;
    }
    public JSONObject results() {
        return result;
    }
}
