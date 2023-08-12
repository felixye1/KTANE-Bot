package model;

import model.solvers.MazeSolver.Moves;

import java.util.Map;
import java.util.Set;

// represents a maze
public class Maze {
    private Set<Integer> circles;
    private Map<Integer, Set<Moves>> cells;

    // EFFECTS: constructor
    public Maze(Set<Integer> circles, Map<Integer, Set<Moves>> cells) {
        this.circles = circles;
        this.cells = cells;
    }

    // EFFECTS: getters
    public Set<Integer> getCircles() {
        return circles;
    }

    public Map<Integer, Set<Moves>> getCells() {
        return cells;
    }
}

