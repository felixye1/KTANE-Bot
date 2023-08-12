package model.solvers;

import model.exceptions.OutOfBoundsException;

import java.util.ArrayList;

// represents all modules that are inputted as a list of a variable number of strings at once
public abstract class ListOfInputsSolver extends ModuleSolver {
    protected String input;

    protected static final String NEXT_INPUT_KEY = " ";

    // EFFECTS: constructor
    public ListOfInputsSolver(String input) {
        this.input = input;
    }

    // EFFECTS: converts user input into appropriate input for solver
    protected ArrayList<String> convert(String key) {
        int rightIndex;
        String tempInput = input.trim().toLowerCase();
        ArrayList<String> strings = new ArrayList<>();
        while (tempInput.contains(key)) {
            rightIndex = tempInput.indexOf(key);
            strings.add(tempInput.substring(0, rightIndex));
            tempInput = tempInput.substring(rightIndex + key.length());
        }
        if (!tempInput.isEmpty()) {
            strings.add(tempInput);
        }
        return strings;
    }

    // EFFECTS: setters
    public void setInput(String input) {
        this.input = input;
    }

    // EFFECTS: getters
    public String getInput() {
        return input;
    }

    public abstract String solve() throws OutOfBoundsException;
}
