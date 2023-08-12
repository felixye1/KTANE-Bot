package model.solvers;

import model.exceptions.OutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WireSequenceSolverTest {
    private WireSequenceSolver wsSolver;

    @BeforeEach
    public void setup() {
        wsSolver = WireSequenceSolver.getInstance();
        wsSolver.setInput("done");
        try {
            wsSolver.solve();
            wsSolver.setInput(null);
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testConstructor() {
        assertNull(wsSolver.getInput());
        assertEquals(0, wsSolver.getRedPos());
        assertEquals(0, wsSolver.getBluePos());
        assertEquals(0, wsSolver.getBlackPos());
        assertEquals(1, wsSolver.getStage());
        assertEquals(0, wsSolver.getPrevInputs().size());
    }

    @Test
    public void testSetStage() {
        wsSolver.setStage(3);
        assertEquals(3, wsSolver.getStage());
        wsSolver.setStage(5);
        assertEquals(5, wsSolver.getStage());
    }

    @Test
    public void testExit() {
        try {
            wsSolver.setInput("exit");
            assertEquals("\nExited module.", wsSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testBack() {
        try {
            wsSolver.setInput("back");
            assertEquals("\nYou are at the first stage and thus cannot go back a stage.", wsSolver.solve());
            wsSolver.setInput("red b next red c next blue a");
            wsSolver.solve();
            wsSolver.setInput("black b next blue c next red a");
            wsSolver.solve();
            assertEquals(3, wsSolver.getRedPos());
            assertEquals(2, wsSolver.getBluePos());
            assertEquals(1, wsSolver.getBlackPos());
            assertEquals(3, wsSolver.getStage());
            assertEquals(2, wsSolver.getPrevInputs().size());
            wsSolver.setInput("back");
            assertEquals("\nReverted to previous stage of the Wire Sequences module.", wsSolver.solve());
            assertEquals(2, wsSolver.getRedPos());
            assertEquals(1, wsSolver.getBluePos());
            assertEquals(0, wsSolver.getBlackPos());
            assertEquals(2, wsSolver.getStage());
            assertEquals(1, wsSolver.getPrevInputs().size());
            assertEquals("\nReverted to previous stage of the Wire Sequences module.", wsSolver.solve());
            assertEquals(0, wsSolver.getRedPos());
            assertEquals(0, wsSolver.getBluePos());
            assertEquals(0, wsSolver.getBlackPos());
            assertEquals(1, wsSolver.getStage());
            assertEquals(0, wsSolver.getPrevInputs().size());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testReset() {
        try {
            wsSolver.setInput("red b next red c next blue a");
            wsSolver.solve();
            wsSolver.setInput("black b next blue c next red a");
            wsSolver.solve();
            assertEquals(3, wsSolver.getRedPos());
            assertEquals(2, wsSolver.getBluePos());
            assertEquals(1, wsSolver.getBlackPos());
            assertEquals(3, wsSolver.getStage());
            assertEquals(2, wsSolver.getPrevInputs().size());
            wsSolver.setInput("done");
            assertEquals("\nMemory reset, ready for the next Wire Sequences module.", wsSolver.solve());
            assertEquals(0, wsSolver.getRedPos());
            assertEquals(0, wsSolver.getBluePos());
            assertEquals(0, wsSolver.getBlackPos());
            assertEquals(1, wsSolver.getStage());
            assertEquals(0, wsSolver.getPrevInputs().size());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveInvalidFormat() {
        wsSolver.setInput("red next black a next blue c");
        try {
            wsSolver.solve();
            fail();
        } catch (OutOfBoundsException e) {
            assertEquals("\nInput is invalid, please try again.", e.getMessage());
            wsSolver.setInput("red a dummy black a next blue c");
            try {
                wsSolver.solve();
                fail();
            } catch (OutOfBoundsException e2) {
                assertEquals("\nInput is invalid, please try again.", e2.getMessage());
                wsSolver.setInput("red c next black");
                try {
                    wsSolver.solve();
                    fail();
                } catch (OutOfBoundsException e3) {
                    assertEquals("\nYour input should have 2 words per wire "
                            + "(its color and which letter it ends at), each separated by the "
                            + "\"next\" keyword, please try again.", e3.getMessage());
                }
            }
        }
    }

    @Test
    public void testSolveInvalidInput() {
        wsSolver.setInput("dummy b next black a next blue c");
        try {
            wsSolver.solve();
        } catch (OutOfBoundsException e) {
            assertEquals("\nInput is invalid, please try again.", e.getMessage());
        }
    }

    @Test
    public void testSolve() {
        try {
            wsSolver.setInput("red c next blue a next blue b");
            assertEquals("\nFollow whether to cut (in order of wires inputted): yes, no, no.", wsSolver.solve());
            wsSolver.setInput("black a next black c next blue a");
            assertEquals("\nFollow whether to cut (in order of wires inputted): yes, yes, no.", wsSolver.solve());
            wsSolver.setInput("red c next black b next blue c");
            assertEquals("\nFollow whether to cut (in order of wires inputted): no, yes, no.", wsSolver.solve());
            wsSolver.setInput("blue b next red c next black b");
            assertEquals("\nFollow whether to cut (in order of wires inputted): yes, no, no." +
                    "\n\nMemory reset, ready for the next Wire Sequences module.", wsSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }
}
