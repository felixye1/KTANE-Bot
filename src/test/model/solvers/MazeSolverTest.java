package model.solvers;

import model.exceptions.OutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MazeSolverTest {
    private MazeSolver mzSolver;

    @BeforeEach
    public void setup() {
        mzSolver = MazeSolver.getInstance();
    }

    @Test
    public void testExit() {
        mzSolver.setInput("exit");
        try {
            assertEquals("\nExited module.", mzSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveInvalidFormat() {
        mzSolver.setInput("12 63 42");
        try {
            mzSolver.solve();
            fail();
        } catch (OutOfBoundsException e) {
            assertEquals("\nYour input should have 4 two-digit numbers (both circles, target, "
                    + "your location), please try again.", e.getMessage());
        }
    }

    @Test
    public void testSolveIncorrectInput() {
        mzSolver.setInput("11 63 11 66");
        try {
            mzSolver.solve();
            fail();
        } catch (OutOfBoundsException e) {
            assertEquals("\nOne or more of your inputs were not correct, please try again.",
                    e.getMessage());
            mzSolver.setInput("12 63 11 322");
            try {
                mzSolver.solve();
                fail();
            } catch (OutOfBoundsException e2) {
                assertEquals("\nOne or more of your inputs were not correct, please try again.",
                        e2.getMessage());
            }
        }
    }

    @Test
    public void testSolvePathNotFound() {
        mzSolver.setInput("12 63 112 66");
        try {
            mzSolver.solve();
            fail();
        } catch (OutOfBoundsException e) {
            assertEquals("\nA path could not be found, please check your inputs and try again.",
                    e.getMessage());
        }
    }

    @Test
    public void testSolve() {
        try {
            mzSolver.setInput("12 63 52 34");
            assertEquals("\nMove (in this order): " +
                    "right, up, right, right, up, left.", mzSolver.solve());
            mzSolver.setInput("24 52 25 63");
            assertEquals("\nMove (in this order): " +
                    "left, left, down, left, down, down, left, up.", mzSolver.solve());
            mzSolver.setInput("44 64 32 14");
            assertEquals("\nMove (in this order): " +
                    "up, right, down, down, right, up, up, up.", mzSolver.solve());
            mzSolver.setInput("11 14 63 42");
            assertEquals("\nMove (in this order): " +
                    "right, right, down.", mzSolver.solve());
            mzSolver.setInput("46 53 22 16");
            assertEquals("\nMove (in this order): " +
                    "up, up, up, up, right.", mzSolver.solve());
            mzSolver.setInput("35 51 35 21");
            assertEquals("\nMove (in this order): " +
                    "down, down, left, down, right, down, " +
                    "left, down, right, right, right, up, up, left, down.", mzSolver.solve());
            mzSolver.setInput("21 26 12 51");
            assertEquals("\nMove (in this order): " +
                    "down, left, up, left, left, left, down.", mzSolver.solve());
            mzSolver.setInput("34 41 53 35");
            assertEquals("\nMove (in this order): " +
                    "up, left, up, right, right, right.", mzSolver.solve());
            mzSolver.setInput("15 32 61 44");
            assertEquals("\nMove (in this order): " +
                    "up, right, up, up, right.", mzSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }
}
