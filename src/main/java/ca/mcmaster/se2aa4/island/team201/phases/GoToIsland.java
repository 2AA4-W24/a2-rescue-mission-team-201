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

public class GoToIsland implements Phase {
    private final Logger logger = LogManager.getLogger();
    int actionCount =0;
    ActionExecutor executor; 
    int state = 1;
    int initialDistanceFromIsland;
    String directionToIsland; 
    String doNotEchoSide = "none";
    String actionLog = "";
    int mapWidth = 0;
    Boolean done = false;
    JSONObject result = new JSONObject();
    Interpreter interpreter;
    Queue<Action> actionQueue = new LinkedList<Action>();
    public GoToIsland(ActionExecutor executor, Interpreter interpreter) {
        this.executor = executor;
        this.interpreter = interpreter;
    }
    public void echoRight() {
        String right = interpreter.getRightDirection();
        actionQueue.add(new Action("echo","right"));
    }
    public void echo() {
        String front = interpreter.facing();
        actionQueue.add(new Action("echo","front"));
    }
    public void turnRight() {
        String right = interpreter.getRightDirection();
        actionQueue.add(new Action("heading","right"));
    }
    public void turnTo(String direction) {
        actionQueue.add(new Action("heading",direction));
    }
    public void turnLeft() {
        String left = interpreter.getLeftDirection();

        actionQueue.add(new Action("heading","left"));
    }
    public Action addDirection(Action action) {
        Action actionWithDirection;
        String direction = action.direction();
        switch (direction) {
            case "left":
                actionWithDirection = new Action(action.name(), interpreter.getLeftDirection());
                break;
            case "right":
                actionWithDirection = new Action(action.name(), interpreter.getRightDirection());
                break;
            case "front":
                actionWithDirection = new Action(action.name(), interpreter.facing());
                break;
            case "none":
                actionWithDirection = action;
                break;
            default: 
                actionWithDirection = action;
                break;
        }

        return actionWithDirection;
    }
    public void scan() {
        actionQueue.add(new Action("scan"));
    }
    public void echoLeft() {
        String left = interpreter.getLeftDirection();
        actionQueue.add(new Action("echo","left"));
    }
    public void fly() {
        actionQueue.add(new Action("fly"));
    }
    public void flyForwardBy(int blocks) {
        for (int i=0; i<blocks; i++) {
            actionQueue.add(new Action("fly"));
        }
    }
    public void stop() {
        actionQueue.add(new Action("stop"));
    }
    public JSONObject nextAction() {
        Action actionToDo = actionQueue.remove();
        Action actionWithDirection = addDirection(actionToDo);
        
        logger.info("doing {}", actionToDo.name());
        return executor.execute(actionWithDirection);
    }
    public void setInfoNeeded(JSONObject info) {
        initialDistanceFromIsland = info.getInt("rangeOfIslandRelativeToDrone");
        directionToIsland = info.getString("directionOfIslandRelativeToDrone");
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
                // Turn to the island direction
                if (!interpreter.facing().equals(directionToIsland)) {

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

        if (actionQueue.isEmpty()) {
            logger.info("empty");
        }
        logger.info(actionLog);
        Coordinate current =  interpreter.getCurrent();
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
