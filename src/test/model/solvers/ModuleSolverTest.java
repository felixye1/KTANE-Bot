package model.solvers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModuleSolverTest {
    private ModuleSolver moduleSolver;

    @Test
    public void testFinals() {
        assertEquals("exit", ModuleSolver.EXIT_KEY);
        assertEquals("\nExited module.", ModuleSolver.EXIT_MESSAGE);
        assertEquals("\nInput is invalid, please try again.", ModuleSolver.INVALID_INPUT_MESSAGE);
    }
}
