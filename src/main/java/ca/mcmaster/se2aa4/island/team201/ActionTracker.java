package ca.mcmaster.se2aa4.island.team201;

public class ActionTracker {
    private int numActionsCompleted =0;
    private Action lastActionCompleted; 
    public void setLastAction(String action) {
        lastActionCompleted = new Action(action);
    }
    public void setLastAction(String action, String direction) {
        lastActionCompleted = new Action(action,direction);
    }
    public void incrementActionsCompleted() {
        numActionsCompleted = numActionsCompleted+1;
    }
    public int numberOfActions() {
        return numActionsCompleted;
    }

    public Action lastAction() {
        return lastActionCompleted;
    }
}
