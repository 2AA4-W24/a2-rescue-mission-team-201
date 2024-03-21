package ca.mcmaster.se2aa4.island.team201;

import org.json.JSONObject;

public class Navigator {
    private Phase[] phases;
    private Phase currentPhase;
    private int currentPhaseIndex;

    public Navigator(Phase[] phases) {
        this.phases = phases;
        this.currentPhaseIndex = 0;
        this.currentPhase = phases[currentPhaseIndex];
    }

    private void changePhaseIfNeeded() {
        if (currentPhase.done()) {
            JSONObject infoToPassOn = currentPhase.results();
            goToNextPhase();
            currentPhase.initialize(infoToPassOn);
        }
    }
    private void goToNextPhase() {
        currentPhaseIndex += 1;
        currentPhase = phases[currentPhaseIndex];
    }
    public String takeDecision() {

        changePhaseIfNeeded();

        JSONObject decision = currentPhase.takeDecision();
        return decision.toString();
    }

}
