package model.solvers;

import model.exceptions.OutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MemorySolverTest {
    private MemorySolver meSolver;

    @BeforeEach
    public void setup() {
        meSolver = MemorySolver.getInstance();
        meSolver.setInput(null);
        meSolver.getLabels().clear();
        meSolver.getPositions().clear();
        meSolver.setStage(1);
    }

    @Test
    public void testConstructor() {
        assertNull(meSolver.getInput());
        assertEquals(0, meSolver.getLabels().size());
        assertEquals(0, meSolver.getPositions().size());
        assertEquals(1, meSolver.getStage());
    }

    @Test
    public void testSetStage() {
        meSolver.setStage(3);
        assertEquals(3, meSolver.getStage());
        meSolver.setStage(5);
        assertEquals(5, meSolver.getStage());
    }

    @Test
    public void testExit() {
        meSolver.setInput("exit");
        try {
            assertEquals("\nExited module.", meSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testReset() {
        meSolver.getLabels().add("1");
        meSolver.getPositions().add(1);
        meSolver.setInput("done");
        try {
            assertEquals("\nMemory reset, ready for the next Memory module.", meSolver.solve());
            assertEquals(0, meSolver.getLabels().size());
            assertEquals(0, meSolver.getPositions().size());
            assertEquals(1, meSolver.getStage());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testBack() {
        meSolver.getLabels().add("1");
        meSolver.getPositions().add(1);
        try {
            meSolver.setInput("back");
            assertEquals("\nYou are at the first stage and thus cannot go back a stage.", meSolver.solve());
            meSolver.setStage(2);
            assertEquals("\nReverted to previous stage of the Memory module.", meSolver.solve());
            assertEquals(0, meSolver.getLabels().size());
            assertEquals(0, meSolver.getPositions().size());
            assertEquals(1, meSolver.getStage());
            meSolver.getLabels().add("1");
            meSolver.getPositions().add(1);
            meSolver.getLabels().add("2");
            meSolver.getPositions().add(2);
            meSolver.setStage(2);
            assertEquals("\nReverted to previous stage of the Memory module.", meSolver.solve());
            assertEquals(1, meSolver.getLabels().size());
            assertEquals(1, meSolver.getPositions().size());
            assertEquals("1", meSolver.getLabels().get(0));
            assertEquals(1, meSolver.getPositions().get(0));
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testInvalidFormatException() {
        meSolver.setInput("1");
        try {
            meSolver.solve();
            fail();
        } catch (OutOfBoundsException e) {
            assertEquals("\nYour input should have 5 numbers (the display and 4 buttons)"
                    + ", please try again.", e.getMessage());
        }
    }

    @Test
    public void testDuplicateException() {
        meSolver.setInput("1 4 2 3 3");
        try {
            meSolver.solve();
            fail();
        } catch (OutOfBoundsException e) {
            assertEquals("\nThere should not be duplicate button labels, please try again.", e.getMessage());
        }
    }

    @Test
    public void testSolveNumberException() {
        for (int x = 0; x < 6; x++) {
            meSolver.setStage(x);
            meSolver.setInput("43 3 1 2 4");
            try {
                meSolver.solve();
                fail();
            } catch (OutOfBoundsException e) {
                assertEquals("\nOne or more of your inputs were not typed in the correct "
                        + "format, please try again.", e.getMessage());
            }
        }
    }

    @Test
    public void testSolveDisplay1() {
        try {
            meSolver.setInput("1 3 1 2 4");
            assertEquals("\nPress \"1\".", meSolver.solve());
            meSolver.setInput("1 1 3 2 4");
            assertEquals("\nPress \"4\".", meSolver.solve());
            meSolver.setInput("1 4 1 3 2");
            assertEquals("\nPress \"4\".", meSolver.solve());
            meSolver.setInput("1 2 1 3 4");
            assertEquals("\nPress \"1\".", meSolver.solve());
            meSolver.setInput("1 3 4 2 1");
            assertEquals("\nPress \"1\".\n\nMemory reset, ready for the next Memory module.", meSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveDisplay2() {
        try {
            meSolver.setInput("2 1 3 2 4");
            assertEquals("\nPress \"3\".", meSolver.solve());
            meSolver.setInput("2 2 1 3 4");
            assertEquals("\nPress \"1\".", meSolver.solve());
            meSolver.setInput("2 4 1 3 2");
            assertEquals("\nPress \"3\".", meSolver.solve());
            meSolver.setInput("2 3 1 2 4");
            assertEquals("\nPress \"3\".", meSolver.solve());
            meSolver.setInput("2 3 4 2 1");
            assertEquals("\nPress \"1\".\n\nMemory reset, ready for the next Memory module.", meSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveDisplay3() {
        try {
            meSolver.setInput("3 3 4 2 1");
            assertEquals("\nPress \"2\".", meSolver.solve());
            meSolver.setInput("3 3 1 2 4");
            assertEquals("\nPress \"3\".", meSolver.solve());
            meSolver.setInput("3 2 1 3 4");
            assertEquals("\nPress \"3\".", meSolver.solve());
            meSolver.setInput("3 4 1 3 2");
            assertEquals("\nPress \"4\".", meSolver.solve());
            meSolver.setInput("3 1 3 2 4");
            assertEquals("\nPress \"4\".\n\nMemory reset, ready for the next Memory module.", meSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveDisplay4() {
        try {
            meSolver.setInput("4 4 1 3 2");
            assertEquals("\nPress \"2\".", meSolver.solve());
            meSolver.setInput("4 3 4 2 1");
            assertEquals("\nPress \"1\".", meSolver.solve());
            meSolver.setInput("4 3 1 2 4");
            assertEquals("\nPress \"4\".", meSolver.solve());
            meSolver.setInput("4 2 1 3 4");
            assertEquals("\nPress \"4\".", meSolver.solve());
            meSolver.setInput("4 1 3 2 4");
            assertEquals("\nPress \"4\".\n\nMemory reset, ready for the next Memory module.", meSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }
}
