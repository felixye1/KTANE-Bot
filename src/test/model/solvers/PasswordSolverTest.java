package model.solvers;

import model.exceptions.OutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordSolverTest {
    private PasswordSolver paSolver;

    @BeforeEach
    public void setup() {
        paSolver = PasswordSolver.getInstance();
        try {
            paSolver.setInput("done");
            paSolver.solve();
            paSolver.setSolved(false);
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testConstructor() {
        assertEquals(1, paSolver.getPossiblePasses().size());
        assertEquals(1, paSolver.getColumn());
        assertFalse(paSolver.getSolved());
    }

    @Test
    public void testSetColumn() {
        assertEquals(1, paSolver.getColumn());
        paSolver.setColumn(3);
        assertEquals(3, paSolver.getColumn());
    }

    @Test
    public void testSetSolved() {
        assertFalse(paSolver.getSolved());
        paSolver.setSolved(true);
        assertTrue(paSolver.getSolved());
    }

    @Test
    public void testExit() {
        try {
            paSolver.setInput("exit");
            assertEquals("\nExited module.", paSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testBack() {
        paSolver.setInput("back");
        paSolver.getPossiblePasses().add(new HashSet<>());
        paSolver.getPossiblePasses().add(new HashSet<>());
        assertEquals(3, paSolver.getPossiblePasses().size());
        try {
            assertEquals("\nYou are at the first stage and thus cannot go back a stage.", paSolver.solve());
            paSolver.setColumn(3);
            assertEquals("\nReverted to previous 'column' of the Password module.", paSolver.solve());
            assertEquals(2, paSolver.getColumn());
            assertEquals(2, paSolver.getPossiblePasses().size());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testReset() {
        try {
            paSolver.setInput("done");
            assertEquals("\nMemory reset, ready for the next Password module.", paSolver.solve());
            assertEquals(1, paSolver.getPossiblePasses().size());
            assertEquals(1, paSolver.getColumn());
            assertFalse(paSolver.getSolved());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveException() {
        try {
            paSolver.setInput("a b c d e");
            paSolver.solve();
            fail();
        } catch (OutOfBoundsException e) {
            assertEquals("\nYour input should have 6 letters, please try again.", e.getMessage());
        }
    }

    @Test
    public void testSolveNoneFoundColumn1() {
        try {
            paSolver.setInput("d i j k m q");
            assertEquals("\nNo valid passwords were found with the given inputs, "
                    + "either go back characters or reset and try again.", paSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveNoneFoundColumn2() {
        try {
            paSolver.setInput("f i j k m q");
            assertEquals("\nColumn characters inputted successfully.", paSolver.solve());
            assertEquals(2, paSolver.getColumn());
            paSolver.setInput("a c f g h p");
            assertEquals("\nNo valid passwords were found with the given inputs, "
                    + "either go back characters or reset and try again.", paSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveNoneFoundColumn3() {
        try {
            paSolver.setInput("f i j k m q");
            assertEquals("\nColumn characters inputted successfully.", paSolver.solve());
            assertEquals(2, paSolver.getColumn());
            paSolver.setInput("a c i g h o");
            assertEquals("\nColumn characters inputted successfully.", paSolver.solve());
            assertEquals(3, paSolver.getColumn());
            paSolver.setInput("a b c d e f");
            assertEquals("\nNo valid passwords were found with the given inputs, "
                    + "either go back characters or reset and try again.", paSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveFoundColumn1() {
        try {
            paSolver.setInput("b q u v x y");
            assertEquals("\nThe password is \"below\".\n" +
                    "\nMemory reset, ready for the next Password module.", paSolver.solve());
            assertTrue(paSolver.getSolved());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveFoundColumn2() {
        try {
            paSolver.setInput("b c u v x y");
            assertEquals("\nColumn characters inputted successfully.", paSolver.solve());
            assertEquals(2, paSolver.getColumn());
            paSolver.setInput("a f g h o s");
            assertEquals("\nThe password is \"could\".\n" +
                    "\nMemory reset, ready for the next Password module.", paSolver.solve());
            assertTrue(paSolver.getSolved());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveFoundColumn3() {
        try {
            paSolver.setInput("b c p v x y");
            assertEquals("\nColumn characters inputted successfully.", paSolver.solve());
            assertEquals(2, paSolver.getColumn());
            paSolver.setInput("a f g h o s");
            assertEquals("\nColumn characters inputted successfully.", paSolver.solve());
            assertEquals(3, paSolver.getColumn());
            paSolver.setInput("a b c i s t");
            assertEquals("\nThe password is \"point\".\n" +
                    "\nMemory reset, ready for the next Password module.", paSolver.solve());
            assertTrue(paSolver.getSolved());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }
}
