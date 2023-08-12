package model.solvers;

import model.Bomb;
import model.exceptions.OutOfBoundsException;

import java.util.ArrayList;

// represents a button module solver
public class ButtonSolver extends ListOfInputsSolver {
    private static ButtonSolver instance;
    private Bomb bomb;

    private static final String HOLD_MESSAGE = "\nHold the button and identify the color of the colored strip."
            + "Based on the colour, release when the countdown timer has the specified number in any position:"
            + "\n\nBlue: 4\t Yellow: 5\t Other: 1";
    private static final String INSTANT_PRESS_MESSAGE = "\nPress and immediately release the button.";

    // EFFECTS: constructor
    private ButtonSolver(String input) {
        super(input);
        bomb = Bomb.getInstance();
    }

    // EFFECTS: returns single instance of the button solver
    public static ButtonSolver getInstance() {
        if (instance == null) {
            instance = new ButtonSolver(null);
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

    // REQUIRES: input is spelt correctly
    // EFFECTS: solves the module
    public String solve() throws OutOfBoundsException {
        input = input.trim().toLowerCase();
        if (input.equals(EXIT_KEY)) {
            return EXIT_MESSAGE;
        }
        ArrayList<String> strings = convert(NEXT_INPUT_KEY);
        if (strings.size() != 2) {
            throw new OutOfBoundsException(INVALID_INPUT_MESSAGE);
        } else if (strings.get(0).equals("blue") && strings.get(1).equals("abort")) {
            return HOLD_MESSAGE;
        } else if (bomb.getNumBatteries() > 1 && strings.get(1).equals("detonate")) {
            return INSTANT_PRESS_MESSAGE;
        } else if (strings.get(0).equals("white") && bomb.hasLitCar()) {
            return HOLD_MESSAGE;
        } else if (bomb.getNumBatteries() > 2 && bomb.hasLitFrk()) {
            return INSTANT_PRESS_MESSAGE;
        } else if (strings.get(0).equals("yellow")) {
            return HOLD_MESSAGE;
        } else if (strings.get(0).equals("red") && strings.get(1).equals("hold")) {
            return INSTANT_PRESS_MESSAGE;
        } else {
            return HOLD_MESSAGE;
        }
    }
}
