package model.solvers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeypadSolverTest {
    private KeypadSolver keSolver;

    @BeforeEach
    public void setup() {
        keSolver = KeypadSolver.getInstance();
        keSolver.getSelected().clear();
    }

    @Test
    public void testConstructorAndFinals() {
        assertEquals(4, keSolver.getNumKeys());
        assertEquals(0, keSolver.getSelected().size());
        assertEquals(Set.of(
                "src/main/symbols/ae.png", "src/main/symbols/at.png", "src/main/symbols/blackstar.png",
                "src/main/symbols/bt.png", "src/main/symbols/cdotleft.png", "src/main/symbols/cdotright.png",
                "src/main/symbols/copyright.png", "src/main/symbols/eumlat.png", "src/main/symbols/hy.png",
                "src/main/symbols/lambda.png", "src/main/symbols/lightning.png", "src/main/symbols/loop.png",
                "src/main/symbols/mirror.png", "src/main/symbols/nhat.png", "src/main/symbols/omega.png",
                "src/main/symbols/paragraph.png", "src/main/symbols/pumpkin.png", "src/main/symbols/puzzle.png",
                "src/main/symbols/question.png", "src/main/symbols/six.png", "src/main/symbols/smiley.png",
                "src/main/symbols/three.png", "src/main/symbols/trident.png", "src/main/symbols/tripod.png",
                "src/main/symbols/whitestar.png", "src/main/symbols/worm.png", "src/main/symbols/xi.png"
        ), keSolver.getImageFiles());
    }

    @Test
    public void testSolveError() {
        keSolver.getSelected().addAll(List.of("src/main/symbols/ae.png", "src/main/symbols/at.png",
                "src/main/symbols/blackstar.png", "src/main/symbols/bt.png"));
        assertEquals(0, keSolver.solve().size());
    }

    @Test
    public void testSolve() {
        keSolver.getSelected().addAll(List.of("src/main/symbols/mirror.png", "src/main/symbols/cdotleft.png",
                "src/main/symbols/tripod.png", "src/main/symbols/lambda.png"));
        assertEquals(List.of("src/main/symbols/mirror.png", "src/main/symbols/lambda.png",
                "src/main/symbols/tripod.png", "src/main/symbols/cdotleft.png"), keSolver.solve());
        keSolver.getSelected().clear();
        keSolver.getSelected().addAll(List.of("src/main/symbols/whitestar.png", "src/main/symbols/loop.png",
                "src/main/symbols/hy.png", "src/main/symbols/eumlat.png"));
        assertEquals(List.of("src/main/symbols/eumlat.png", "src/main/symbols/loop.png",
                "src/main/symbols/whitestar.png", "src/main/symbols/hy.png"), keSolver.solve());
        keSolver.getSelected().clear();
        keSolver.getSelected().addAll(List.of("src/main/symbols/xi.png", "src/main/symbols/three.png",
                "src/main/symbols/pumpkin.png", "src/main/symbols/copyright.png"));
        assertEquals(List.of("src/main/symbols/copyright.png", "src/main/symbols/pumpkin.png",
                "src/main/symbols/xi.png", "src/main/symbols/three.png"), keSolver.solve());
        keSolver.getSelected().clear();
        keSolver.getSelected().addAll(List.of("src/main/symbols/smiley.png", "src/main/symbols/bt.png",
                "src/main/symbols/six.png", "src/main/symbols/paragraph.png"));
        assertEquals(List.of("src/main/symbols/six.png", "src/main/symbols/paragraph.png",
                "src/main/symbols/bt.png", "src/main/symbols/smiley.png"), keSolver.solve());
        keSolver.getSelected().clear();
        keSolver.getSelected().addAll(List.of("src/main/symbols/cdotright.png", "src/main/symbols/worm.png",
                "src/main/symbols/trident.png", "src/main/symbols/blackstar.png"));
        assertEquals(List.of("src/main/symbols/trident.png", "src/main/symbols/cdotright.png",
                "src/main/symbols/worm.png", "src/main/symbols/blackstar.png"), keSolver.solve());
        keSolver.getSelected().clear();
        keSolver.getSelected().addAll(List.of("src/main/symbols/ae.png", "src/main/symbols/omega.png",
                "src/main/symbols/nhat.png", "src/main/symbols/puzzle.png"));
        assertEquals(List.of("src/main/symbols/puzzle.png", "src/main/symbols/ae.png",
                "src/main/symbols/nhat.png", "src/main/symbols/omega.png"), keSolver.solve());
    }
}
