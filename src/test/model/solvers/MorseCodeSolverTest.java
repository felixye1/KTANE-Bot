package model.solvers;

import model.exceptions.OutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class MorseCodeSolverTest {
    private MorseCodeSolver mcSolver;

    @BeforeEach
    public void setup() {
        mcSolver = MorseCodeSolver.getInstance();
        mcSolver.setInput(null);
        while (mcSolver.getPossibleCodes().size() != 1) {
            mcSolver.getPossibleCodes().remove(mcSolver.getPossibleCodes().size() - 1);
        }
        mcSolver.setCharacter(1);
        mcSolver.setSolved(false);
    }

    @Test
    public void testConstructor() {
        assertNull(mcSolver.getInput());
        assertEquals(1, mcSolver.getPossibleCodes().size());
        assertEquals(1, mcSolver.getCharacter());
        assertFalse(mcSolver.getSolved());
    }

    @Test
    public void testSetters() {
        mcSolver.setInput("test");
        assertEquals("test", mcSolver.getInput());
        mcSolver.setCharacter(3);
        assertEquals(3, mcSolver.getCharacter());
        mcSolver.setSolved(true);
        assertTrue(mcSolver.getSolved());
    }

    @Test
    public void testExit() {
        mcSolver.setInput("exit");
        try {
            assertEquals("\nExited module.", mcSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testBack() {
        mcSolver.setInput("back");
        mcSolver.getPossibleCodes().add(new HashSet<>());
        mcSolver.getPossibleCodes().add(new HashSet<>());
        mcSolver.setCharacter(1);
        assertEquals(3, mcSolver.getPossibleCodes().size());
        try {
            assertEquals("\nYou are at the first stage and thus cannot go back a stage.", mcSolver.solve());
            mcSolver.setCharacter(3);
            assertEquals("\nReverted to previous 'character' of the Morse Code module.", mcSolver.solve());
            assertEquals(2, mcSolver.getCharacter());
            assertEquals(2, mcSolver.getPossibleCodes().size());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testReset() {
        mcSolver.setInput("done");
        mcSolver.getPossibleCodes().add(new HashSet<>());
        mcSolver.getPossibleCodes().add(new HashSet<>());
        mcSolver.setCharacter(3);
        assertEquals(3, mcSolver.getPossibleCodes().size());
        try {
            assertEquals("\nMemory reset, ready for the next Morse Code module.", mcSolver.solve());
            assertEquals(1, mcSolver.getCharacter());
            assertEquals(1, mcSolver.getPossibleCodes().size());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveException() {
        mcSolver.setInput("..--");
        try {
            mcSolver.solve();
            fail();
        } catch (OutOfBoundsException e) {
            assertEquals("\nYou did not input a valid morse letter, please try again.", e.getMessage());
        }
    }

    @Test
    public void testSolveNoneFoundFirstChar() {
        mcSolver.setInput(".-");
        try {
            assertEquals("\nNo possible codes were found with the given inputs, either go back characters or reset and try again.",
                    mcSolver.solve());
            mcSolver.setInput("-.-.");
            assertEquals("\nNo possible codes were found with the given inputs, either go back characters or reset and try again.",
                    mcSolver.solve());
            mcSolver.setInput("....");
            assertEquals("\nNo possible codes were found with the given inputs, either go back characters or reset and try again.",
                    mcSolver.solve());
            mcSolver.setInput(".--.");
            assertEquals("\nNo possible codes were found with the given inputs, either go back characters or reset and try again.",
                    mcSolver.solve());
            mcSolver.setInput("--");
            assertEquals("\nNo possible codes were found with the given inputs, either go back characters or reset and try again.",
                    mcSolver.solve());
            mcSolver.setInput("..-");
            assertEquals("\nNo possible codes were found with the given inputs, either go back characters or reset and try again.",
                    mcSolver.solve());
            mcSolver.setInput("--..");
            assertEquals("\nNo possible codes were found with the given inputs, either go back characters or reset and try again.",
                    mcSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveNoneFoundSecondChar() {
        mcSolver.setInput("...");
        try {
            assertEquals("\nMorse character inputted successfully.", mcSolver.solve());
            mcSolver.setInput("..-.");
            assertEquals("\nNo possible codes were found with the given inputs, either go back characters or reset and try again.",
                    mcSolver.solve());
            mcSolver.setInput("...-");
            assertEquals("\nNo possible codes were found with the given inputs, either go back characters or reset and try again.",
                    mcSolver.solve());
            mcSolver.setInput("done");
            mcSolver.solve();
            mcSolver.setInput("-...");
            assertEquals("\nMorse character inputted successfully.", mcSolver.solve());
            mcSolver.setInput("..-.");
            assertEquals("\nNo possible codes were found with the given inputs, either go back characters or reset and try again.",
                    mcSolver.solve());
            mcSolver.setInput("...-");
            assertEquals("\nNo possible codes were found with the given inputs, either go back characters or reset and try again.",
                    mcSolver.solve());
            mcSolver.setInput("...");
            assertEquals("\nNo possible codes were found with the given inputs, either go back characters or reset and try again.",
                    mcSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveNoneFoundThirdChar() {
        mcSolver.setInput("...");
        try {
            assertEquals("\nMorse character inputted successfully.", mcSolver.solve());
            mcSolver.setInput("-");
            assertEquals("\nMorse character inputted successfully.", mcSolver.solve());
            mcSolver.setInput("....");
            assertEquals("\nNo possible codes were found with the given inputs, either go back characters or reset and try again.",
                    mcSolver.solve());
            mcSolver.setInput(".-..");
            assertEquals("\nNo possible codes were found with the given inputs, either go back characters or reset and try again.",
                    mcSolver.solve());
            mcSolver.setInput("done");
            mcSolver.solve();
            mcSolver.setInput("-...");
            assertEquals("\nMorse character inputted successfully.", mcSolver.solve());
            mcSolver.setInput("---");
            assertEquals("\nMorse character inputted successfully.", mcSolver.solve());
            mcSolver.setInput("-");
            assertEquals("\nNo possible codes were found with the given inputs, either go back characters or reset and try again.",
                    mcSolver.solve());
            mcSolver.setInput("..");
            assertEquals("\nNo possible codes were found with the given inputs, either go back characters or reset and try again.",
                    mcSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveFoundFirstChar() {
        mcSolver.setInput("....");
        try {
            assertEquals("\nRespond at frequency 3.515 MHz.\n\nMemory reset, ready for the next Morse Code module.",
                    mcSolver.solve());
            mcSolver.setInput("-");
            assertEquals("\nRespond at frequency 3.532 MHz.\n\nMemory reset, ready for the next Morse Code module.",
                    mcSolver.solve());
            mcSolver.setInput(".-..");
            assertEquals("\nRespond at frequency 3.542 MHz.\n\nMemory reset, ready for the next Morse Code module.",
                    mcSolver.solve());
            mcSolver.setInput("..-.");
            assertEquals("\nRespond at frequency 3.555 MHz.\n\nMemory reset, ready for the next Morse Code module.",
                    mcSolver.solve());
            mcSolver.setInput("...-");
            assertEquals("\nRespond at frequency 3.595 MHz.\n\nMemory reset, ready for the next Morse Code module.",
                    mcSolver.solve());
            assertTrue(mcSolver.getSolved());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveFoundSecondChar() {
        mcSolver.setInput("...");
        try {
            mcSolver.solve();
            mcSolver.setInput("....");
            assertEquals("\nRespond at frequency 3.505 MHz.\n\nMemory reset, ready for the next Morse Code module.",
                    mcSolver.solve());
            mcSolver.setInput("...");
            mcSolver.solve();
            mcSolver.setInput(".-..");
            assertEquals("\nRespond at frequency 3.522 MHz.\n\nMemory reset, ready for the next Morse Code module.",
                    mcSolver.solve());
            mcSolver.setInput("-...");
            mcSolver.solve();
            mcSolver.setInput("..");
            assertEquals("\nRespond at frequency 3.552 MHz.\n\nMemory reset, ready for the next Morse Code module.",
                    mcSolver.solve());
            mcSolver.setInput("-...");
            mcSolver.solve();
            mcSolver.setInput(".");
            assertEquals("\nRespond at frequency 3.6 MHz.\n\nMemory reset, ready for the next Morse Code module.",
                    mcSolver.solve());
            assertTrue(mcSolver.getSolved());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveFoundThirdCharS() {
        mcSolver.setInput("...");
        try {
            mcSolver.solve();
            mcSolver.setInput("-");
            mcSolver.solve();
            mcSolver.setInput(".-.");
            assertEquals("\nRespond at frequency 3.545 MHz.\n\nMemory reset, ready for the next Morse Code module.",
                    mcSolver.solve());
            mcSolver.setInput("...");
            mcSolver.solve();
            mcSolver.setInput("-");
            mcSolver.solve();
            mcSolver.setInput(".");
            assertEquals("\nRespond at frequency 3.582 MHz.\n\nMemory reset, ready for the next Morse Code module.",
                    mcSolver.solve());
            mcSolver.setInput("...");
            mcSolver.solve();
            mcSolver.setInput("-");
            mcSolver.solve();
            mcSolver.setInput("..");
            assertEquals("\nRespond at frequency 3.592 MHz.\n\nMemory reset, ready for the next Morse Code module.",
                    mcSolver.solve());
            assertTrue(mcSolver.getSolved());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveFoundThirdCharB() {
        mcSolver.setInput("-...");
        try {
            mcSolver.solve();
            mcSolver.setInput("---");
            mcSolver.solve();
            mcSolver.setInput("-..-");
            assertEquals("\nRespond at frequency 3.535 MHz.\n\nMemory reset, ready for the next Morse Code module.",
                    mcSolver.solve());
            mcSolver.setInput("-...");
            mcSolver.solve();
            mcSolver.setInput("---");
            mcSolver.solve();
            mcSolver.setInput("--");
            assertEquals("\nRespond at frequency 3.565 MHz.\n\nMemory reset, ready for the next Morse Code module.",
                    mcSolver.solve());
            mcSolver.setInput("-...");
            mcSolver.solve();
            mcSolver.setInput(".-.");
            mcSolver.solve();
            mcSolver.setInput(".");
            assertEquals("\nRespond at frequency 3.572 MHz.\n\nMemory reset, ready for the next Morse Code module.",
                    mcSolver.solve());
            mcSolver.setInput("-...");
            mcSolver.solve();
            mcSolver.setInput(".-.");
            mcSolver.solve();
            mcSolver.setInput("..");
            assertEquals("\nRespond at frequency 3.575 MHz.\n\nMemory reset, ready for the next Morse Code module.",
                    mcSolver.solve());
            assertTrue(mcSolver.getSolved());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }
}
