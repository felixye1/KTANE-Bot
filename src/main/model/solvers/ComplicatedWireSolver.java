package model.solvers;

import model.Bomb;
import model.exceptions.OutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

// represents a complicated wire module solver
public class ComplicatedWireSolver extends ListOfInputsSolver {
    private static ComplicatedWireSolver instance;
    private Bomb bomb;
    private ArrayList<String> key;

    // EFFECTS: constructor
    private ComplicatedWireSolver(String input) {
        super(input);
        bomb = Bomb.getInstance();
        key = null;
    }

    // EFFECTS: returns single instance of the complicated wire solver
    public static ComplicatedWireSolver getInstance() {
        if (instance == null) {
            instance = new ComplicatedWireSolver(null);
        }
        return instance;
    }

    // EFFECTS: setters
    public void setKey(ArrayList<String> key) {
        this.key = key;
    }

    // EFFECTS: getters
    public Bomb getBomb() {
        return bomb;
    }

    public ArrayList<String> getKey() {
        return key;
    }

    // EFFECTS: determines to cut or not depending on the bomb's serial number
    private String checkSerial() {
        if (bomb.digitIsEven()) {
            return "yes";
        }
        return "no";
    }

    // EFFECTS: determines to cut or not depending on whether the bomb has a parallel port
    private String checkParallelPort() {
        if (bomb.hasParallelPort()) {
            return "yes";
        }
        return "no";
    }

    // EFFECTS: determines to cut or not depending on the number of batteries on the bomb
    private String checkBatteries() {
        if (bomb.getNumBatteries() >= 2) {
            return "yes";
        }
        return "no";
    }

    // REQUIRES: input is spelt correctly
    // EFFECTS: solves the module
    public String solve() throws OutOfBoundsException {
        if (key == null) {
            updateKey();
        }
        input = input.trim().toLowerCase();
        if (input.equals(EXIT_KEY)) {
            return EXIT_MESSAGE;
        }
        ArrayList<String> inputs = convert(NEXT_INPUT_KEY);
        ArrayList<String> result = new ArrayList<>();
        for (String wire : inputs) {
            result.add(key.get(checkProperties(wire)));
        }
        return convertOutput(result);
    }

    // EFFECTS: updates the wire key based on bomb properties
    public void updateKey() {
        key = new ArrayList<>(List.of("yes", "no", checkSerial(), checkBatteries(),
                checkSerial(), checkParallelPort(), checkSerial(), checkSerial(),
                "yes", checkBatteries(), "yes", checkBatteries(), "no", checkParallelPort(),
                checkParallelPort(), "no"));
    }

    // EFFECTS: assigns a number to the wire based on its properties
    private int checkProperties(String wire) throws OutOfBoundsException {
        int code = 0;
        if (wire.contains("n")) {
            return code;
        }
        if (wire.contains("l")) {
            code += 1;
        }
        if (wire.contains("r")) {
            code += 2;
        }
        if (wire.contains("b")) {
            code += 4;
        }
        if (wire.contains("s")) {
            code += 8;
        }
        if (code == 0) {
            throw new OutOfBoundsException(INVALID_INPUT_MESSAGE);
        }
        return code;
    }

    // EFFECTS: converts output into string format
    private String convertOutput(ArrayList<String> result) {
        String outputString = "";
        String start = "\nFollow whether to cut (in order of wires inputted): ";
        for (String str : result) {
            outputString = outputString.concat(start).concat(str);
            start = ", ";
        }
        return outputString.concat(".");
    }
}
