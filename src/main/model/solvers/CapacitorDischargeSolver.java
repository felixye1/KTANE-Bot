package model.solvers;

// represents a capacitor discharge needy module solver
public class CapacitorDischargeSolver {
    private static CapacitorDischargeSolver instance;

    // EFFECTS: constructor
    private CapacitorDischargeSolver() {

    }

    // EFFECTS: returns single instance of capacitor discharge solver
    public static CapacitorDischargeSolver getInstance() {
        if (instance == null) {
            instance = new CapacitorDischargeSolver();
        }
        return instance;
    }

    // EFFECTS: solves the module
    public String solve() {
        return "\nPress on the lever whenever either the meter on the left "
                + "is close to filling or when the timer is near 0.";
    }
}
