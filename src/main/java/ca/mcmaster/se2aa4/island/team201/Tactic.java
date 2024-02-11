package ca.mcmaster.se2aa4.island.team201;

import org.json.JSONObject;

public interface Tactic {
    public Boolean tacticComplete();
    public JSONObject decideAction();
}
