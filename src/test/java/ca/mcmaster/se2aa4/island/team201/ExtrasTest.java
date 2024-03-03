package ca.mcmaster.se2aa4.island.team201;
import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class ExtrasTest {
    @Test
    void updatesEchoResponsesCorrectly() {
        Extras extras = new Extras();
        JSONObject echoInfo = new JSONObject("{\"range\": 5, \"found\": \"tree\"}");
        extras.updateState(echoInfo, "echo");
        assertEquals(1, extras.getEchos().size());
        assertEquals("tree", extras.mostRecentEcho().found());
    }

}
