package ca.mcmaster.se2aa4.island.team201;
import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;


 public class ExtrasTest {
     @Test
     void testEchoActionUpdates() {
         Extras extras = new Extras();
         Action echoAction = new Action("echo", "N");

         JSONObject echoInfo = new JSONObject()
                 .put("range", 5)
                 .put("found", "tree");
         Coordinate location = new Coordinate(0,0);

         extras.updateState(echoInfo, echoAction, location);

         // some Assertions
         assertFalse(extras.getEchos().isEmpty(), "Echo list shouldn't be empty after processing echo");
         Echo mostRecentEcho = extras.lastEcho();
         assertEquals(5, mostRecentEcho.range(), "The range of recent echo should be 5");
         assertEquals("tree", mostRecentEcho.found(), "should be 'tree'");
         assertEquals("N", mostRecentEcho.direction(), " direction should be 'N'.");
     }

 }
