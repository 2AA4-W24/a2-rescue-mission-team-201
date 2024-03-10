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
    ActionExecutor executor; 
    int state = 1;
    int initialDistanceFromIsland;
    String directionToIsland; 
    String initialDirection;
    String directionToTurn;
    String doNotEchoSide = "none";
    String actionLog = "";
    int mapWidth = 0;
    Boolean done = false;
    JSONObject result = new JSONObject();
    Interpreter interpreter;
    Queue<Action> actionQueue = new LinkedList<Action>();
    public BasicGridSearch(ActionExecutor executor, Interpreter interpreter) {
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
    public void turnLeft() {
        String left = interpreter.getLeftDirection();

        actionQueue.add(new Action("heading","left"));
    }
    public void stop() {
        actionQueue.add(new Action("stop"));
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
    public JSONObject nextAction() {
        Action actionToDo = actionQueue.remove();
        Action actionWithDirection = addDirection(actionToDo);
        
        logger.info("doing {} #{} with battery {}", actionToDo.name(), interpreter.numberOfActions(), interpreter.getBattery());
        return executor.execute(actionWithDirection);
    }
    // no info needed
    public void setInfoNeeded(JSONObject info) {
    } 
   
    public void undoTurn() {
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
    public JSONObject takeDecision() {
        //logger.info("battery {}", interpreter.getBattery());
        if (interpreter.getBattery() < 50 || interpreter.numberOfActions() > 1500){
            //logger.info(actionLog);
            return executor.stop();
        }
        while (actionQueue.isEmpty()) {
            switch (state) {
                case 1:
                    scan();
                    state = 4;
                    break;
                case 2:
                if (interpreter.lastScanHadLand()) {
                    fly();
                    state = 1;
                } else {
                    fly();
                    scan();
                    state = 4;
                }
                logger.info("the last scan biome is " + interpreter.lastScan().biomes()[0]);  
                break;
                case 3:
                if (interpreter.lastScanHadLand()) {
                    fly();
                    state = 1;
                } else {
                    fly();
                    scan();
                    state = 4;
                }
                logger.info("the last scan biome is " + interpreter.lastScan().biomes()[0]);  
                break;
                case 4: 
                if (interpreter.lastScanHadLand()) {
                    fly();
                    state = 1;
                } else {
                    if (directionToTurn != null && directionToTurn.equals("left")) {
                        logger.info("turns left");
                        turnLeft();
                        turnLeft();
                        echo();
                        directionToTurn = "right";
                    // If direction to turn is right or its the first turn
                    } else {
                        logger.info("does this RIGHT");
                        turnRight();
                        turnRight();
                        scan();
                        echo();
                        directionToTurn = "left";
                    }
                    state = 5;
                } 
                break;
                case 5:
                    Echo lastEcho = interpreter.lastEcho();
                    // If out of range, then turn around (island is not that way!)
                    if (lastEcho.found().equals("OUT_OF_RANGE")) {
                        // Go back to where you were before you scanned ocean 3 times. 
                        fly();
                        fly();
                        fly();
                        // comment this scan out later.
                        logger.info("out of range found?");
                        scan();


                        // Don't undo the turn until you find the whole line (range > 4)
                        // State 6 will keep echoing until it finds the whole "line of land"
                        state = 6;
                    // If ground is found, then go to ground, and then go to state 1 to enter scanning phase.
                    } else if (lastEcho.found().equals("GROUND")) {
                        logger.info("flying forward by {}", lastEcho.range());
                        flyForwardBy(lastEcho.range()+1);
                        state = 1;
                    }
                    
                break;
                case 6:
                // Basically, if the direction to turn is left and you're in this state, (you only get to this
                // state if you don't see land in front of you after turning. ) 
                // You know that you shouldn't actually turn left because there isn't land in that direction
                // The land is to your right.
                // Keep echoing until you find when you're no longer beside land (this is to 
                // ensure that you don't miss any)
                    if (directionToTurn.equals("left")) {
                        fly();
                        fly();
                        fly();
                        scan();
                        echoRight();
                    }
                    else if (directionToTurn.equals("right")) {
                        fly();
                        fly();
                        fly();
                        scan();
                        echoLeft();
                    }
                    state = 7;
                break;
                case 7:
                    Echo lastEchoState7 = interpreter.lastEcho();
                    if (lastEchoState7.range() > 2) {
                        undoTurn();
                        echo();
                        state = 5;
                    } else {
                        state = 6;
                    }
                break;
                default: 
                    break;
            }
        }

        if (actionQueue.isEmpty()) {
            logger.info("empty");
        }
        //logger.info(actionLog);
        Coordinate current =  interpreter.getCurrent();
        //logger.info("Coordinates (based on action queue): {} {}", current.x(), current.y());
        
        return nextAction();
    }

    public Boolean done() {
        return done;
    }
    public JSONObject results() {
        return result;
    }
}
