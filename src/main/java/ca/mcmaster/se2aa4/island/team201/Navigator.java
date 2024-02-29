package ca.mcmaster.se2aa4.island.team201;

import org.json.JSONObject;

public class Navigator {
    private Phase phase;

    public Navigator(Phase phase) {
        this.phase = phase;
    }

    public String takeDecision() {
        JSONObject decision = phase.takeDecision();
        
        return decision.toString();
    }

}
