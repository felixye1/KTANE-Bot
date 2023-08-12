package model.solvers;

import model.Bomb;
import model.exceptions.OutOfBoundsException;

import java.util.ArrayList;
import java.util.Collections;

// represents a simple wire module solver
public class SimpleWireSolver extends ListOfInputsSolver {
    private static SimpleWireSolver instance;
    private Bomb bomb;

    // EFFECTS: constructor
    private SimpleWireSolver(String input) {
        super(input);
        bomb = Bomb.getInstance();
    }

    // EFFECTS: returns single instance of the simple wire solver
    public static SimpleWireSolver getInstance() {
        if (instance == null) {
            instance = new SimpleWireSolver(null);
        }
        return instance;
    }

    // EFFECTS: getters
    public Bomb getBomb() {
        return bomb;
    }

    // REQUIRES: input is spelt correctly
    // EFFECTS: solves the module
    public String solve() throws OutOfBoundsException {
        input = input.trim().toLowerCase();
        if (input.equals(EXIT_KEY)) {
            return EXIT_MESSAGE;
        }
        ArrayList<String> inputs = convert(NEXT_INPUT_KEY);
        int size = inputs.size();
        if (size < 3 || size > 6) {
            throw new OutOfBoundsException("\nThe module cannot have the specified amount of wires, please try again.");
        }
        if (size == 3) {
            return solveCase3Wires(inputs);
        }
        if (size == 4) {
            return solveCase4Wires(inputs);
        }
        if (size == 5) {
            return solveCase5Wires(inputs);
        }
        return solveCase6Wires(inputs);
    }

    // EFFECTS: solves the module in the case of 3 wires
    private String solveCase3Wires(ArrayList<String> inputs) {
        if (!inputs.contains("red")) {
            return "\nCut the second wire.";
        }
        if (inputs.get(2).equals("white")) {
            return "\nCut the last wire.";
        }
        if (inputs.get(0).equals("blue") && inputs.get(1).equals("blue")) {
            return "\nCut the second wire.";
        }
        return "\nCut the last wire.";
    }

    // EFFECTS: solves the module in the case of 4 wires
    private String solveCase4Wires(ArrayList<String> inputs) {
        if (Collections.frequency(inputs, "red") > 1 && bomb.digitIsOdd()) {
            int index = inputs.lastIndexOf("red");
            if (index == 3) {
                return "\nCut the last wire.";
            }
            if (index == 2) {
                return "\nCut the third wire.";
            }
            return "\nCut the second wire.";
        }
        if ((inputs.get(3).equals("yellow") && !inputs.contains("red"))
                || Collections.frequency(inputs, "blue") == 1) {
            return "\nCut the first wire.";
        }
        if (Collections.frequency(inputs, "yellow") > 1) {
            return "\nCut the last wire.";
        }
        return "\nCut the second wire.";
    }

    // EFFECTS: solves the module in the case of 5 wires
    private String solveCase5Wires(ArrayList<String> inputs) {
        if (inputs.get(4).equals("black") && bomb.digitIsOdd()) {
            return "\nCut the fourth wire.";
        }
        if (Collections.frequency(inputs, "red") == 1
                && Collections.frequency(inputs, "yellow") > 1) {
            return "\nCut the first wire.";
        }
        if (!inputs.contains("black")) {
            return "\nCut the second wire.";
        }
        return "\nCut the first wire.";
    }

    // EFFECTS: solves the module in the case of 6 wires
    private String solveCase6Wires(ArrayList<String> inputs) {
        if (!inputs.contains("yellow") && bomb.digitIsOdd()) {
            return "\nCut the third wire.";
        }
        if (Collections.frequency(inputs, "yellow") == 1
                && Collections.frequency(inputs, "white") > 1) {
            return "\nCut the fourth wire.";
        }
        if (!inputs.contains("red")) {
            return "\nCut the last wire.";
        }
        return "\nCut the fourth wire.";
    }
}
