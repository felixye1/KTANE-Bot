package model.solvers;

import model.Bomb;
import model.exceptions.OutOfBoundsException;

import java.util.ArrayList;

// represents a simon says module solver
public class SimonSaysSolver extends ModuleSolver {
    private static final String RESET_KEY = "done";
    private static final String RESET_MESSAGE = "\nMemory reset, ready for the next Simon Says module.";
    private static final String BACK_KEY = "back";
    private static final String BACK_MESSAGE = "\nReverted to previous stage of the Simon Says module.";
    private static SimonSaysSolver instance;
    private String input;
    private Bomb bomb;
    private ArrayList<String> output;

    // EFFECTS: constructor
    private SimonSaysSolver(String input) {
        this.input = input;
        bomb = Bomb.getInstance();
        output = new ArrayList<>();
    }

    // EFFECTS: returns single instance of the simon says solver
    public static SimonSaysSolver getInstance() {
        if (instance == null) {
            instance = new SimonSaysSolver(null);
        }
        return instance;
    }

    // EFFECTS: setters
    public void setInput(String input) {
        this.input = input;
    }

    // EFFECTS: getters
    public String getInput() {
        return input;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public ArrayList<String> getOutput() {
        return output;
    }

    // REQUIRES: input is spelt correctly
    // MODIFIES: this
    // EFFECTS: solves the module for that particular stage
    public String solve() throws OutOfBoundsException {
        input = input.trim().toLowerCase();
        int numStrikes = bomb.getStrikes();
        if (input.equals(EXIT_KEY)) {
            return EXIT_MESSAGE;
        } else if (input.equals(RESET_KEY)) {
            return reset();
        } else if (input.equals(BACK_KEY)) {
            return goBack();
        } else if (input.equals("red")) {
            return solveCaseRed(numStrikes);
        } else if (input.equals("blue")) {
            return solveCaseBlue(numStrikes);
        } else if (input.equals("green")) {
            return solveCaseGreen(numStrikes);
        } else if (input.equals("yellow")) {
            return solveCaseYellow(numStrikes);
        }
        throw new OutOfBoundsException(INVALID_INPUT_MESSAGE);
    }

    // EFFECTS: reverts the module back to its initial instantiated properties
    private String reset() {
        output.clear();
        return RESET_MESSAGE;
    }

    // EFFECTS: reverts the module back 1 stage
    private String goBack() {
        if (output.size() != 0) {
            output.remove(output.size() - 1);
            return BACK_MESSAGE;
        }
        return "\nYou are at the first stage and thus cannot go back a stage.";
    }

    // MODIFIES: this
    // EFFECTS: solves the module in the case of red flashing
    private String solveCaseRed(int numStrikes) {
        if (numStrikes == 0) {
            output.add("blue");
        } else if (bomb.hasVowelInSerial()) {
            if (numStrikes == 1) {
                output.add("yellow");
            } else {
                output.add("green");
            }
        } else {
            if (numStrikes == 1) {
                output.add("red");
            } else {
                output.add("yellow");
            }
        }
        return convertOutput();
    }

    // EFFECTS: solves the module in the case of blue flashing
    // MODIFIES: this
    private String solveCaseBlue(int numStrikes) {
        if (bomb.hasVowelInSerial()) {
            if (numStrikes == 1) {
                output.add("green");
            } else {
                output.add("red");
            }
        } else {
            if (numStrikes == 0) {
                output.add("yellow");
            } else if (numStrikes == 1) {
                output.add("blue");
            } else {
                output.add("green");
            }
        }
        return convertOutput();
    }

    // EFFECTS: solves the module in the case of green flashing
    // MODIFIES: this
    private String solveCaseGreen(int numStrikes) {
        if (bomb.hasVowelInSerial()) {
            if (numStrikes == 1) {
                output.add("blue");
            } else {
                output.add("yellow");
            }
        } else {
            if (numStrikes == 0) {
                output.add("green");
            } else if (numStrikes == 1) {
                output.add("yellow");
            } else {
                output.add("blue");
            }
        }
        return convertOutput();
    }

    // EFFECTS: solves the module in the case of red flashing
    // MODIFIES: this
    private String solveCaseYellow(int numStrikes) {
        if (bomb.hasVowelInSerial()) {
            if (numStrikes == 0) {
                output.add("green");
            } else if (numStrikes == 1) {
                output.add("red");
            } else {
                output.add("blue");
            }
        } else {
            if (numStrikes == 1) {
                output.add("green");
            } else {
                output.add("red");
            }
        }
        return convertOutput();
    }

    // EFFECTS: converts output into string format
    private String convertOutput() {
        String outputString = "";
        String start = "\nPress (in this order): ";
        for (String str : output) {
            outputString = outputString.concat(start).concat(str);
            start = ", ";
        }
        return outputString.concat(".");
    }
}
