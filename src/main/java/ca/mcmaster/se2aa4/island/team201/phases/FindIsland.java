package ca.mcmaster.se2aa4.island.team201.phases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team201.ActionExecutor;
import ca.mcmaster.se2aa4.island.team201.Interpreter;
import ca.mcmaster.se2aa4.island.team201.Phase;

public class FindIsland implements Phase {
    private final Logger logger = LogManager.getLogger();
  
    
    ActionExecutor executor; 
    Interpreter interpreter;
    public FindIsland(ActionExecutor executor, Interpreter interpreter) {
        this.executor = executor;
        this.interpreter = interpreter;
    }

    public JSONObject turnRight() {
        String right = interpreter.getRightDirection();
        return executor.turn(right);
    }
    public JSONObject echoRight() {
        String right = interpreter.getRightDirection();
        return executor.echo(right);
    }
    public JSONObject echoLeft() {
        String left = interpreter.getLeftDirection();
        return executor.echo(left);
    }


    public JSONObject takeDecision() {
        int numActions = interpreter.numberOfActions();
        logger.info("Action #{}", numActions);
        if (interpreter.numberOfActions() == 0) {
            return echoRight();
        } else if (interpreter.numberOfActions() == 1) {
            return echoLeft();
        } else {
        return executor.stop();
        }


    public Boolean done() {
        return true;
    }
}
