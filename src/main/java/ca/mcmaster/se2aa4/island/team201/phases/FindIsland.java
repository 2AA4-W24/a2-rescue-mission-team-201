package ca.mcmaster.se2aa4.island.team201.phases;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team201.ActionExecutor;
import ca.mcmaster.se2aa4.island.team201.Interpreter;
import ca.mcmaster.se2aa4.island.team201.Phase;

public class FindIsland implements Phase {

    ActionExecutor executor; 
    Interpreter interpreter;
    public FindIsland(ActionExecutor executor, Interpreter interpreter) {
        
    }

    public JSONObject takeDecision() {
        return executor.stop();
    }

    public Boolean done() {
        return true;
    }
}
