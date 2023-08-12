package model;

import model.exceptions.OutOfBoundsException;

// represents the relevant information on the exterior of the bomb (excluding modules)
public class Bomb {
    private static Bomb instance;

    private boolean litFrk;
    private boolean litCar;
    private boolean parallelPort;
    private boolean vowelInSerial;
    private int lastDigitOfSerial;
    private int numBatteries;
    private int strikes;

    // EFFECTS: constructor
    private Bomb(boolean litFrk, boolean litCar, boolean parallelPort, boolean vowelInSerial,
                int lastDigitOfSerial, int numBatteries) {
        this.litFrk = litFrk;
        this.litCar = litCar;
        this.parallelPort = parallelPort;
        this.vowelInSerial = vowelInSerial;
        this.lastDigitOfSerial = lastDigitOfSerial;
        this.numBatteries = numBatteries;
        strikes = 0;
    }

    // EFFECTS: returns single instance of the bomb
    public static Bomb getInstance() {
        if (instance == null) {
            instance = new Bomb(false, false, false, false,
                    0, 0);
        }
        return instance;
    }

    // EFFECTS: setters
    public void setFrk(boolean litFrk) {
        this.litFrk = litFrk;
    }

    public void setCar(boolean litCar) {
        this.litCar = litCar;
    }

    public void setParallelPort(boolean parallelPort) {
        this.parallelPort = parallelPort;
    }

    public void setVowelInSerial(boolean vowelInSerial) {
        this.vowelInSerial = vowelInSerial;
    }

    public void setLastDigit(int lastDigitOfSerial) throws OutOfBoundsException {
        if (lastDigitOfSerial < 0 || lastDigitOfSerial > 9) {
            throw new OutOfBoundsException("That is not a valid digit, please try again.");
        }
        this.lastDigitOfSerial = lastDigitOfSerial;
    }

    public void setNumBatteries(int numBatteries) throws OutOfBoundsException {
        if (numBatteries < 0 || numBatteries > 10) {
            throw new OutOfBoundsException("The bomb cannot have that amount of batteries, please try again.");
        }
        this.numBatteries = numBatteries;
    }

    public void setStrikes(int strikes) throws OutOfBoundsException {
        if (strikes < 0 || strikes > 2) {
            throw new OutOfBoundsException("The bomb cannot have that amount of strikes, please try again.");
        }
        this.strikes = strikes;
    }

    // EFFECTS: getters
    public boolean hasLitFrk() {
        return litFrk;
    }

    public boolean hasLitCar() {
        return litCar;
    }

    public boolean hasParallelPort() {
        return parallelPort;
    }

    public boolean hasVowelInSerial() {
        return vowelInSerial;
    }

    public int getLastDigitOfSerial() {
        return lastDigitOfSerial;
    }

    public int getNumBatteries() {
        return numBatteries;
    }

    public int getStrikes() {
        return strikes;
    }

    // EFFECTS: checks if the last digit of the serial number is odd
    public boolean digitIsOdd() {
        return lastDigitOfSerial % 2 == 1;
    }

    // EFFECTS: checks if the last digit of the serial number is even
    public boolean digitIsEven() {
        return lastDigitOfSerial % 2 == 0;
    }
}
