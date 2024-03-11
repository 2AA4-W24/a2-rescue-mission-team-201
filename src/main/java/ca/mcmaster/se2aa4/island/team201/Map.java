package ca.mcmaster.se2aa4.island.team201;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Map {
    ArrayList<Integer> linesSearched = new ArrayList<Integer>();
    String directionOfLines;
    String axisToSave = "x";
    ArrayList<Scan> creeks = new ArrayList<Scan>();
    Scan site;

    public void setDirectionOfLines(String direction) {
        this.directionOfLines = direction;
        setAxisTosave();
    }

    public void updateState(JSONObject scan, Coordinate current) {
        JSONArray jsonBiomes = scan.getJSONArray("biomes");
        JSONArray jsonCreeks = scan.getJSONArray("creeks");
        JSONArray jsonSites = scan.getJSONArray("sites");

        String[] biomes = new String[0];
        String[] creeks = new String[0];
        String[] sites = new String[0];

        if (jsonBiomes.length() != 0) {
            biomes = new String[jsonBiomes.length()];
            for (int i = 0; i < biomes.length; i++) {
                biomes[i] = jsonBiomes.optString(i);

            }
        }
        if (jsonCreeks.length() != 0) {
            creeks = new String[jsonCreeks.length()];
            for (int i = 0; i < creeks.length; i++) {
                creeks[i] = jsonCreeks.optString(i);
            }
            
        }
        if (jsonSites.length() != 0) {
            sites = new String[jsonSites.length()];
            for (int i = 0; i < sites.length; i++) {
                sites[i] = jsonSites.optString(i);
            }
        }

        Scan scanToCheck = new Scan(biomes, creeks, sites, current);

        addIfCreekOrSite(scanToCheck);
    }
    private void addIfCreekOrSite(Scan scan) {
        if (scan.sites().length > 0) {
            site = scan;
        }
        if (scan.creeks().length> 0) {
            creeks.add(scan);
        }
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
