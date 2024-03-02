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
    Boolean done = false;
    JSONObject result = new JSONObject();
    Interpreter interpreter;
    String shouldTurn = "";
    Queue<JSONObject> actionQueue = new LinkedList<JSONObject>();
    Boolean initialScanDone = false;
    public FindIsland(ActionExecutor executor, Interpreter interpreter) {
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
        shouldTurn = "left";
        return executor.turn(right);
    }
    public JSONObject turnLeft() {
        String left = interpreter.getLeftDirection();
        shouldTurn = "right";

        return executor.turn(left);
    }
    public JSONObject echoLeft() {
        String left = interpreter.getLeftDirection();
        return executor.echo(left);
    }
    // public JSONObject initialScan() {
    //     actionCount++;
    //     if (actionCount == 1) {
    //         String facing = interpreter.facing();
    //         return executor.echo(facing);
    //     } else if (actionCount == 2) {
    //         return echoLeft();
    //     } else {
    //         initialScanDone = true;
    //         return echoRight();
    //     }

    // }
    public void flyForwardBy(int blocks) {
        for (int i=0; i<blocks; i++) {
            actionQueue.add(executor.fly());
            actionQueue.add(executor.scan());
        }
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
                    actionQueue.add(echo());
                    state = 2;
                    break;
                case 2:
                    actionQueue.add(executor.fly());
                    actionQueue.add(echoLeft());
                    actionQueue.add(echoRight());
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
                    actionQueue.add(executor.fly());
                    if (doNotEchoSide.equals("left")) {
                        actionQueue.add(echoRight());
                    } else if (doNotEchoSide.equals("right")) {
                        actionQueue.add(echoLeft());
                    } else {
                        actionQueue.add(echoLeft());
                        actionQueue.add(echoRight());
                    }
                    state = 4;
                    break;
                default:
                    actionQueue.add(executor.stop());
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
        Echo lastEcho = interpreter.lastEcho();
        if (lastEcho == null) {
            return false;
        }
        logger.info("i found the {}", lastEcho.found());
        if (lastEcho.found().equals("GROUND")) {
            done = true;
            result.put("rangeOfIslandRelativeToDrone", lastEcho.range());
            result.put("directionOfIslandRelativeToDrone", lastEcho.direction());
        }


        return done;
    }
    public JSONObject results() {
        return result;
    }
}
