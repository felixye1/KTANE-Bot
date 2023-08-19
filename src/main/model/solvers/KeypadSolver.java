package model.solvers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// represents a keypad module solver
public class KeypadSolver {
    private static final Set<String> IMAGE_FILES = Set.of(
            "symbols/ae.png", "symbols/at.png", "symbols/blackstar.png",
            "symbols/bt.png", "symbols/cdotleft.png", "symbols/cdotright.png",
            "symbols/copyright.png", "symbols/eumlat.png", "symbols/hy.png",
            "symbols/lambda.png", "symbols/lightning.png", "symbols/loop.png",
            "symbols/mirror.png", "symbols/nhat.png", "symbols/omega.png",
            "symbols/paragraph.png", "symbols/pumpkin.png", "symbols/puzzle.png",
            "symbols/question.png", "symbols/six.png", "symbols/smiley.png",
            "symbols/three.png", "symbols/trident.png", "symbols/tripod.png",
            "symbols/whitestar.png", "symbols/worm.png", "symbols/xi.png"
    );
    private static final int NUM_KEYS = 4;
    private static final Set<ArrayList<String>> IMAGE_KEYS = Set.of(
            new ArrayList<>(List.of("symbols/mirror.png", "symbols/at.png", "symbols/lambda.png",
                    "symbols/lightning.png", "symbols/tripod.png", "symbols/hy.png", "symbols/cdotleft.png")),
            new ArrayList<>(List.of("symbols/eumlat.png", "symbols/mirror.png", "symbols/cdotleft.png",
                    "symbols/loop.png", "symbols/whitestar.png", "symbols/hy.png", "symbols/question.png")),
            new ArrayList<>(List.of("symbols/copyright.png", "symbols/pumpkin.png", "symbols/loop.png",
                    "symbols/xi.png", "symbols/three.png", "symbols/lambda.png", "symbols/whitestar.png")),
            new ArrayList<>(List.of("symbols/six.png", "symbols/paragraph.png", "symbols/bt.png", "symbols/tripod.png",
                    "symbols/xi.png", "symbols/question.png", "symbols/smiley.png")),
            new ArrayList<>(List.of("symbols/trident.png", "symbols/smiley.png", "symbols/bt.png",
                    "symbols/cdotright.png", "symbols/paragraph.png", "symbols/worm.png", "symbols/blackstar.png")),
            new ArrayList<>(List.of("symbols/six.png", "symbols/eumlat.png", "symbols/puzzle.png",
                    "symbols/ae.png", "symbols/trident.png", "symbols/nhat.png", "symbols/omega.png"))
    );
    private Set<String> selected;
    private static KeypadSolver instance;

    // EFFECTS: constructor
    private KeypadSolver() {
        selected = new HashSet<>();
    }

    // EFFECTS: returns single instance of the keypad solver
    public static KeypadSolver getInstance() {
        if (instance == null) {
            instance = new KeypadSolver();
        }
        return instance;
    }

    // EFFECTS: getters
    public Set<String> getImageFiles() {
        return IMAGE_FILES;
    }

    public int getNumKeys() {
        return NUM_KEYS;
    }

    public Set<String> getSelected() {
        return selected;
    }

    // REQUIRES: selected.size == 4
    // EFFECTS: solves the module
    public ArrayList<String> solve() {
        ArrayList<String> solution = new ArrayList<>();
        for (ArrayList<String> key : IMAGE_KEYS) {
            if (key.containsAll(selected)) {
                for (String image : key) {
                    if (selected.contains(image)) {
                        solution.add(image);
                    }
                }
                break;
            }
        }
        return solution;
    }
}
