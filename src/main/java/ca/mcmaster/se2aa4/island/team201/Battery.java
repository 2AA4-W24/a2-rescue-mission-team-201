package ca.mcmaster.se2aa4.island.team201;

public class Battery {
    private int level;

    public Battery(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void decreaseLevelBy(int cost) {
        this.level -= cost;
    }
}
