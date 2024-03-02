package ca.mcmaster.se2aa4.island.team201;

import org.json.JSONObject;

public interface Phase {
    public Boolean done();
    public JSONObject takeDecision();
    public void setInfoNeeded(JSONObject info);
    public JSONObject results();
}
