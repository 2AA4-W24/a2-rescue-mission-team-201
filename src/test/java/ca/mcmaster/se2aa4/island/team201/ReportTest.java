package ca.mcmaster.se2aa4.island.team201;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportTest {
    private Interpreter interpreter;
    private Report report;

    @BeforeEach
    void setUp() {
        interpreter = mock(Interpreter.class);
        report = new Report(interpreter);
    }

    @Test
    public void testClosestInletWithNoInlets() {
        when(interpreter.getCreeks()).thenReturn(new Scan[0]);
        assertEquals("The closest inlet is No inlet found", report.getReport());
    }

    @Test
    public void testClosestInletWithSingleInlet() {
        Scan site = new Scan(new String[]{}, new String[]{}, new String[]{"site"}, new Coordinate(0, 0));
        Scan[] creekScans = {new Scan(new String[]{}, new String[]{"creek"}, new String[]{}, new Coordinate(1, 1))};
        when(interpreter.getCreeks()).thenReturn(creekScans);
        when(interpreter.getSite()).thenReturn(site);
        assertEquals("The closest inlet is creek", report.getReport());
    }
}
