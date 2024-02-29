package ca.mcmaster.se2aa4.island.team201;

public class Navigator {
    private Phase phase;

    public Navigator(Phase phase) {
        this.phase = phase;
    }

    public String takeDecision() {
        return phase.takeDecision();
    }

}
