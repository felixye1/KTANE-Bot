package model.solvers;

import model.exceptions.OutOfBoundsException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

// represents a who's on first module solver
public class WhoIsOnFirstSolver extends ListOfInputsSolver {
    private static WhoIsOnFirstSolver instance;
    private static final HashSet<String> TOP_LEFT = new HashSet<>(List.of("ur"));
    private static final HashSet<String> TOP_RIGHT = new HashSet<>(List.of("first", "okay", "c"));
    private static final HashSet<String> MIDDLE_LEFT = new HashSet<>(List.of("yes", "nothing", "led", "theyare"));
    private static final HashSet<String> MIDDLE_RIGHT =
            new HashSet<>(List.of("blank", "read", "red", "you", "your", "youre", "their"));
    private static final HashSet<String> BOTTOM_LEFT = new HashSet<>(List.of("empty", "reed", "leed", "theyre"));
    private static final HashSet<String> BOTTOM_RIGHT =
            new HashSet<>(List.of("display", "says", "no", "lead", "holdon", "youare", "there", "see", "cee"));
    private static final Map<String, ArrayList<String>> STRING_TABLE = Map.ofEntries(
            entry("ready", new ArrayList<>(List.of("yes", "okay", "what", "middle", "left", "press",
                    "right", "blank", "ready"))),
            entry("first", new ArrayList<>(List.of("left", "okay", "yes", "middle", "no", "right",
                            "nothing", "uhhh", "wait", "ready", "blank", "what", "press", "first"))),
            entry("no", new ArrayList<>(List.of("blank", "uhhh", "wait", "first", "what", "ready",
                    "right", "yes", "nothing", "left", "press", "okay", "no"))),
            entry("blank", new ArrayList<>(List.of("wait", "right", "okay", "middle", "blank"))),
            entry("nothing", new ArrayList<>(List.of("uhhh", "right", "okay", "middle", "yes", "blank",
                    "no", "press", "left", "what", "wait", "first", "nothing"))),
            entry("yes", new ArrayList<>(List.of("okay", "right", "uhhh", "middle", "first", "what",
                    "press", "ready", "nothing", "yes"))),
            entry("what", new ArrayList<>(List.of("uhhh", "what"))),
            entry("uhhh", new ArrayList<>(List.of("ready", "nothing", "left", "what", "okay", "yes",
                    "right", "no", "press", "blank", "uhhh"))),
            entry("left", new ArrayList<>(List.of("right", "left"))),
            entry("right", new ArrayList<>(List.of("yes", "nothing", "ready", "press", "no", "wait", "what",
                    "right"))),
            entry("middle", new ArrayList<>(List.of("blank", "ready", "okay", "what", "nothing", "press",
                    "no", "wait", "left", "middle"))),
            entry("okay", new ArrayList<>(List.of("middle", "no", "first", "yes", "uhhh", "nothing", "wait",
                    "okay"))),
            entry("wait", new ArrayList<>(List.of("uhhh", "no", "blank", "okay", "yes", "left", "first", "press",
                    "what", "wait"))),
            entry("press", new ArrayList<>(List.of("right", "middle", "yes", "ready", "press"))),
            entry("you", new ArrayList<>(List.of("sure", "youare", "your", "youre", "next", "uhhuh", "ur",
                    "hold", "what?", "you"))),
            entry("youare", new ArrayList<>(List.of("your", "next", "like", "uhhuh", "what?", "done",
                    "uhuh", "hold", "you", "u", "youre", "sure", "ur", "youare"))),
            entry("your", new ArrayList<>(List.of("uhuh", "youare", "uhhuh", "your"))),
            entry("youre", new ArrayList<>(List.of("you", "youre"))),
            entry("ur", new ArrayList<>(List.of("done", "u", "ur"))),
            entry("u", new ArrayList<>(List.of("uhhuh", "sure", "next", "what?", "youre", "ur", "uhuh",
                    "done", "u"))),
            entry("uhhuh", new ArrayList<>(List.of("uhhuh"))),
            entry("uhuh", new ArrayList<>(List.of("ur", "u", "youare", "youre", "next", "uhuh"))),
            entry("what?", new ArrayList<>(List.of("you", "hold", "youre", "your", "u", "done", "uhuh",
                    "like", "youare", "uhhuh", "ur", "next", "what?"))),
            entry("done", new ArrayList<>(List.of("sure", "uhhuh", "next", "what?", "your", "ur", "youre",
                    "hold", "like", "you", "u", "youare", "uhuh", "done"))),
            entry("next", new ArrayList<>(List.of("what?", "uhhuh", "uhuh", "your", "hold", "sure", "next"))),
            entry("hold", new ArrayList<>(List.of("youare", "u", "done", "uhuh", "you", "ur", "sure",
                    "what?", "youre", "next", "hold"))),
            entry("sure", new ArrayList<>(List.of("youare", "done", "like", "youre", "you", "hold", "uhhuh",
                    "ur", "sure"))),
            entry("like", new ArrayList<>(List.of("youre", "next", "u", "ur", "hold", "done", "uhuh",
                    "what?", "uhhuh", "you", "like")))
    );
    private static final String INVALID_FORMAT_MESSAGE = "\nOne or more of your inputs were not typed in the correct "
            + "format or spelling, please try again.";

    // EFFECTS: constructor
    private WhoIsOnFirstSolver(String input) {
        super(input);
    }

    // EFFECTS: returns single instance of the who's on first solver
    public static WhoIsOnFirstSolver getInstance() {
        if (instance == null) {
            instance = new WhoIsOnFirstSolver(null);
        }
        return instance;
    }

    // REQUIRES: input is spelt correctly
    // EFFECTS: solves the module
    public String solve() throws OutOfBoundsException {
        input = input.trim().toLowerCase();
        if (input.equals(EXIT_KEY)) {
            return EXIT_MESSAGE;
        }
        ArrayList<String> inputs = convert(NEXT_INPUT_KEY);
        if (inputs.size() != 7) {
            throw new OutOfBoundsException("\nYour input should have 7 words (the display and 6 buttons)"
                    + ", please try again.");
        }
        String displayString = inputs.remove(0);
        try {
            if (TOP_LEFT.contains(displayString)) {
                return findLabel(STRING_TABLE.get(inputs.get(0)), inputs);
            } else if (TOP_RIGHT.contains(displayString)) {
                return findLabel(STRING_TABLE.get(inputs.get(1)), inputs);
            } else if (MIDDLE_LEFT.contains(displayString)) {
                return findLabel(STRING_TABLE.get(inputs.get(2)), inputs);
            } else if (MIDDLE_RIGHT.contains(displayString)) {
                return findLabel(STRING_TABLE.get(inputs.get(3)), inputs);
            } else if (BOTTOM_LEFT.contains(displayString)) {
                return findLabel(STRING_TABLE.get(inputs.get(4)), inputs);
            } else if (BOTTOM_RIGHT.contains(displayString)) {
                return findLabel(STRING_TABLE.get(inputs.get(5)), inputs);
            } else {
                throw new OutOfBoundsException(INVALID_FORMAT_MESSAGE);
            }
        } catch (NullPointerException e) {
            return INVALID_FORMAT_MESSAGE;
        }
    }

    // EFFECTS: finds the correct label to be pressed
    private String findLabel(ArrayList<String> list, ArrayList<String> inputs) {
        String str = null;
        for (String listStr : list) {
            for (String labelStr : inputs) {
                if (listStr.equals(labelStr)) {
                    str = "\nPress the button with the label \"" + listStr + "\".";
                    break;
                }
            }
            if (str != null) {
                break;
            }
        }
        return str;
    }
}
