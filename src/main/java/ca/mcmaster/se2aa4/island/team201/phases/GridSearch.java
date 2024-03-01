package ca.mcmaster.se2aa4.island.team201.phases;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team201.ActionExecutor;
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
        if (interpreter.getBattery() < 50) {
            return executor.stop();
        }
        if (actionQueue.isEmpty()) {
            switch (step) {
                case 1:
                    logger.info("step 1");
                    actionQueue.add(echo());
                    step= 2;
                    break;
                case 2:
                    logger.info("step 2");
                    Echo echo = interpreter.mostRecentEcho();
                    int blocksToFly = echo.range();
                    logger.info("Let's fly forward by {} blocks", blocksToFly);
                    flyForwardBy(blocksToFly-2);
                    step = 3;
                    break;
                case 3:
                    logger.info("step 3");
                    actionQueue.add(echoRight());
                    actionQueue.add(echoLeft());
                    if (lastTurned.equals("left")) {
                        step = 5;
                    } else {
                        step = 4;
                    }
                    break;
                case 4:
                    logger.info("step 4");
                    lastTurned = "right";
                    actionQueue.add(turnRight());
                    actionQueue.add(turnRight());
                    actionQueue.add(echo());
                    step = 2;
                    break;
                case 5:
                    logger.info("step 5");
                    lastTurned = "left";
                    actionQueue.add(turnLeft());
                    actionQueue.add(turnLeft());
                    actionQueue.add(echo());
                    step = 2;
                    break;
                
                default:
                    actionQueue.add(executor.stop());
                    break;
            }
        }

        logger.info("here");
        if (actionQueue.isEmpty()) {
            logger.info("empty");
        }
        JSONObject actionToDo = actionQueue.remove();
        logger.info("doing {}", actionToDo.getString("action"));
        return actionToDo;
    }

    public Boolean done() {
        return true;
    }
}
