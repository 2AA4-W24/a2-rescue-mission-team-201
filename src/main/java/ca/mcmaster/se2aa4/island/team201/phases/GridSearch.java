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

public class GridSearch implements Phase {
    private final Logger logger = LogManager.getLogger();
    int actionCount =0;
    Boolean initialActions = false;
    ActionExecutor executor; 
    int step = 1;
    String actionLog = "";
    int mapWidth = 0;
    Interpreter interpreter;
    String lastTurned = "";
    Queue<JSONObject> actionQueue = new LinkedList<JSONObject>();
    Boolean initialScanDone = false;
    public GridSearch(ActionExecutor executor, Interpreter interpreter) {
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
        if (interpreter.getBattery() < 50 || interpreter.numberOfActions() > 350) {
            logger.info(actionLog);
            return executor.stop();

        }
        if (actionQueue.isEmpty()) {
            switch (step) {
                case 1:
                    actionLog+="1";
                    logger.info("step 1");
                    flyForwardBy(15);
                    actionQueue.add(echoRight());
                    actionQueue.add(echoLeft());
                    step= 2;
                    break;
                case 2:
                    actionLog+="2";
                    Echo[] last2Echos = interpreter.lastNumEchos(2);
                    mapWidth = last2Echos[0].range() + last2Echos[1].range();
                    step = 3;
                case 3:
                    logger.info("step 3");
                    actionLog+="3";
                    int blocksToFly = mapWidth-2;
                    logger.info("Let's fly forward by {} blocks", blocksToFly);
                    flyForwardBy(10);
                    step = 4;
                    break;
                case 4:
                    logger.info("step 4");
                    actionLog+="4";
                    //actionQueue.add(echoRight());
                    //actionQueue.add(echoLeft());
                    actionQueue.add(executor.scan());
                    if (lastTurned.equals("right")) {
                        step = 6;
                    } else {
                        step = 5;
                    }
                    break;
                case 5:
                    actionLog+="5";
                    logger.info("step 5");
                    lastTurned = "right";
                    actionQueue.add(turnRight());
                    actionQueue.add(turnRight());
                    actionQueue.add(echo());
                    step = 3;
                    break;
                case 6:
                    logger.info("step 6");
                    actionLog+="6";
                    lastTurned = "left";
                    actionQueue.add(turnLeft());
                    actionQueue.add(turnLeft());
                    actionQueue.add(echo());
                    step = 3;
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
        logger.info("Coordinates: {} {}", current.x(), current.y());
        JSONObject actionToDo = actionQueue.remove();
        logger.info("doing {}", actionToDo.getString("action"));
        return actionToDo;
    }

    public Boolean done() {
        return true;
    }
}
