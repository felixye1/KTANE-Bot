package model.solvers;

import model.Bomb;
import model.exceptions.OutOfBoundsException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SimonSaysSolverTest {
    private SimonSaysSolver ssSolver;

    @BeforeEach
    public void setup() {
        ssSolver = SimonSaysSolver.getInstance();
    }

    @Test
    @Order(1)
    public void testConstructor() {
        assertNull(ssSolver.getInput());
        assertEquals(ssSolver.getBomb(), Bomb.getInstance());
        assertEquals(0, ssSolver.getOutput().size());
    }

    @Test
    public void testSetInput() {
        ssSolver.setInput("black");
        assertEquals("black", ssSolver.getInput());
        ssSolver.setInput(" white");
        assertEquals(" white", ssSolver.getInput());
    }

    @Test
    public void testExit() {
        ssSolver.setInput("exit");
        try {
            assertEquals("\nExited module.", ssSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testReset() {
        ssSolver.getOutput().add("test");
        ssSolver.setInput("done");
        try {
            assertEquals("\nMemory reset, ready for the next Simon Says module.", ssSolver.solve());
            assertEquals(0, ssSolver.getOutput().size());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testBack() {
        ssSolver.getOutput().clear();
        try {
            ssSolver.setInput("back");
            assertEquals("\nYou are at the first stage and thus cannot go back a stage.", ssSolver.solve());
            ssSolver.getOutput().add("test");
            assertEquals("\nReverted to previous stage of the Simon Says module.", ssSolver.solve());
            assertEquals(0, ssSolver.getOutput().size());
            ssSolver.getOutput().add("red");
            ssSolver.getOutput().add("blue");
            assertEquals("\nReverted to previous stage of the Simon Says module.", ssSolver.solve());
            assertEquals(1, ssSolver.getOutput().size());
            assertEquals("red", ssSolver.getOutput().get(0));
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveException() {
        ssSolver.setInput("dummy");
        try {
            ssSolver.solve();
            fail();
        } catch (OutOfBoundsException e) {
            assertEquals("\nInput is invalid, please try again.", e.getMessage());
        }
    }

    @Test
    public void testSolveCaseVowelNoStrikes() {
        try {
            ssSolver.getOutput().clear();
            ssSolver.getBomb().setStrikes(0);
            ssSolver.getBomb().setVowelInSerial(true);
            ssSolver.setInput("red");
            assertEquals("\nPress (in this order): blue.", ssSolver.solve());
            ssSolver.setInput("blue");
            assertEquals("\nPress (in this order): blue, red.", ssSolver.solve());
            ssSolver.setInput("green");
            assertEquals("\nPress (in this order): blue, red, yellow.", ssSolver.solve());
            ssSolver.setInput("yellow");
            assertEquals("\nPress (in this order): blue, red, yellow, green.", ssSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveCaseNoVowelNoStrikes() {
        try {
            ssSolver.getOutput().clear();
            ssSolver.getBomb().setStrikes(0);
            ssSolver.getBomb().setVowelInSerial(false);
            ssSolver.setInput("red");
            assertEquals("\nPress (in this order): blue.", ssSolver.solve());
            ssSolver.setInput("blue");
            assertEquals("\nPress (in this order): blue, yellow.", ssSolver.solve());
            ssSolver.setInput("green");
            assertEquals("\nPress (in this order): blue, yellow, green.", ssSolver.solve());
            ssSolver.setInput("yellow");
            assertEquals("\nPress (in this order): blue, yellow, green, red.", ssSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveCaseVowelOneStrike() {
        try {
            ssSolver.getOutput().clear();
            ssSolver.getBomb().setStrikes(1);
            ssSolver.getBomb().setVowelInSerial(true);
            ssSolver.setInput("red");
            assertEquals("\nPress (in this order): yellow.", ssSolver.solve());
            ssSolver.setInput("blue");
            assertEquals("\nPress (in this order): yellow, green.", ssSolver.solve());
            ssSolver.setInput("green");
            assertEquals("\nPress (in this order): yellow, green, blue.", ssSolver.solve());
            ssSolver.setInput("yellow");
            assertEquals("\nPress (in this order): yellow, green, blue, red.", ssSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveCaseNoVowelOneStrike() {
        try {
            ssSolver.getOutput().clear();
            ssSolver.getBomb().setStrikes(1);
            ssSolver.getBomb().setVowelInSerial(false);
            ssSolver.setInput("red");
            assertEquals("\nPress (in this order): red.", ssSolver.solve());
            ssSolver.setInput("blue");
            assertEquals("\nPress (in this order): red, blue.", ssSolver.solve());
            ssSolver.setInput("green");
            assertEquals("\nPress (in this order): red, blue, yellow.", ssSolver.solve());
            ssSolver.setInput("yellow");
            assertEquals("\nPress (in this order): red, blue, yellow, green.", ssSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveCaseVowelTwoStrikes() {
        try {
            ssSolver.getOutput().clear();
            ssSolver.getBomb().setStrikes(2);
            ssSolver.getBomb().setVowelInSerial(true);
            ssSolver.setInput("red");
            assertEquals("\nPress (in this order): green.", ssSolver.solve());
            ssSolver.setInput("blue");
            assertEquals("\nPress (in this order): green, red.", ssSolver.solve());
            ssSolver.setInput("green");
            assertEquals("\nPress (in this order): green, red, yellow.", ssSolver.solve());
            ssSolver.setInput("yellow");
            assertEquals("\nPress (in this order): green, red, yellow, blue.", ssSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveCaseNoVowelTwoStrikes() {
        try {
            ssSolver.getOutput().clear();
            ssSolver.getBomb().setStrikes(2);
            ssSolver.getBomb().setVowelInSerial(false);
            ssSolver.setInput("red");
            assertEquals("\nPress (in this order): yellow.", ssSolver.solve());
            ssSolver.setInput("blue");
            assertEquals("\nPress (in this order): yellow, green.", ssSolver.solve());
            ssSolver.setInput("green");
            assertEquals("\nPress (in this order): yellow, green, blue.", ssSolver.solve());
            ssSolver.setInput("yellow");
            assertEquals("\nPress (in this order): yellow, green, blue, red.", ssSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }
}
