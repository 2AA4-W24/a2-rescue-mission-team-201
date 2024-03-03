package ca.mcmaster.se2aa4.island.team201;

public class ActionTracker {
    private int numActionsCompleted =0;
    private String[] lastActionCompleted; 
    public void setLastAction(String action) {
        lastActionCompleted = new String[] {action, "N/A"};
    }
    public void setLastAction(String action, String direction) {
        lastActionCompleted = new String[] {action, direction};
    }
    public void incrementActionsCompleted() {
        numActionsCompleted = numActionsCompleted+1;
    }
    public int numberOfActions() {
        return numActionsCompleted;
    }

    public String[] lastActionWithDirection() {
        return lastActionCompleted;
    }
    public String lastAction() {
        return lastActionCompleted[0];
    }
}
