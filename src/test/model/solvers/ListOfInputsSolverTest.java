package model.solvers;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ListOfInputsSolverTest {
    private ListOfInputsSolver listSolver;

    @BeforeEach
    public void setup() {
        listSolver = SimpleWireSolver.getInstance();
        listSolver.input = null;
    }

    @Test
    @Order(1)
    public void testConstructor() {
        assertNull(listSolver.getInput());
    }

    @Test
    public void testSetInput() {
        listSolver.setInput("black");
        assertEquals("black", listSolver.getInput());
        listSolver.setInput(" white");
        assertEquals(" white", listSolver.getInput());
    }

    @Test
    public void testConvert() {
        listSolver.input = "";
        assertEquals(0, listSolver.convert(" ").size());
        listSolver.input = "   black white";
        ArrayList<String> strings = new ArrayList<>();
        strings.add("black");
        strings.add("white");
        assertEquals(strings, listSolver.convert(" "));
        listSolver.input = "   black white    ";
        assertEquals(strings, listSolver.convert(" "));
        listSolver.input = "black white red blue white";
        strings.add("red");
        strings.add("blue");
        strings.add("white");
        assertEquals(strings, listSolver.convert(" "));
    }
}
