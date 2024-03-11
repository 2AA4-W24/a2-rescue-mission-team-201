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

public class FindIsland implements Phase {
    private final Logger logger = LogManager.getLogger();
    int actionCount =0;
    ActionExecutor executor; 
    int state = 1;
    String doNotEchoSide = "none";
    String actionLog = "";
    int mapWidth = 0;
    String initialDirection;
    Boolean done = false;
    JSONObject result = new JSONObject();
    Interpreter interpreter;
    Queue<Action> actionQueue = new LinkedList<Action>();
    public FindIsland(ActionExecutor executor, Interpreter interpreter) {
        this.executor = executor;
        this.interpreter = interpreter;
        initialDirection = interpreter.facing();
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
            actionQueue.add(new Action("scan"));
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
    public JSONObject takeDecision() {
        logger.info("action number {}", interpreter.numberOfActions());
        logger.info("battery {}", interpreter.getBattery());
        if (interpreter.getBattery() < 50 || interpreter.numberOfActions() > 1250) {
            logger.info(actionLog);
            return executor.stop();

        }
        while (actionQueue.isEmpty()) {
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
                    state = 4;
                    break;
                case 4:
                    fly();
                    if (doNotEchoSide.equals("left")) {
                        echoRight();
                    } else if (doNotEchoSide.equals("right")) {
                        echoLeft();
                    } else {
                        echoLeft();
                        echoRight();
                    }
                    state = 4;
                    break;
                default:
                    stop();
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
        Echo lastEcho = interpreter.lastEcho();
        if (lastEcho == null) {
            return false;
        }
        logger.info("i found the {}", lastEcho.found());
        if (lastEcho.found().equals("GROUND")) {
            done = true;
            result.put("rangeOfIslandRelativeToDrone", lastEcho.range());
            result.put("directionOfIslandRelativeToDrone", lastEcho.direction());
            result.put("initialDirection", initialDirection);
        }


        return done;
    }
    public void setInfoNeeded(JSONObject info) {
        // Does not require info
    }
    public JSONObject results() {
        return result;
    }
}
