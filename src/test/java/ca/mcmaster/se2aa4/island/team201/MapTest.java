package ca.mcmaster.se2aa4.island.team201;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void testSetDirectionOfLines() {
        Map map = new Map();
        map.setDirectionOfLines("N");
        assertEquals("x", map.axisToSave, "the Axis should be x for North direction");
    }

}

