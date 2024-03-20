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
}
