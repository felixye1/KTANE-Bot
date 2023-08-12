package model.solvers;

import model.exceptions.OutOfBoundsException;

import java.util.ArrayList;
import java.util.Map;
import static java.util.Map.entry;

// represents a knobs needy module solver
public class KnobsSolver extends ListOfInputsSolver {
    private static final Map<String, Integer> LED_CONFIGURATIONS = Map.ofEntries(
            entry("001011111101", 0), entry("101010011011", 0),
            entry("101111111010", 1), entry("101100111010", 1),
            entry("011001111101", 2), entry("101010010001", 2),
            entry("000010100111", 3), entry("000010000110", 3)
    );
    private static final Map<String, Integer> DIRECTION_CONFIGURATIONS = Map.ofEntries(
            entry("up", 0), entry("right", 1),
            entry("down", 2), entry("left", 3)
    );
    private static final Map<Integer, String> SOLUTION_KEY = Map.ofEntries(
            entry(0, "up"), entry(1, "right"),
            entry(2, "down"), entry(3, "left")
    );
    private static KnobsSolver instance;

    // EFFECTS: constructor
    private KnobsSolver(String input) {
        super(input);
    }

    // EFFECTS: returns single instance of the knobs solver
    public static KnobsSolver getInstance() {
        if (instance == null) {
            instance = new KnobsSolver(null);
        }
        return instance;
    }

    // EFFECTS: solves the module
    public String solve() throws OutOfBoundsException {
        if (input.equalsIgnoreCase(EXIT_KEY)) {
            return EXIT_MESSAGE;
        }
        ArrayList<String> inputs = convert(NEXT_INPUT_KEY);
        if (inputs.size() != 2) {
            throw new OutOfBoundsException("\nYour input should have the a string of 0/1s"
                    + ", followed by the position of the \"UP\" label, please try again.");
        }
        try {
            int result = (LED_CONFIGURATIONS.get(inputs.get(0))
                    + DIRECTION_CONFIGURATIONS.get(inputs.get(1))) % 4;
            return "\nPoint the knob " + SOLUTION_KEY.get(result) + ".";
        } catch (NullPointerException e) {
            throw new OutOfBoundsException("\nOne or more of your inputs were not correct, "
                    + "please try again.");
        }
    }
}
