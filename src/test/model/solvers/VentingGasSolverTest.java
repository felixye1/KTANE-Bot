package model.solvers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VentingGasSolverTest {
    private VentingGasSolver veSolver;

    @BeforeEach
    public void setup() {
        veSolver = VentingGasSolver.getInstance();
    }

    @Test
    public void testSolve() {
        assertEquals("\nIf the module asks to vent gas, press \"Y\". If the module asks to detonate, press \"N\".",
                veSolver.solve());
    }
}
