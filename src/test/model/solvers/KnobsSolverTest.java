package model.solvers;

import model.exceptions.OutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KnobsSolverTest {
    private KnobsSolver knSolver;

    @BeforeEach
    public void setup() {
        knSolver = KnobsSolver.getInstance();
        knSolver.setInput(null);
    }

    @Test
    public void testConstructor() {
        assertNull(knSolver.getInput());
    }

    @Test
    public void testExit() {
        try {
            knSolver.setInput("exit");
            assertEquals("\nExited module.", knSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testInvalidInput() {
        try {
            knSolver.setInput("101110101010");
            knSolver.solve();
            fail();
        } catch (OutOfBoundsException e) {
            assertEquals("\nYour input should have the a string of 0/1s"
                    + ", followed by the position of the \"UP\" label, please try again.",
                    e.getMessage());
        }
    }

    @Test
    public void testIncorrectInput() {
        try {
            knSolver.setInput("111111111111 right");
            knSolver.solve();
            fail();
        } catch (OutOfBoundsException e) {
            assertEquals("\nOne or more of your inputs were not correct, "
                    + "please try again.", e.getMessage());
            try {
                knSolver.setInput("001011111101 dummy");
                knSolver.solve();
                fail();
            } catch (OutOfBoundsException e2) {
                assertEquals("\nOne or more of your inputs were not correct, "
                        + "please try again.", e2.getMessage());
            }
        }
    }

    @Test
    public void testSolve() {
        try {
            knSolver.setInput("001011111101 down");
            assertEquals("\nPoint the knob down.", knSolver.solve());
            knSolver.setInput("101010011011 right");
            assertEquals("\nPoint the knob right.", knSolver.solve());
            knSolver.setInput("011001111101 up");
            assertEquals("\nPoint the knob down.", knSolver.solve());
            knSolver.setInput("101010010001 down");
            assertEquals("\nPoint the knob up.", knSolver.solve());
            knSolver.setInput("000010100111 right");
            assertEquals("\nPoint the knob up.", knSolver.solve());
            knSolver.setInput("000010000110 left");
            assertEquals("\nPoint the knob down.", knSolver.solve());
            knSolver.setInput("101111111010 left");
            assertEquals("\nPoint the knob up.", knSolver.solve());
            knSolver.setInput("101100111010 up");
            assertEquals("\nPoint the knob right.", knSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }
}
