package ca.mcmaster.se2aa4.island.team201;

import java.util.ArrayList;

public class Map {
    ArrayList<Integer> linesSearched = new ArrayList<Integer>();
    String directionOfLines;
    
    public void setDirectionOfLines(String direction) {
        this.directionOfLines = direction;
    }
    public void setLineSearched(int line) {
        linesSearched.add(line);
    }
    public Boolean hasAlreadySearched(int line) {
        return linesSearched.contains(line);
    }
}
