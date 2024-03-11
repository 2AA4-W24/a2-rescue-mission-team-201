package ca.mcmaster.se2aa4.island.team201;

import java.util.ArrayList;

public class Map {
    ArrayList<Integer> linesSearched = new ArrayList<Integer>();
    String directionOfLines;
    String axisToSave = "x";
    public void setDirectionOfLines(String direction) {
        this.directionOfLines = direction;
        setAxisTosave();
    }
    private void setAxisTosave() {
        switch (directionOfLines) {
            case "N":
            axisToSave = "x";
            break;
            case "S":
            axisToSave = "x";
            break;
            case "E":
            axisToSave = "y";
            break;
            case "W":
            axisToSave = "y";
            break;
            default: 
            axisToSave = "x";
            break;
        }
    }
    public void setLineSearched(Coordinate current) {
        if (axisToSave.equals("x")) {
            linesSearched.add(current.x());
        } else if (axisToSave.equals("y")) {
            linesSearched.add(current.y());
        }
    }
    
    public Boolean hasAlreadySearched(Coordinate current) {
        if (axisToSave.equals("x")) {
            return linesSearched.contains(current.x());
        } else if (axisToSave.equals("y")) {
            return linesSearched.contains(current.y());
        }

        // should not reach this case
        return true;
    }
}
