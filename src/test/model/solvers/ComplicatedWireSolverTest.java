package model.solvers;

import model.Bomb;
import model.exceptions.OutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComplicatedWireSolverTest {
    private ComplicatedWireSolver cwSolver;

    @BeforeEach
    public void setup () {
        cwSolver = ComplicatedWireSolver.getInstance();
        cwSolver.setInput(null);
        cwSolver.setKey(null);
    }

    @Test
    public void testConstructor() {
        assertNull(cwSolver.getKey());
        assertEquals(cwSolver.getBomb(), Bomb.getInstance());
    }

    @Test
    public void testSetKey() {
        cwSolver.setKey(new ArrayList<>(List.of("test")));
        assertEquals(new ArrayList<>(List.of("test")), cwSolver.getKey());
    }

    @Test
    public void testExit() {
        cwSolver.setInput("exit");
        try {
            assertEquals("\nExited module.", cwSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveException() {
        cwSolver.setInput("dummy");
        try {
            cwSolver.solve();
        } catch (OutOfBoundsException e) {
            assertEquals("\nInput is invalid, please try again.", e.getMessage());
        }
    }

    @Test
    public void testSolveAlwaysCut() {
        cwSolver.setInput("n s sr");
        try {
            cwSolver.getBomb().setLastDigit(3);
            cwSolver.getBomb().setNumBatteries(4);
            cwSolver.getBomb().setParallelPort(false);
            assertEquals("\nFollow whether to cut (in order of wires inputted): "
                    + "yes, yes, yes.", cwSolver.solve());
            cwSolver.getBomb().setLastDigit(4);
            cwSolver.getBomb().setNumBatteries(1);
            cwSolver.getBomb().setParallelPort(true);
            assertEquals("\nFollow whether to cut (in order of wires inputted): "
                    + "yes, yes, yes.", cwSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveNeverCut() {
        cwSolver.setInput("l sb srbl");
        try {
            cwSolver.getBomb().setLastDigit(3);
            cwSolver.getBomb().setNumBatteries(4);
            cwSolver.getBomb().setParallelPort(false);
            cwSolver.updateKey();
            assertEquals("\nFollow whether to cut (in order of wires inputted): "
                    + "no, no, no.", cwSolver.solve());
            cwSolver.getBomb().setLastDigit(4);
            cwSolver.getBomb().setNumBatteries(1);
            cwSolver.getBomb().setParallelPort(true);
            cwSolver.updateKey();
            assertEquals("\nFollow whether to cut (in order of wires inputted): "
                    + "no, no, no.", cwSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveSerial() {
        cwSolver.setInput("r b br rbl");
        try {
            cwSolver.getBomb().setLastDigit(3);
            cwSolver.getBomb().setNumBatteries(4);
            cwSolver.getBomb().setParallelPort(false);
            cwSolver.updateKey();
            assertEquals("\nFollow whether to cut (in order of wires inputted): "
                    + "no, no, no, no.", cwSolver.solve());
            cwSolver.getBomb().setLastDigit(4);
            cwSolver.getBomb().setNumBatteries(1);
            cwSolver.getBomb().setParallelPort(true);
            cwSolver.updateKey();
            assertEquals("\nFollow whether to cut (in order of wires inputted): "
                    + "yes, yes, yes, yes.", cwSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveBatteries() {
        cwSolver.setInput("rl sl srl");
        try {
            cwSolver.getBomb().setLastDigit(3);
            cwSolver.getBomb().setNumBatteries(4);
            cwSolver.getBomb().setParallelPort(false);
            cwSolver.updateKey();
            assertEquals("\nFollow whether to cut (in order of wires inputted): "
                    + "yes, yes, yes.", cwSolver.solve());
            cwSolver.getBomb().setLastDigit(4);
            cwSolver.getBomb().setNumBatteries(1);
            cwSolver.getBomb().setParallelPort(true);
            cwSolver.updateKey();
            assertEquals("\nFollow whether to cut (in order of wires inputted): "
                    + "no, no, no.", cwSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolvePort() {
        cwSolver.setInput("bl sbl srb");
        try {
            cwSolver.getBomb().setLastDigit(3);
            cwSolver.getBomb().setNumBatteries(4);
            cwSolver.getBomb().setParallelPort(false);
            cwSolver.updateKey();
            assertEquals("\nFollow whether to cut (in order of wires inputted): "
                    + "no, no, no.", cwSolver.solve());
            cwSolver.getBomb().setLastDigit(4);
            cwSolver.getBomb().setNumBatteries(1);
            cwSolver.getBomb().setParallelPort(true);
            cwSolver.updateKey();
            assertEquals("\nFollow whether to cut (in order of wires inputted): "
                    + "yes, yes, yes.", cwSolver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }
}
