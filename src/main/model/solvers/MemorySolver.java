package model.solvers;

import model.exceptions.OutOfBoundsException;

import java.util.ArrayList;
import java.util.HashSet;

// represents a memory module solver
public class MemorySolver extends ListOfInputsSolver {
    private static final String RESET_KEY = "done";
    private static final String RESET_MESSAGE = "\nMemory reset, ready for the next Memory module.";
    private static final String PREFIX = "\nPress \"";
    private static final String SUFFIX = "\".";
    private static final String INVALID_FORMAT_MESSAGE = "\nOne or more of your inputs were not typed in the correct "
            + "format, please try again.";
    private static final String BACK_KEY = "back";
    private static final String BACK_MESSAGE = "\nReverted to previous stage of the Memory module.";
    private static MemorySolver instance;
    private ArrayList<String> labels;
    private ArrayList<Integer> positions;
    private int stage;

    // EFFECTS: constructor
    private MemorySolver(String input) {
        super(input);
        labels = new ArrayList<>();
        positions = new ArrayList<>();
        stage = 1;
    }

    // EFFECTS: returns single instance of the memory solver
    public static MemorySolver getInstance() {
        if (instance == null) {
            instance = new MemorySolver(null);
        }
        return instance;
    }

    // EFFECTS: setters
    public void setStage(int stage) {
        this.stage = stage;
    }

    // EFFECTS: getters
    public ArrayList<String> getLabels() {
        return labels;
    }

    public ArrayList<Integer> getPositions() {
        return positions;
    }

    public int getStage() {
        return stage;
    }

    // MODIFIES: this
    // EFFECTS: solves the module for the particular stage
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
        if (inputs.size() != 5) {
            throw new OutOfBoundsException("\nYour input should have 5 numbers (the display and 4 buttons)"
                    + ", please try again.");
        }
        String displayNum = inputs.remove(0);
        if (areDuplicates(inputs)) {
            throw new OutOfBoundsException("\nThere should not be duplicate button labels, please try again.");
        } else if (stage == 1) {
            return solveCaseStage1(inputs, Integer.parseInt(displayNum));
        } else if (stage == 2) {
            return solveCaseStage2(inputs, Integer.parseInt(displayNum));
        } else if (stage == 3) {
            return solveCaseStage3(inputs, Integer.parseInt(displayNum));
        } else if (stage == 4) {
            return solveCaseStage4(inputs, Integer.parseInt(displayNum));
        } else {
            return solveCaseStage5(inputs, Integer.parseInt(displayNum));
        }
    }

    // EFFECTS: reverts the module back 1 stage
    private String goBack() {
        if (stage != 1) {
            labels.remove(labels.size() - 1);
            positions.remove(positions.size() - 1);
            stage--;
            return BACK_MESSAGE;
        }
        return "\nYou are at the first stage and thus cannot go back a stage.";
    }

    // EFFECTS: reverts the module back to its initial instantiated properties
    private String reset() {
        labels.clear();
        positions.clear();
        stage = 1;
        return RESET_MESSAGE;
    }

    // EFFECTS: checks whether the list has duplicate elements
    private boolean areDuplicates(ArrayList<String> inputs) {
        HashSet<String> set = new HashSet<>();
        for (String str : inputs) {
            if (!set.add(str)) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: solves the module in stage 1
    private String solveCaseStage1(ArrayList<String> inputs, int displayNum) throws OutOfBoundsException {
        if (displayNum == 1) {
            return getLabelToPress(inputs.get(1), 1);
        } else if (displayNum == 2) {
            return getLabelToPress(inputs.get(1), 1);
        } else if (displayNum == 3) {
            return getLabelToPress(inputs.get(2), 2);
        } else if (displayNum == 4) {
            return getLabelToPress(inputs.get(3), 3);
        } else {
            throw new OutOfBoundsException(INVALID_FORMAT_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: solves the module in stage 1
    private String solveCaseStage2(ArrayList<String> inputs, int displayNum) throws OutOfBoundsException {
        if (displayNum == 1) {
            return getLabelToPress("4", inputs.indexOf("4"));
        } else if (displayNum == 2) {
            return getLabelToPress(inputs.get(positions.get(0)), positions.get(0));
        } else if (displayNum == 3) {
            return getLabelToPress(inputs.get(0), 0);
        } else if (displayNum == 4) {
            return getLabelToPress(inputs.get(positions.get(0)), positions.get(0));
        } else {
            throw new OutOfBoundsException(INVALID_FORMAT_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: solves the module in stage 1
    private String solveCaseStage3(ArrayList<String> inputs, int displayNum) throws OutOfBoundsException {
        if (displayNum == 1) {
            return getLabelToPress(labels.get(1), inputs.indexOf(labels.get(1)));
        } else if (displayNum == 2) {
            return getLabelToPress(labels.get(0), inputs.indexOf(labels.get(0)));
        } else if (displayNum == 3) {
            return getLabelToPress(inputs.get(2), 2);
        } else if (displayNum == 4) {
            return getLabelToPress("4", inputs.indexOf("4"));
        } else {
            throw new OutOfBoundsException(INVALID_FORMAT_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: solves the module in stage 1
    private String solveCaseStage4(ArrayList<String> inputs, int displayNum) throws OutOfBoundsException {
        if (displayNum == 1) {
            return getLabelToPress(inputs.get(positions.get(0)), positions.get(0));
        } else if (displayNum == 2) {
            return getLabelToPress(inputs.get(0), 0);
        } else if (displayNum == 3) {
            return getLabelToPress(inputs.get(positions.get(1)), positions.get(1));
        } else if (displayNum == 4) {
            return getLabelToPress(inputs.get(positions.get(1)), positions.get(1));
        } else {
            throw new OutOfBoundsException(INVALID_FORMAT_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: solves the module in stage 1
    private String solveCaseStage5(ArrayList<String> inputs, int displayNum) throws OutOfBoundsException {
        if (displayNum == 1) {
            return getLabelToPress(labels.get(0), inputs.indexOf(labels.get(0)));
        } else if (displayNum == 2) {
            return getLabelToPress(labels.get(1), inputs.indexOf(labels.get(1)));
        } else if (displayNum == 3) {
            return getLabelToPress(labels.get(3), inputs.indexOf(labels.get(3)));
        } else if (displayNum == 4) {
            return getLabelToPress(labels.get(2), inputs.indexOf(labels.get(2)));
        } else {
            throw new OutOfBoundsException(INVALID_FORMAT_MESSAGE);
        }
    }

    // EFFECTS: returns which label is to be pressed
    private String getLabelToPress(String labelToPress, int position) {
        labels.add(labelToPress);
        positions.add(position);
        if (stage == 5) {
            return PREFIX + labelToPress + SUFFIX + "\n" + reset();
        } else {
            stage++;
            return PREFIX + labelToPress + SUFFIX;
        }
    }
}
