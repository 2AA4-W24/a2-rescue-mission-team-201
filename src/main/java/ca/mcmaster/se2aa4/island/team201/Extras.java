package ca.mcmaster.se2aa4.island.team201;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class Extras {
    
    private final Logger logger = LogManager.getLogger();
    int mostRecentEcho;
    String mostRecentScan;
    public void updateState(JSONObject extraInfo) {
        //logger.info(extraInfo.toString());
    }
    

}
