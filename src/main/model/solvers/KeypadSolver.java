package model.solvers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// represents a keypad module solver
public class KeypadSolver {
    private static final Set<String> IMAGE_FILES = Set.of(
            "src/main/symbols/ae.png", "src/main/symbols/at.png", "src/main/symbols/blackstar.png",
            "src/main/symbols/bt.png", "src/main/symbols/cdotleft.png", "src/main/symbols/cdotright.png",
            "src/main/symbols/copyright.png", "src/main/symbols/eumlat.png", "src/main/symbols/hy.png",
            "src/main/symbols/lambda.png", "src/main/symbols/lightning.png", "src/main/symbols/loop.png",
            "src/main/symbols/mirror.png", "src/main/symbols/nhat.png", "src/main/symbols/omega.png",
            "src/main/symbols/paragraph.png", "src/main/symbols/pumpkin.png", "src/main/symbols/puzzle.png",
            "src/main/symbols/question.png", "src/main/symbols/six.png", "src/main/symbols/smiley.png",
            "src/main/symbols/three.png", "src/main/symbols/trident.png", "src/main/symbols/tripod.png",
            "src/main/symbols/whitestar.png", "src/main/symbols/worm.png", "src/main/symbols/xi.png"
    );
    private static final int NUM_KEYS = 4;
    private static final Set<ArrayList<String>> IMAGE_KEYS = Set.of(
            new ArrayList<>(List.of("src/main/symbols/mirror.png", "src/main/symbols/at.png",
                    "src/main/symbols/lambda.png", "src/main/symbols/lightning.png", "src/main/symbols/tripod.png",
                    "src/main/symbols/hy.png", "src/main/symbols/cdotleft.png")),
            new ArrayList<>(List.of("src/main/symbols/eumlat.png", "src/main/symbols/mirror.png",
                    "src/main/symbols/cdotleft.png", "src/main/symbols/loop.png", "src/main/symbols/whitestar.png",
                    "src/main/symbols/hy.png", "src/main/symbols/question.png")),
            new ArrayList<>(List.of("src/main/symbols/copyright.png", "src/main/symbols/pumpkin.png",
                    "src/main/symbols/loop.png", "src/main/symbols/xi.png", "src/main/symbols/three.png",
                    "src/main/symbols/lambda.png", "src/main/symbols/whitestar.png")),
            new ArrayList<>(List.of("src/main/symbols/six.png", "src/main/symbols/paragraph.png",
                    "src/main/symbols/bt.png", "src/main/symbols/tripod.png", "src/main/symbols/xi.png",
                    "src/main/symbols/question.png", "src/main/symbols/smiley.png")),
            new ArrayList<>(List.of("src/main/symbols/trident.png", "src/main/symbols/smiley.png",
                    "src/main/symbols/bt.png", "src/main/symbols/cdotright.png", "src/main/symbols/paragraph.png",
                    "src/main/symbols/worm.png", "src/main/symbols/blackstar.png")),
            new ArrayList<>(List.of("src/main/symbols/six.png", "src/main/symbols/eumlat.png",
                    "src/main/symbols/puzzle.png", "src/main/symbols/ae.png", "src/main/symbols/trident.png",
                    "src/main/symbols/nhat.png", "src/main/symbols/omega.png"))
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
