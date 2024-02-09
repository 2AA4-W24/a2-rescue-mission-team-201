package ca.mcmaster.se2aa4.island.team201;
import org.json.JSONObject;

public class EnvironmentScanner {

    public void scanEnvironment(Map map, Position currentPosition) {

        TerrainType scannedType = performScan();
        map.updateCell(currentPosition.getX(), currentPosition.getY(), scannedType);
    }

    private TerrainType performScan() {

        return TerrainType.FOREST;
    }
}

