package ui;

import model.Bomb;
import model.exceptions.OutOfBoundsException;
import model.solvers.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

// Keep Talking And No One Explodes Solver application (console version)
public class KeepTalkingAndNoOneExplodesSolverApp {
    private static final String FINISH_KEY = "finish";
    private static final String EXIT_KEY = "exit";
    private static final String RESET_KEY = "done";
    private static final String BACK_KEY = "back";
    private static final String INVALID_INPUT_MESSAGE = "\nInput is invalid, please try again.";
    private Bomb bomb;
    private SimpleWireSolver swSolver;
    private ButtonSolver buSolver;
    private KeypadGui keGui;
    private SimonSaysSolver ssSolver;
    private WhoIsOnFirstSolver wfSolver;
    private MemorySolver meSolver;
    private MorseCodeSolver mcSolver;
    private ComplicatedWireSolver cwSolver;
    private WireSequenceSolver wsSolver;
    private MazeSolver mzSolver;
    private PasswordSolver paSolver;
    private VentingGasSolver veSolver;
    private CapacitorDischargeSolver cdSolver;
    private KnobsSolver knSolver;
    private Scanner scanner;
    private String command;
    private int number;
    public static final String GOODBYE = "\nGoodbye!";

    // EFFECTS: runs the solver application
    public KeepTalkingAndNoOneExplodesSolverApp() {
        number = -1;
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        initialize();
        setUpBomb();
        while (true) {
            displayOptions();
            command = scanner.nextLine();
            if (command.equalsIgnoreCase(FINISH_KEY)) {
                scanner.close();
                break;
            } else {
                processInput(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the solvers and bomb
    private void initialize() {
        scanner = new Scanner(System.in);
        System.out.println("Welcome to the Keep Talking And No One Explodes solver. "
                + "Make sure to read the README.md file before starting.");
        bomb = Bomb.getInstance();
        swSolver = SimpleWireSolver.getInstance();
        buSolver = ButtonSolver.getInstance();
        ssSolver = SimonSaysSolver.getInstance();
        wfSolver = WhoIsOnFirstSolver.getInstance();
        meSolver = MemorySolver.getInstance();
        mcSolver = MorseCodeSolver.getInstance();
        cwSolver = ComplicatedWireSolver.getInstance();
        wsSolver = WireSequenceSolver.getInstance();
        mzSolver = MazeSolver.getInstance();
        paSolver = PasswordSolver.getInstance();
        veSolver = VentingGasSolver.getInstance();
        cdSolver = CapacitorDischargeSolver.getInstance();
        knSolver = KnobsSolver.getInstance();
    }

    // MODIFIES: Bomb
    // EFFECTS: sets relevant information about the exterior of the bomb
    private void setUpBomb() {
        System.out.println("\nFirst, fill some information about the bomb: (answer \"y/n\" for yes/no questions)");
        setFrk();
        setCar();
        setParallelPort();
        setVowelInSerial();
        setLastDigit();
        setNumBatteries();
        System.out.println("\nBomb information successfully inputted.");
    }

    // MODIFIES: Bomb
    // EFFECTS: sets whether the bomb has a lit indicator labelled "FRK"
    private void setFrk() {
        askValidYesOrNo("Does the bomb have an indicator that is lit and has the label \"FRK\"?");
        if (command.equalsIgnoreCase("y")) {
            bomb.setFrk(true);
        }
    }

    // MODIFIES: Bomb
    // EFFECTS: sets whether the bomb has a lit indicator labelled "CAR"
    private void setCar() {
        askValidYesOrNo("Does the bomb have an indicator that is lit and has the label \"CAR\"?");
        if (command.equalsIgnoreCase("y")) {
            bomb.setCar(true);
        }
    }

    // MODIFIES: Bomb
    // EFFECTS: sets whether the bomb has a parallel port
    private void setParallelPort() {
        askValidYesOrNo("Does the bomb have a parallel port?");
        if (command.equalsIgnoreCase("y")) {
            bomb.setParallelPort(true);
        }
    }

    // MODIFIES: Bomb
    // EFFECTS: sets whether the bomb has a vowel in its serial code
    private void setVowelInSerial() {
        askValidYesOrNo("Does the bomb's serial code contain a vowel?");
        if (command.equalsIgnoreCase("y")) {
            bomb.setVowelInSerial(true);
        }
    }

    // MODIFIES: Bomb
    // EFFECTS: sets the last digit of the bomb's serial code
    private void setLastDigit() {
        while (true) {
            try {
                askValidNumber("The bomb's serial code should end in a number. What is that number?");
                bomb.setLastDigit(number);
                break;
            } catch (OutOfBoundsException e) {
                System.out.println("\n" + e.getMessage());
            } catch (Exception e2) {
                System.out.println(INVALID_INPUT_MESSAGE);
            }
        }
    }

    // MODIFIES: Bomb
    // EFFECTS: sets the number of batteries on the bomb
    private void setNumBatteries() {
        while (true) {
            try {
                askValidNumber("How many batteries (of any type) are on the bomb?");
                bomb.setNumBatteries(number);
                break;
            } catch (OutOfBoundsException e) {
                System.out.println("\n" + e.getMessage());
            } catch (Exception e2) {
                System.out.println(INVALID_INPUT_MESSAGE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: asks a yes/no question and checks whether answer is in the format "y/n"
    private void askValidYesOrNo(String question) {
        System.out.println("\n" + question);
        command = scanner.nextLine();
        while (!(command.equalsIgnoreCase("y") || command.equalsIgnoreCase("n"))) {
            System.out.println(INVALID_INPUT_MESSAGE);
            System.out.println("\n" + question);
            command = scanner.nextLine();
        }
    }

    // MODIFIES: this
    // EFFECTS: asks a number question and checks whether answer is a number
    private void askValidNumber(String question) {
        System.out.println("\n" + question);
        try {
            command = scanner.nextLine();
            number = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        } catch (InputMismatchException e2) {
            throw new InputMismatchException();
        }
    }

    // EFFECTS: displays options available to user
    private void displayOptions() {
        System.out.println("\nSelect from:");
        System.out.println("\tsw => solve simple wires");
        System.out.println("\tbu => solve button");
        System.out.println("\tke => solve keypad");
        System.out.println("\tss => solve simon says");
        System.out.println("\twf => solve who's on first");
        System.out.println("\tme => solve memory");
        System.out.println("\tmc => solve morse code");
        System.out.println("\tcw => solve complicated wires");
        System.out.println("\tws => solve wire sequences");
        System.out.println("\tmz => solve maze");
        System.out.println("\tpa => solve password");
        System.out.println("\tve => solve venting gas (needy)");
        System.out.println("\tcd => solve capacitor discharge (needy)");
        System.out.println("\tkn => solve knobs (needy)");
        System.out.println("\tus => update number of strikes on the bomb");
        System.out.println("\tfinish => exit the program");
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void processInput(String command) {
        command = command.trim();
        if (command.equalsIgnoreCase("sw")) {
            doSimpleWires();
        } else if (command.equalsIgnoreCase("bu")) {
            doButton();
        } else if (command.equalsIgnoreCase("ke")) {
            doKeypad();
        } else if (command.equalsIgnoreCase("ss")) {
            doSimonSays();
        } else if (command.equalsIgnoreCase("wf")) {
            doWhoIsOnFirst();
        } else if (command.equalsIgnoreCase("me")) {
            doMemory();
        } else if (command.equalsIgnoreCase("mc")) {
            doMorseCode();
        } else if (command.equalsIgnoreCase("cw")) {
            doComplicatedWires();
        } else if (command.equalsIgnoreCase("ws")) {
            doWireSequences();
        } else if (command.equalsIgnoreCase("mz")) {
            doMaze();
        } else if (command.equalsIgnoreCase("pa")) {
            doPassword();
        } else if (command.equalsIgnoreCase("ve")) {
            doVentingGas();
        } else if (command.equalsIgnoreCase("cd")) {
            doCapacitorDischarge();
        } else if (command.equalsIgnoreCase("kn")) {
            doKnobs();
        } else if (command.equalsIgnoreCase("us")) {
            updateStrikes();
        } else {
            System.out.println("\nInvalid input, please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: receives input of and gives solution to a module with simple wires
    private void doSimpleWires() {
        doListOfInputs("\nEnter the colors of the wires from top to bottom:", swSolver, "[a-zA-Z ]+");
    }

    // MODIFIES: this
    // EFFECTS: receives input and gives solution of a button module solver
    private void doButton() {
        doListOfInputs("\nEnter the color of the button and its label:", buSolver, "[a-zA-Z ]+");
    }

    // MODIFIES: this
    // EFFECTS: receives input of and gives solution to a keypad module
    private void doKeypad() {
        keGui = new KeypadGui();
    }

    // MODIFIES: SimonSaysSolver
    // EFFECTS: receives input to a Simon Says module
    private void doSimonSays() {
        while (true) {
            System.out.println("\nEnter the color that last flashed:");
            command = scanner.nextLine();
            ssSolver.setInput(command);
            if (command.equalsIgnoreCase(EXIT_KEY) || command.equalsIgnoreCase(RESET_KEY)) {
                solveSimonSays();
                break;
            } else if (Pattern.matches("[a-zA-Z ]+", command)) {
                solveSimonSays();
            } else {
                System.out.println(INVALID_INPUT_MESSAGE);
            }
        }
    }

    // EFFECTS: gives solution to a Simon Says module
    private void solveSimonSays() {
        try {
            System.out.println(ssSolver.solve());
        } catch (OutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    // MODIFIES: this
    // EFFECTS: receives input of and gives solution to a Who's on First module
    private void doWhoIsOnFirst() {
        while (true) {
            doListOfInputs("\nEnter the display label, then the 6 labels (left to right, top to bottom):",
                    wfSolver, "[a-zA-Z ?]+");
            if (command.equalsIgnoreCase(EXIT_KEY)) {
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: receives input of and gives solution to a memory module
    private void doMemory() {
        while (true) {
            if (meSolver.getStage() == 5) {
                doListOfInputs("\n(Stage " + meSolver.getStage()
                                + ") Enter the display number, then the 4 button numbers (left to right):",
                        meSolver, "[1-4 ]+");
                if (!command.equalsIgnoreCase(BACK_KEY)) {
                    break;
                }
            }
            doListOfInputs("\n(Stage " + meSolver.getStage()
                            + ") Enter the display number, then the 4 button numbers (left to right):",
                    meSolver, "[1-4 ]+");
            if (command.equalsIgnoreCase(EXIT_KEY) || command.equalsIgnoreCase(RESET_KEY)) {
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: receives input of and gives solution to a morse code module
    private void doMorseCode() {
        mcSolver.setSolved(false);
        while (!mcSolver.getSolved()) {
            System.out.println("\n(Character " + mcSolver.getCharacter()
                    + ") Enter the morse code:");
            command = scanner.nextLine();
            mcSolver.setInput(command);
            if (command.equalsIgnoreCase(EXIT_KEY) || command.equalsIgnoreCase(RESET_KEY)) {
                solveMorseCode();
                break;
            } else if (command.equalsIgnoreCase(BACK_KEY) || Pattern.matches("[ .-]+", command)) {
                solveMorseCode();
            } else {
                System.out.println(INVALID_INPUT_MESSAGE);
            }
        }
    }

    // EFFECTS: gives solution to a Morse Code module
    private void solveMorseCode() {
        try {
            System.out.println(mcSolver.solve());
        } catch (OutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    // MODIFIES: this
    // EFFECTS: receives input of and gives solution to a module with complicated wires
    private void doComplicatedWires() {
        doListOfInputs("\nEnter the properties of each of the wires:",
                cwSolver, "[a-zA-Z ]+");
    }

    // MODIFIES: this
    // EFFECTS: receives input of and gives solution to a wire sequence module
    private void doWireSequences() {
        while (true) {
            if (wsSolver.getStage() == 4) {
                doListOfInputs("\n(Stage " + wsSolver.getStage()
                                + ") Enter the color of the wires and the letter on the right to which each goes to, "
                                + "seperated by the \"next\" keyword (in order based on increasing number "
                                + "on the left):",
                        wsSolver, "[a-zA-Z ]+");
                if (!command.equalsIgnoreCase(BACK_KEY)) {
                    break;
                }
            }
            doListOfInputs("\n(Stage " + wsSolver.getStage()
                            + ") Enter the color of the wires and the letter on the right to which each goes to, "
                            + "seperated by the \"next\" keyword (in order based on increasing number on the left):",
                    wsSolver, "[a-zA-Z ]+");
            if (command.equalsIgnoreCase(EXIT_KEY) || command.equalsIgnoreCase(RESET_KEY)) {
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: receives input of and gives solution to a maze module
    private void doMaze() {
        doListOfInputs("\nEnter the two circles, then the target (red triangle), then you (white square):",
                mzSolver, "[1-6 ]+");
    }

    // MODIFIES: this
    // EFFECTS: receives input of and gives solution to a password module
    private void doPassword() {
        paSolver.setSolved(false);
        while (!paSolver.getSolved()) {
            System.out.println("\n(Column " + paSolver.getColumn()
                    + ") Enter all the possible letters:");
            command = scanner.nextLine();
            paSolver.setInput(command);
            if (command.equalsIgnoreCase(EXIT_KEY) || command.equalsIgnoreCase(RESET_KEY)) {
                solvePassword();
                break;
            } else if (command.equalsIgnoreCase(BACK_KEY) || Pattern.matches("[a-zA-Z ]+", command)) {
                solvePassword();
            } else {
                System.out.println(INVALID_INPUT_MESSAGE);
            }
        }
    }

    // EFFECTS: gives solution to a Password module
    private void solvePassword() {
        try {
            System.out.println(paSolver.solve());
        } catch (OutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    // MODIFIES: this
    // EFFECTS: gives solution to a needy venting gas module
    private void doVentingGas() {
        System.out.println(veSolver.solve());
    }

    // MODIFIES: this
    // EFFECTS: gives solution to a needy capacitor discharge module
    private void doCapacitorDischarge() {
        System.out.println(cdSolver.solve());
    }

    // MODIFIES: this
    // EFFECTS: receives input of and gives solution to a needy knob module
    private void doKnobs() {
        doListOfInputs("\nEnter the sequence of LEDs (right to left, top to bottom), "
                + "followed by the position of the \"UP\" label (up, right, down, left):",
                knSolver, "[0-1a-zA-z ]+");
    }

    // MODIFIES: this
    // EFFECTS: receives input of and gives solution to a module whose input is a list of strings
    private void doListOfInputs(String question, ListOfInputsSolver listSolver, String regex) {
        System.out.println(question);
        command = scanner.nextLine();
        if (Pattern.matches(regex, command) || command.equalsIgnoreCase(BACK_KEY)
                || command.equalsIgnoreCase(RESET_KEY) || command.equalsIgnoreCase(EXIT_KEY)) {
            listSolver.setInput(command);
            try {
                System.out.println(listSolver.solve());
            } catch (OutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println(INVALID_INPUT_MESSAGE);
        }
    }

    // MODIFIES: Bomb
    // EFFECTS: updates the number of strikes on the bomb
    private void updateStrikes() {
        while (true) {
            try {
                askValidNumber("How many strikes does the bomb now have?");
                bomb.setStrikes(number);
                break;
            } catch (OutOfBoundsException e) {
                System.out.println("\n" + e.getMessage());
            } catch (Exception e2) {
                System.out.println(INVALID_INPUT_MESSAGE);
            }
        }
        System.out.println("\nSuccessfully updated number of strikes.");
    }
}

