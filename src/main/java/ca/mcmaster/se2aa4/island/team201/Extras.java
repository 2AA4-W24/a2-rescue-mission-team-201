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
    
    public void updateState(JSONObject extraInfo, String lastAction) {
        logger.info("last action is " + lastAction);
        if (extraInfo.has("range")) {
            int range = extraInfo.getInt("range");
            String found = extraInfo.getString("found");
            mostRecentEcho = new Echo(range, found);
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
