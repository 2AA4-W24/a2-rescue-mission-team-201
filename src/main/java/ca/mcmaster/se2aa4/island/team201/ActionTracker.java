package ca.mcmaster.se2aa4.island.team201;

public class ActionTracker {
    private int numActionsCompleted =0;
    private String lastActionCompleted; 

    public void setLastAction(String action) {
        lastActionCompleted = action;
    }
    public void incrementActionsCompleted() {
        numActionsCompleted = numActionsCompleted+1;
    }
    public int numberOfActions() {
        return numActionsCompleted;
    }
    public String lastAction() {
        return lastActionCompleted;
    }
}
