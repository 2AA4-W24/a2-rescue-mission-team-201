package ca.mcmaster.se2aa4.island.team201;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class Extras {
    
    private final Logger logger = LogManager.getLogger();
    Echo mostRecentEcho;
    ArrayList<Echo> echos = new ArrayList<Echo>();
    String mostRecentScan;
    
    public void updateState(JSONObject extraInfo, Action lastAction) {
        logger.info("last action is " + lastAction.name());
        if (lastAction.name().equals("echo")) {
            int range = extraInfo.getInt("range");
            String found = extraInfo.getString("found");
            String direction = lastAction.direction();
            mostRecentEcho = new Echo(range, found, direction);
            echos.add(mostRecentEcho);
        }

    }
    public Echo lastEcho() {
        return mostRecentEcho;
    }
    public ArrayList<Echo> getEchos() {
        return echos;
    }
    

}
