package model.solvers;

// represents a venting gas needy module solver
public class VentingGasSolver {
    private static VentingGasSolver instance;

    // EFFECTS: constructor
    private VentingGasSolver() {

    }

    // EFFECTS: returns single instance of the venting gas solver
    public static VentingGasSolver getInstance() {
        if (instance == null) {
            instance = new VentingGasSolver();
        }
        return instance;
    }

    // EFFECTS: solves the module
    public String solve() {
        return "\nIf the module asks to vent gas, press \"Y\". If the module asks to detonate, press \"N\".";
    }
}
