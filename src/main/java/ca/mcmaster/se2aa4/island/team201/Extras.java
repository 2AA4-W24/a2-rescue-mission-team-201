package ca.mcmaster.se2aa4.island.team201;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class Extras {

    private final Logger logger = LogManager.getLogger();
    Echo mostRecentEcho;
    ArrayList<Echo> echos = new ArrayList<Echo>();
    Scan mostRecentScan;
    ArrayList<Scan> scans = new ArrayList<Scan>();
    
    public void updateState(JSONObject extraInfo, Action lastAction, Coordinate location) {
        switch (lastAction.name()) {
            case "echo":
                int range = extraInfo.getInt("range");
                String found = extraInfo.getString("found");
                String direction = lastAction.direction();
                mostRecentEcho = new Echo(range, found, direction);
                echos.add(mostRecentEcho);
                break;
            case "scan":
                JSONArray jsonBiomes = extraInfo.getJSONArray("biomes");
                JSONArray jsonCreeks = extraInfo.getJSONArray("creeks");
                JSONArray jsonSites = extraInfo.getJSONArray("sites");

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
                mostRecentScan = new Scan(biomes, creeks, sites, location);
                scans.add(mostRecentScan);
            default:
                break;
        }

    }

    public Echo lastEcho() {
        return mostRecentEcho;
    }
    public Scan lastScan() {
        return mostRecentScan;
    }

    public ArrayList<Echo> getEchos() {
        return echos;
    }
    public ArrayList<Scan> getScans() {
        return scans;
    }

}
