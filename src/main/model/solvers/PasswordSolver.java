package model.solvers;

import model.exceptions.OutOfBoundsException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// represents a password module solver
public class PasswordSolver extends ListOfInputsSolver {
    private static PasswordSolver instance;
    private static final String RESET_KEY = "done";
    private static final String RESET_MESSAGE = "\nMemory reset, ready for the next Password module.";
    private static final String BACK_KEY = "back";
    private static final String BACK_MESSAGE = "\nReverted to previous 'column' of the Password module.";
    private static final Set<String> ALL_POSSIBLE_PASSES = Set.of("about", "after", "again", "below", "could",
            "every", "first", "found", "great", "house", "large", "learn", "never", "other", "place", "plant",
            "point", "right", "small", "sound", "spell", "still", "study", "their", "there", "these", "thing",
            "think", "three", "water", "where", "which", "world", "would", "write");
    private ArrayList<Set<String>> possiblePasses;
    private int column;
    private boolean solved;

    // EFFECTS: constructor
    private PasswordSolver(String input) {
        super(input);
        possiblePasses = new ArrayList<>();
        possiblePasses.add(ALL_POSSIBLE_PASSES);
        column = 1;
        solved = false;
    }

    // EFFECTS: returns single instance of the password solver
    public static PasswordSolver getInstance() {
        if (instance == null) {
            instance = new PasswordSolver(null);
        }
        return instance;
    }

    // EFFECTS: setters
    public void setColumn(int column) {
        this.column = column;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    // EFFECTS: getters
    public ArrayList<Set<String>> getPossiblePasses() {
        return possiblePasses;
    }

    public int getColumn() {
        return column;
    }

    public boolean getSolved() {
        return solved;
    }

    // REQUIRES: input is spelt correctly
    // EFFECTS: solves the module for the particular column
    public String solve() throws OutOfBoundsException {
        input = input.trim();
        if (input.equalsIgnoreCase(EXIT_KEY)) {
            return EXIT_MESSAGE;
        } else if (input.equalsIgnoreCase(BACK_KEY)) {
            return goBack();
        } else if (input.equalsIgnoreCase(RESET_KEY)) {
            return reset();
        }
        ArrayList<String> inputs = convert(NEXT_INPUT_KEY);
        if (inputs.size() != 6) {
            throw new OutOfBoundsException("\nYour input should have 6 letters, please try again.");
        }
        return checkIfFound(filter(possiblePasses.get(possiblePasses.size() - 1), inputs));
    }

    // MODIFIES: this
    // EFFECTS: filter the possible remaining codes from given input
    private Set<String> filter(Set<String> passes, ArrayList<String> characters) {
        Set<String> newPossiblePasses = new HashSet<>();
        for (String pass : passes) {
            if (characters.contains(pass.substring(column - 1, column))) {
                newPossiblePasses.add(pass);
            }
        }
        possiblePasses.add(newPossiblePasses);
        return newPossiblePasses;
    }

    // EFFECTS: checks whether only one code is possible from given inputs
    private String checkIfFound(Set<String> passes) {
        String result = null;
        column++;
        if (passes.size() == 1) {
            for (String pass : passes) {
                result = pass;
            }
            solved = true;
            return "\nThe password is \"" + result + "\"."
                    + "\n" + reset();
        } else if (passes.size() == 0) {
            return "\nNo valid passwords were found with the given inputs, "
                    + "either go back characters or reset and try again.";
        } else {
            return "\nColumn characters inputted successfully.";
        }
    }

    // EFFECTS: reverts the module back 1 stage
    private String goBack() {
        if (column != 1) {
            possiblePasses.remove(possiblePasses.size() - 1);
            column--;
            return BACK_MESSAGE;
        }
        return "\nYou are at the first stage and thus cannot go back a stage.";
    }

    // EFFECTS: reverts the module back to its initial instantiated properties
    private String reset() {
        possiblePasses.clear();
        possiblePasses.add(ALL_POSSIBLE_PASSES);
        column = 1;
        return RESET_MESSAGE;
    }
}
