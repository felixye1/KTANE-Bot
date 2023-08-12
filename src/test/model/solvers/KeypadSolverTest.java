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
                "symbols/ae.png", "symbols/at.png", "symbols/blackstar.png", "symbols/bt.png",
                "symbols/cdotleft.png", "symbols/cdotright.png", "symbols/copyright.png",
                "symbols/eumlat.png", "symbols/hy.png", "symbols/lambda.png",
                "symbols/lightning.png", "symbols/loop.png", "symbols/mirror.png", "symbols/nhat.png",
                "symbols/omega.png", "symbols/paragraph.png", "symbols/pumpkin.png",
                "symbols/puzzle.png", "symbols/question.png", "symbols/six.png", "symbols/smiley.png",
                "symbols/three.png", "symbols/trident.png", "symbols/tripod.png",
                "symbols/whitestar.png", "symbols/worm.png", "symbols/xi.png"), keSolver.getImageFiles());
    }

    @Test
    public void testSolveError() {
        keSolver.getSelected().addAll(List.of("symbols/ae.png", "symbols/at.png",
                "symbols/blackstar.png", "symbols/bt.png"));
        assertEquals(0, keSolver.solve().size());
    }

    @Test
    public void testSolve() {
        keSolver.getSelected().addAll(List.of("symbols/mirror.png", "symbols/cdotleft.png",
                "symbols/tripod.png", "symbols/lambda.png"));
        assertEquals(List.of("symbols/mirror.png", "symbols/lambda.png",
                "symbols/tripod.png", "symbols/cdotleft.png"), keSolver.solve());
        keSolver.getSelected().clear();
        keSolver.getSelected().addAll(List.of("symbols/whitestar.png", "symbols/loop.png",
                "symbols/hy.png", "symbols/eumlat.png"));
        assertEquals(List.of("symbols/eumlat.png", "symbols/loop.png",
                "symbols/whitestar.png", "symbols/hy.png"), keSolver.solve());
        keSolver.getSelected().clear();
        keSolver.getSelected().addAll(List.of("symbols/xi.png", "symbols/three.png",
                "symbols/pumpkin.png", "symbols/copyright.png"));
        assertEquals(List.of("symbols/copyright.png", "symbols/pumpkin.png",
                "symbols/xi.png", "symbols/three.png"), keSolver.solve());
        keSolver.getSelected().clear();
        keSolver.getSelected().addAll(List.of("symbols/smiley.png", "symbols/bt.png",
                "symbols/six.png", "symbols/paragraph.png"));
        assertEquals(List.of("symbols/six.png", "symbols/paragraph.png",
                "symbols/bt.png", "symbols/smiley.png"), keSolver.solve());
        keSolver.getSelected().clear();
        keSolver.getSelected().addAll(List.of("symbols/cdotright.png", "symbols/worm.png",
                "symbols/trident.png", "symbols/blackstar.png"));
        assertEquals(List.of("symbols/trident.png", "symbols/cdotright.png",
                "symbols/worm.png", "symbols/blackstar.png"), keSolver.solve());
        keSolver.getSelected().clear();
        keSolver.getSelected().addAll(List.of("symbols/ae.png", "symbols/omega.png",
                "symbols/nhat.png", "symbols/puzzle.png"));
        assertEquals(List.of("symbols/puzzle.png", "symbols/ae.png",
                "symbols/nhat.png", "symbols/omega.png"), keSolver.solve());
    }
}
