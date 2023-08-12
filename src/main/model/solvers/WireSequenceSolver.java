package model.solvers;

import model.exceptions.OutOfBoundsException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// represents a wire sequence module solver
public class WireSequenceSolver extends ListOfInputsSolver {
    private static final String RESET_KEY = "done";
    private static final String RESET_MESSAGE = "\nMemory reset, ready for the next Wire Sequences module.";
    private static final String NEXT_WIRE_KEY = "next";
    private static final String BACK_KEY = "back";
    private static final String BACK_MESSAGE = "\nReverted to previous stage of the Wire Sequences module.";
    private static final ArrayList<String> redKey =
            new ArrayList<>(List.of("c", "b", "a", "ac", "b", "ac", "abc", "ab", "b"));

    private static final ArrayList<String> blueKey =
            new ArrayList<>(List.of("b", "ac", "b", "a", "b", "bc", "c", "ac", "a"));
    private static final ArrayList<String> blackKey =
            new ArrayList<>(List.of("abc", "ac", "b", "ac", "b", "bc", "ab", "c", "c"));
    private static WireSequenceSolver instance;
    private int redPos;
    private int bluePos;
    private int blackPos;
    private int stage;
    private ArrayList<ArrayList<String>> prevInputs;

    // EFFECTS: constructor
    private WireSequenceSolver(String input) {
        super(input);
        redPos = 0;
        bluePos = 0;
        blackPos = 0;
        stage = 1;
        prevInputs = new ArrayList<>();
    }

    // EFFECTS: returns single instance of the wire sequence solver
    public static WireSequenceSolver getInstance() {
        if (instance == null) {
            instance = new WireSequenceSolver(null);
        }
        return instance;
    }

    // EFFECTS: setters
    public void setStage(int stage) {
        this.stage = stage;
    }

    // EFFECTS: getters
    public int getRedPos() {
        return redPos;
    }

    public int getBluePos() {
        return bluePos;
    }

    public int getBlackPos() {
        return blackPos;
    }

    public int getStage() {
        return stage;
    }

    public ArrayList<ArrayList<String>> getPrevInputs() {
        return prevInputs;
    }

    // REQUIRES: input is spelt correctly
    // MODIFIES: this
    // EFFECTS: solves the module for this particular stage
    public String solve() throws OutOfBoundsException {
        if (input.equals(EXIT_KEY)) {
            return EXIT_MESSAGE;
        } else if (input.equals(BACK_KEY)) {
            return goBack();
        } else if (input.equals(RESET_KEY)) {
            return reset();
        }
        checkValidInputs(convert(NEXT_INPUT_KEY));
        ArrayList<ArrayList<String>> inputs = convertNext();
        ArrayList<String> result = new ArrayList<>();
        for (ArrayList<String> wire : inputs) {
            result.add(checkCut(wire));
        }
        return convertOutput(result);
    }

    // EFFECTS: reverts the module back 1 stage
    private String goBack() {
        if (stage != 1) {
            ArrayList<String> prevInput = prevInputs.remove(prevInputs.size() - 1);
            int numRed = Collections.frequency(prevInput, "red");
            int numBlue = Collections.frequency(prevInput, "blue");
            int numBlack = Collections.frequency(prevInput, "black");
            redPos -= numRed;
            bluePos -= numBlue;
            blackPos -= numBlack;
            stage--;
            return BACK_MESSAGE;
        }
        return "\nYou are at the first stage and thus cannot go back a stage.";
    }

    // EFFECTS: reverts the module back to its initial instantiated properties
    private String reset() {
        redPos = 0;
        bluePos = 0;
        blackPos = 0;
        stage = 1;
        prevInputs.clear();
        return RESET_MESSAGE;
    }

    private void checkValidInputs(ArrayList<String> inputs) throws OutOfBoundsException {
        for (int x = 0; x < inputs.size(); x++) {
            String input = inputs.get(x);
            if (x % 3 == 0) {
                if (!(input.equals("red") || input.equals("blue") || input.equals("black"))) {
                    throw new OutOfBoundsException(INVALID_INPUT_MESSAGE);
                }
            } else if (x % 3 == 1) {
                if (!(input.equals("a") || input.equals("b") || input.equals("c"))) {
                    throw new OutOfBoundsException(INVALID_INPUT_MESSAGE);
                }
            } else {
                if (!(input.equals("next"))) {
                    throw new OutOfBoundsException(INVALID_INPUT_MESSAGE);
                }
            }
        }
    }

    // EFFECTS: breaks list of inputs into separate lists to be converted
    private ArrayList<ArrayList<String>> convertNext() throws OutOfBoundsException {
        ArrayList<ArrayList<String>> inputs = new ArrayList<>();
        for (String str : convert(NEXT_WIRE_KEY)) {
            int rightIndex;
            String tempInput = str.trim().toLowerCase();
            ArrayList<String> strings = new ArrayList<>();
            while (tempInput.contains(NEXT_INPUT_KEY)) {
                rightIndex = tempInput.indexOf(NEXT_INPUT_KEY);
                strings.add(tempInput.substring(0, rightIndex));
                tempInput = tempInput.substring(rightIndex + NEXT_INPUT_KEY.length());
            }
            if (!tempInput.isEmpty()) {
                strings.add(tempInput);
            }
            if (strings.size() != 2) {
                throw new OutOfBoundsException("\nYour input should have 2 words per wire "
                        + "(its color and which letter it ends at), each separated by the "
                        + "\"next\" keyword, please try again.");
            }
            inputs.add(strings);
        }
        return inputs;
    }

    // EFFECTS: checks whether the wire should be cut or not
    private String checkCut(ArrayList<String> wire) throws OutOfBoundsException {
        String color = wire.get(0);
        String pos = wire.get(1);
        if (color.equals("red")) {
            if (redKey.get(redPos).contains(pos)) {
                redPos++;
                return "yes";
            }
            redPos++;
            return "no";
        } else if (color.equals("blue")) {
            if (blueKey.get(bluePos).contains(pos)) {
                bluePos++;
                return "yes";
            }
            bluePos++;
            return "no";
        } else {
            if (blackKey.get(blackPos).contains(pos)) {
                blackPos++;
                return "yes";
            }
            blackPos++;
            return "no";
        }
    }

    // EFFECTS: converts output into string format
    private String convertOutput(ArrayList<String> result) {
        String outputString = "";
        String start = "\nFollow whether to cut (in order of wires inputted): ";
        for (String str : result) {
            outputString = outputString.concat(start).concat(str);
            start = ", ";
        }
        prevInputs.add(convert(NEXT_INPUT_KEY));
        if (stage == 4) {
            return outputString.concat(".") + "\n" + reset();
        }
        stage++;
        return outputString.concat(".");
    }
}
