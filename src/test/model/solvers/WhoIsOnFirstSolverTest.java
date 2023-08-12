package model.solvers;

import model.exceptions.OutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WhoIsOnFirstSolverTest {
    private WhoIsOnFirstSolver wfSolver;

    @BeforeEach
    public void setup() {
        wfSolver = WhoIsOnFirstSolver.getInstance();
    }

    @Test
    public void testSolveExit() {
        wfSolver.setInput("exit");
        try {
            assertEquals("\nExited module.", wfSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveException() {
        wfSolver.setInput("first no blank nothing yes what");
        try {
            wfSolver.solve();
            fail("Exception should have been thrown.");
        } catch (OutOfBoundsException e) {
            assertEquals("\nYour input should have 7 words (the display and 6 buttons)"
                    + ", please try again.", e.getMessage());
            wfSolver.setInput("first no blank nothing yes what uhhh left");
            try {
                wfSolver.solve();
                fail();
            } catch (OutOfBoundsException e2) {
                assertEquals("\nYour input should have 7 words (the display and 6 buttons)"
                        + ", please try again.", e2.getMessage());
                wfSolver.setInput("dummy first no blank nothing yes what");
                try {
                    wfSolver.solve();
                    fail();
                } catch (OutOfBoundsException e3) {
                    assertEquals("\nOne or more of your inputs were not typed in the correct format or "
                            + "spelling, please try again.", e3.getMessage());
                }
            }
        }
    }

    @Test
    public void testFindLabelException() {
        wfSolver.setInput("ur dummy no blank nothing yes what");
        try {
            assertEquals("\nOne or more of your inputs were not typed in the correct format or "
                    + "spelling, please try again.", wfSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveCaseTopLeft() {
        try {
            wfSolver.setInput("ur ready left right u uhuh sure");
            assertEquals("\nPress the button with the label \"left\".", wfSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveCaseTopRight() {
        try {
            wfSolver.setInput("first yes first wait what? done hold");
            assertEquals("\nPress the button with the label \"yes\".", wfSolver.solve());
            wfSolver.setInput("okay wait no blank uhhuh ur u ");
            assertEquals("\nPress the button with the label \"blank\".", wfSolver.solve());
            wfSolver.setInput("c middle blank uhhuh sure like youare");
            assertEquals("\nPress the button with the label \"middle\".", wfSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveCaseMiddleLeft() {
        try {
            wfSolver.setInput("yes press your nothing u uhhuh like");
            assertEquals("\nPress the button with the label \"press\".", wfSolver.solve());
            wfSolver.setInput("nothing wait ur yes press done next");
            assertEquals("\nPress the button with the label \"press\".", wfSolver.solve());
            wfSolver.setInput("led right middle what you youare uhhuh");
            assertEquals("\nPress the button with the label \"what\".", wfSolver.solve());
            wfSolver.setInput("theyare press ur uhhh what? done what");
            assertEquals("\nPress the button with the label \"what\".", wfSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveCaseMiddleRight() {
        try {
            wfSolver.setInput("blank middle okay wait left press ur");
            assertEquals("\nPress the button with the label \"left\".", wfSolver.solve());
            wfSolver.setInput("read done like ur right left middle");
            assertEquals("\nPress the button with the label \"right\".", wfSolver.solve());
            wfSolver.setInput("red wait your youre middle uhuh uhhuh");
            assertEquals("\nPress the button with the label \"wait\".", wfSolver.solve());
            wfSolver.setInput("you done ur press okay what? sure");
            assertEquals("\nPress the button with the label \"okay\".", wfSolver.solve());
            wfSolver.setInput("your ur uhhuh uhuh wait ready blank");
            assertEquals("\nPress the button with the label \"blank\".", wfSolver.solve());
            wfSolver.setInput("youre u ur hold press like nothing");
            assertEquals("\nPress the button with the label \"press\".", wfSolver.solve());
            wfSolver.setInput("their what? uhhuh hold you ready yes");
            assertEquals("\nPress the button with the label \"uhhuh\".", wfSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveCaseBottomLeft() {
        try {
            wfSolver.setInput("empty first uhhh left okay youare done");
            assertEquals("\nPress the button with the label \"done\".", wfSolver.solve());
            wfSolver.setInput("reed ready yes what wait your u");
            assertEquals("\nPress the button with the label \"your\".", wfSolver.solve());
            wfSolver.setInput("leed blank nothing press you youre uhuh");
            assertEquals("\nPress the button with the label \"you\".", wfSolver.solve());
            wfSolver.setInput("theyre left right wait uhhuh ur sure");
            assertEquals("\nPress the button with the label \"ur\".", wfSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveCaseBottomRight() {
        try {
            wfSolver.setInput("display ready uhhh left youare what? u");
            assertEquals("\nPress the button with the label \"what?\".", wfSolver.solve());
            wfSolver.setInput("says first nothing middle okay youre uhhuh");
            assertEquals("\nPress the button with the label \"uhhuh\".", wfSolver.solve());
            wfSolver.setInput("no blank what uhhh press hold uhuh");
            assertEquals("\nPress the button with the label \"uhuh\".", wfSolver.solve());
            wfSolver.setInput("lead yes what wait your like what?");
            assertEquals("\nPress the button with the label \"your\".", wfSolver.solve());
            wfSolver.setInput("holdon no blank what uhhh uhhuh done");
            assertEquals("\nPress the button with the label \"uhhuh\".", wfSolver.solve());
            wfSolver.setInput("youare okay left uhhh u sure next");
            assertEquals("\nPress the button with the label \"sure\".", wfSolver.solve());
            wfSolver.setInput("there right middle okay nothing done hold");
            assertEquals("\nPress the button with the label \"done\".", wfSolver.solve());
            wfSolver.setInput("see uhhh blank no ur your sure");
            assertEquals("\nPress the button with the label \"ur\".", wfSolver.solve());
            wfSolver.setInput("cee sure hold wait press what like");
            assertEquals("\nPress the button with the label \"hold\".", wfSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }
}
