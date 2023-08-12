package model.solvers;

import model.Bomb;
import model.exceptions.OutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleWireSolverTest {
    private SimpleWireSolver solver;

    @BeforeEach
    public void setup() {
        solver = SimpleWireSolver.getInstance();
    }

    @Test
    public void testConstructor() {
        assertEquals(solver.getBomb(), Bomb.getInstance());
    }

    @Test
    public void testExit() {
        solver.setInput("exit");
        try {
            assertEquals("\nExited module.", solver.solve());
        } catch (OutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testSolveException() {
        solver.setInput("red red");
        try {
            solver.solve();
            fail("Exception should have been thrown.");
        } catch (OutOfBoundsException e) {
            assertEquals("\nThe module cannot have the specified amount of wires, please try again.", e.getMessage());
            solver.setInput("red red red black black white blue");
            try {
                solver.solve();
            } catch (OutOfBoundsException e2) {
                assertEquals("\nThe module cannot have the specified amount of wires, please try again.",
                        e2.getMessage());
            }
        }
    }

    @Test
    public void testSolve3Wires() {
        solver.setInput(" blue blUe white");
        try {
            assertEquals("\nCut the second wire.", solver.solve());
            solver.setInput(" red blue white");
            assertEquals("\nCut the last wire.", solver.solve());
            solver.setInput("blue blue red    ");
            assertEquals("\nCut the second wire.", solver.solve());
            solver.setInput("   red white blue    ");
            assertEquals("\nCut the last wire.", solver.solve());
        } catch (OutOfBoundsException e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    public void testSolve4Wires() {
        solver.setInput(" blue red Red yellow");
        try {
            solver.getBomb().setLastDigit(9);
            assertEquals("\nCut the third wire.", solver.solve());
            solver.setInput(" blue red Red red");
            assertEquals("\nCut the last wire.", solver.solve());
            solver.setInput(" red red blue yellow");
            assertEquals("\nCut the second wire.", solver.solve());
            solver.setInput(" blue yellow yellow yellow");
            assertEquals("\nCut the first wire.", solver.solve());
            solver.setInput("blue red Red yellow    ");
            solver.getBomb().setLastDigit(8);
            assertEquals("\nCut the first wire.", solver.solve());
            solver.setInput("   yellow yellow blue blue    ");
            assertEquals("\nCut the last wire.", solver.solve());
            solver.setInput("red blue bLue red ");
            assertEquals("\nCut the second wire.", solver.solve());
        } catch (OutOfBoundsException e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    public void testSolve5Wires() {
        solver.setInput(" yellow yellow red black black");
        try {
            solver.getBomb().setLastDigit(3);
            assertEquals("\nCut the fourth wire.", solver.solve());
            solver.getBomb().setLastDigit(4);
            assertEquals("\nCut the first wire.", solver.solve());
            solver.setInput("yellow yellow Red red blue    ");
            assertEquals("\nCut the second wire.", solver.solve());
            solver.setInput("   yellow Red blue blue black    ");
            assertEquals("\nCut the first wire.", solver.solve());
        } catch (OutOfBoundsException e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    public void testSolve6Wires() {
        solver.setInput(" blue blue blue blue Blue black");
        try {
            solver.getBomb().setLastDigit(3);
            assertEquals("\nCut the third wire.", solver.solve());
            solver.setInput("white white yellow white white white");
            assertEquals("\nCut the fourth wire.", solver.solve());
            solver.setInput("blue blue blue blue blue black    ");
            solver.getBomb().setLastDigit(4);
            assertEquals("\nCut the last wire.", solver.solve());
            solver.setInput("   blue blue yellow white red blue    ");
            assertEquals("\nCut the fourth wire.", solver.solve());
        } catch (OutOfBoundsException e) {
            fail("Exception should not have been thrown.");
        }
    }
}
