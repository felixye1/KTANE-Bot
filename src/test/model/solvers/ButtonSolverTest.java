package model.solvers;

import model.Bomb;
import model.exceptions.OutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ButtonSolverTest {
    private ButtonSolver buttonSolver;

    @BeforeEach
    public void setup() {
        buttonSolver = ButtonSolver.getInstance();
    }

    @Test
    public void testConstructor() {
        assertEquals(buttonSolver.getBomb(), Bomb.getInstance());
    }

    @Test
    public void testSetInput() {
        buttonSolver.setInput("black");
        assertEquals("black", buttonSolver.getInput());
        buttonSolver.setInput(" white");
        assertEquals(" white", buttonSolver.getInput());
    }

    @Test
    public void testExit() {
        buttonSolver.setInput("exit");
        try {
            assertEquals("\nExited module.", buttonSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveException() {
        buttonSolver.setInput("blue abort trash");
        try {
            buttonSolver.solve();
            fail("Exception should have been thrown.");
        } catch (OutOfBoundsException e) {
            assertEquals("\nInput is invalid, please try again.", e.getMessage());
            buttonSolver.setInput("blue");
            try {
                buttonSolver.solve();
            } catch (OutOfBoundsException e2) {
                assertEquals("\nInput is invalid, please try again.", e2.getMessage());
            }
        }
    }

    @Test
    public void testSolveSuccess() {
        try {
            buttonSolver.setInput("blue abort");
            assertEquals("\nHold the button and identify the color of the colored strip."
                    + "Based on the colour, release when the countdown timer has the specified number in any position:"
                    + "\n\nBlue: 4\t Yellow: 5\t Other: 1", buttonSolver.solve());
            buttonSolver.getBomb().setNumBatteries(2);
            buttonSolver.setInput("blue detonate");
            assertEquals("\nPress and immediately release the button.", buttonSolver.solve());
            buttonSolver.getBomb().setNumBatteries(1);
            buttonSolver.setInput("white detonate");
            buttonSolver.getBomb().setCar(true);
            assertEquals("\nHold the button and identify the color of the colored strip."
                    + "Based on the colour, release when the countdown timer has the specified number in any position:"
                    + "\n\nBlue: 4\t Yellow: 5\t Other: 1", buttonSolver.solve());
            buttonSolver.getBomb().setFrk(true);
            buttonSolver.getBomb().setNumBatteries(3);
            buttonSolver.setInput("yellow abort");
            assertEquals("\nPress and immediately release the button.", buttonSolver.solve());
            buttonSolver.getBomb().setNumBatteries(2);
            assertEquals("\nHold the button and identify the color of the colored strip."
                    + "Based on the colour, release when the countdown timer has the specified number in any position:"
                    + "\n\nBlue: 4\t Yellow: 5\t Other: 1", buttonSolver.solve());
            buttonSolver.setInput("red hold");
            assertEquals("\nPress and immediately release the button.", buttonSolver.solve());
            buttonSolver.setInput("red abort");
            assertEquals("\nHold the button and identify the color of the colored strip."
                    + "Based on the colour, release when the countdown timer has the specified number in any position:"
                    + "\n\nBlue: 4\t Yellow: 5\t Other: 1", buttonSolver.solve());
        } catch (OutOfBoundsException e) {
            fail("Exception should not be thrown.");
        }
    }
}
