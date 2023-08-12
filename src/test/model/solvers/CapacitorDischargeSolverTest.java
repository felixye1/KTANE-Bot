package model.solvers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CapacitorDischargeSolverTest {
    private CapacitorDischargeSolver cdSolver;

    @BeforeEach
    public void testConstructor() {
        cdSolver = CapacitorDischargeSolver.getInstance();
    }

    @Test
    public void testSolve() {
        assertEquals("\nPress on the lever whenever either the meter on the left " +
                "is close to filling or when the timer is near 0." ,cdSolver.solve());
    }
}
