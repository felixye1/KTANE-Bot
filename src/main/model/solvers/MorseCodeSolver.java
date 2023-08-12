package model.solvers;

import model.exceptions.OutOfBoundsException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;

// represents a morse code module solver
public class MorseCodeSolver extends ModuleSolver {
    private static MorseCodeSolver instance;
    private static final String RESET_KEY = "done";
    private static final String RESET_MESSAGE = "\nMemory reset, ready for the next Morse Code module.";
    private static final String BACK_KEY = "back";
    private static final String BACK_MESSAGE = "\nReverted to previous 'character' of the Morse Code module.";
    private static final Map<String, Object> MORSE_TRANSLATION = Map.ofEntries(
            entry(".-", "a"), entry("-...", "b"), entry("-.-.", "c"),
            entry("-..", "d"), entry(".", "e"), entry("..-.", "f"),
            entry("--.", "g"), entry("....", "h"), entry("..", "i"),
            entry(".---", "j"), entry("-.-", "k"), entry(".-..", "l"),
            entry("--", "m"), entry("-.", "n"), entry("---", "o"),
            entry(".--.", "p"), entry("--.-", "q"), entry(".-.", "r"),
            entry("...", "s"), entry("-", 't'), entry("..-", "u"),
            entry("...-", "v"), entry(".--", "w"), entry("-..-", "x"),
            entry("-.--", "y"), entry("--..", "z")
    );
    private static final Map<String, Double> ALL_POSSIBLE_CODES = Map.ofEntries(
            entry("shell", 3.505), entry("halls", 3.515), entry("slick", 3.522),
            entry("trick", 3.532), entry("boxes", 3.535), entry("leaks", 3.542),
            entry("strobe", 3.545), entry("bistro", 3.552), entry("flick", 3.555),
            entry("bombs", 3.565), entry("break", 3.572), entry("brick", 3.575),
            entry("steak", 3.582), entry("sting", 3.592), entry("vector", 3.595),
            entry("beats", 3.6)
    );
    private String input;
    private ArrayList<Set<String>> possibleCodes;
    private int character;
    private boolean solved;

    // EFFECTS: constructor
    private MorseCodeSolver(String input) {
        this.input = input;
        possibleCodes = new ArrayList<>();
        possibleCodes.add(ALL_POSSIBLE_CODES.keySet());
        character = 1;
        solved = false;
    }

    // EFFECTS: returns single instance of the morse code solver
    public static MorseCodeSolver getInstance() {
        if (instance == null) {
            instance = new MorseCodeSolver(null);
        }
        return instance;
    }

    // EFFECTS: setters
    public void setInput(String input) {
        this.input = input;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    // EFFECTS: getters
    public String getInput() {
        return input;
    }

    public ArrayList<Set<String>> getPossibleCodes() {
        return possibleCodes;
    }

    public int getCharacter() {
        return character;
    }

    public boolean getSolved() {
        return solved;
    }

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
        String result;
        try {
            result = MORSE_TRANSLATION.get(input).toString();
        } catch (NullPointerException e) {
            throw new OutOfBoundsException("\nYou did not input a valid morse letter, please try again.");
        }
        return checkIfFound(filter(possibleCodes.get(possibleCodes.size() - 1), result));
    }

    // MODIFIES: this
    // EFFECTS: filter the possible remaining codes from given input
    private Set<String> filter(Set<String> codes, String morseKey) {
        Set<String> newPossibleCodes = new HashSet<>();
        for (String code : codes) {
            if (code.substring(character - 1, character).equals(morseKey)) {
                newPossibleCodes.add(code);
            }
        }
        possibleCodes.add(newPossibleCodes);
        return newPossibleCodes;
    }

    // EFFECTS: checks whether only one code is possible from given inputs
    private String checkIfFound(Set<String> codes) {
        String result = null;
        character++;
        if (codes.size() == 1) {
            for (String code : codes) {
                result = code;
            }
            solved = true;
            return "\nRespond at frequency " + ALL_POSSIBLE_CODES.get(result) + " MHz."
                    + "\n" + reset();
        } else if (codes.size() == 0) {
            return "\nNo possible codes were found with the given inputs, "
                    + "either go back characters or reset and try again.";
        } else {
            return "\nMorse character inputted successfully.";
        }
    }

    // EFFECTS: reverts the module back 1 stage
    private String goBack() {
        if (character != 1) {
            possibleCodes.remove(possibleCodes.size() - 1);
            character--;
            return BACK_MESSAGE;
        }
        return "\nYou are at the first stage and thus cannot go back a stage.";
    }

    // EFFECTS: reverts the module back to its initial instantiated properties
    private String reset() {
        possibleCodes.clear();
        possibleCodes.add(ALL_POSSIBLE_CODES.keySet());
        character = 1;
        return RESET_MESSAGE;
    }
}
