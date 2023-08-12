package model.solvers;

import model.Maze;
import model.exceptions.OutOfBoundsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;
import static model.solvers.MazeSolver.Moves.*;

public class MazeSolver extends ListOfInputsSolver {
    public enum Moves {
        right,
        left,
        up,
        down
    }

    private static MazeSolver instance;
    private static final Set<Maze> MAZES = Set.of(
            new Maze(Set.of(12, 63), Map.ofEntries(
                    entry(11, Set.of(right, down)), entry(21, Set.of(right, left)),
                    entry(31, Set.of(left, down)), entry(41, Set.of(right, down)),
                    entry(51, Set.of(right, left)), entry(61, Set.of(left)),
                    entry(12, Set.of(up, down)), entry(22, Set.of(right, down)),
                    entry(32, Set.of(left, up)), entry(42, Set.of(up, right)),
                    entry(52, Set.of(left, right)), entry(62, Set.of(left, down)),
                    entry(13, Set.of(up, down)), entry(23, Set.of(up, right)),
                    entry(33, Set.of(left, down)), entry(43, Set.of(down, right)),
                    entry(53, Set.of(right, left)), entry(63, Set.of(up, left, down)),
                    entry(14, Set.of(up, down)), entry(24, Set.of(right)),
                    entry(34, Set.of(left, up, right)), entry(44, Set.of(left, up)),
                    entry(54, Set.of(right)), entry(64, Set.of(left, up, down)),
                    entry(15, Set.of(up, right, down)), entry(25, Set.of(left, right)),
                    entry(35, Set.of(left, down)), entry(45, Set.of(right, down)),
                    entry(55, Set.of(left)), entry(65, Set.of(up, down)),
                    entry(16, Set.of(up, right)), entry(26, Set.of(left)),
                    entry(36, Set.of(up, right)), entry(46, Set.of(left, up)),
                    entry(56, Set.of(right)), entry(66, Set.of(left, up)))),
            new Maze(Set.of(24, 52), Map.ofEntries(
                    entry(11, Set.of(right)), entry(21, Set.of(left, down, right)),
                    entry(31, Set.of(left)), entry(41, Set.of(down, right)),
                    entry(51, Set.of(right, down, left)), entry(61, Set.of(left)),
                    entry(12, Set.of(down, right)), entry(22, Set.of(left, up)),
                    entry(32, Set.of(down, right)), entry(42, Set.of(left, up)),
                    entry(52, Set.of(up, right)), entry(62, Set.of(left, down)),
                    entry(13, Set.of(up, down)), entry(23, Set.of(right, down)),
                    entry(33, Set.of(left, up)), entry(43, Set.of(right, down)),
                    entry(53, Set.of(left, right)), entry(63, Set.of(left, up, down)),
                    entry(14, Set.of(up, right, down)), entry(24, Set.of(left, up)),
                    entry(34, Set.of(right, down)), entry(44, Set.of(left, up)),
                    entry(54, Set.of(down)), entry(64, Set.of(up, down)),
                    entry(15, Set.of(up, down)), entry(25, Set.of(down)),
                    entry(35, Set.of(up, down)), entry(45, Set.of(right, down)),
                    entry(55, Set.of(left, up)), entry(65, Set.of(up, down)),
                    entry(16, Set.of(up)), entry(26, Set.of(up, right)),
                    entry(36, Set.of(left, up)), entry(46, Set.of(up, right)),
                    entry(56, Set.of(left, right)), entry(66, Set.of(left, up)))),
            new Maze(Set.of(44, 64), Map.ofEntries(
                    entry(11, Set.of(right, down)), entry(21, Set.of(left, right)),
                    entry(31, Set.of(left, down)), entry(41, Set.of(down)),
                    entry(51, Set.of(down, right)), entry(61, Set.of(left, down)),
                    entry(12, Set.of(up)), entry(22, Set.of(down)),
                    entry(32, Set.of(up, down)), entry(42, Set.of(up, right)),
                    entry(52, Set.of(left, up)), entry(62, Set.of(up, down)),
                    entry(13, Set.of(right, down)), entry(23, Set.of(up, left, down)),
                    entry(33, Set.of(up, down)), entry(43, Set.of(right, down)),
                    entry(53, Set.of(left, down)), entry(63, Set.of(up, down)),
                    entry(14, Set.of(up, down)), entry(24, Set.of(up, down)),
                    entry(34, Set.of(up, down)), entry(44, Set.of(up, down)),
                    entry(54, Set.of(up, down)), entry(64, Set.of(up, down)),
                    entry(15, Set.of(up, down)), entry(25, Set.of(up, right)),
                    entry(35, Set.of(left, up)), entry(45, Set.of(up, down)),
                    entry(55, Set.of(up, down)), entry(65, Set.of(up, down)),
                    entry(16, Set.of(up, right)), entry(26, Set.of(left, right)),
                    entry(36, Set.of(left, right)), entry(46, Set.of(left, up)),
                    entry(56, Set.of(right, up)), entry(66, Set.of(left, up)))),
            new Maze(Set.of(11, 14), Map.ofEntries(
                    entry(11, Set.of(right, down)), entry(21, Set.of(left, down)),
                    entry(31, Set.of(right)), entry(41, Set.of(left, right)),
                    entry(51, Set.of(left, right)), entry(61, Set.of(left, down)),
                    entry(12, Set.of(up, down)), entry(22, Set.of(up, down)),
                    entry(32, Set.of(right, down)), entry(42, Set.of(left, right)),
                    entry(52, Set.of(left, right)), entry(62, Set.of(left, up, down)),
                    entry(13, Set.of(up, down)), entry(23, Set.of(up, right)),
                    entry(33, Set.of(left, up)), entry(43, Set.of(right, down)),
                    entry(53, Set.of(left)), entry(63, Set.of(up, down)),
                    entry(14, Set.of(up, down)), entry(24, Set.of(right)),
                    entry(34, Set.of(left, right)), entry(44, Set.of(up, left, right)),
                    entry(54, Set.of(left, right)), entry(64, Set.of(up, left, down)),
                    entry(15, Set.of(up, right, down)), entry(25, Set.of(left, right)),
                    entry(35, Set.of(left, right)), entry(45, Set.of(left, right)),
                    entry(55, Set.of(left, down)), entry(65, Set.of(up, down)),
                    entry(16, Set.of(up, right)), entry(26, Set.of(left, right)),
                    entry(36, Set.of(left)), entry(46, Set.of(right)),
                    entry(56, Set.of(up, left)), entry(66, Set.of(up)))),
            new Maze(Set.of(46, 53), Map.ofEntries(
                    entry(11, Set.of(right)), entry(21, Set.of(right, left)),
                    entry(31, Set.of(right, left)), entry(41, Set.of(right, left)),
                    entry(51, Set.of(right, down, left)), entry(61, Set.of(left, down)),
                    entry(12, Set.of(right, down)), entry(22, Set.of(left, right)),
                    entry(32, Set.of(left, right)), entry(42, Set.of(left, down, right)),
                    entry(52, Set.of(left, up)), entry(62, Set.of(up)),
                    entry(13, Set.of(up, right, down)), entry(23, Set.of(left, down)),
                    entry(33, Set.of(right)), entry(43, Set.of(up, left)),
                    entry(53, Set.of(down, right)), entry(63, Set.of(left, down)),
                    entry(14, Set.of(up, down)), entry(24, Set.of(up, right)),
                    entry(34, Set.of(left, right)), entry(44, Set.of(left, down)),
                    entry(54, Set.of(up)), entry(64, Set.of(up, down)),
                    entry(15, Set.of(up, down)), entry(25, Set.of(right, down)),
                    entry(35, Set.of(left, right)), entry(45, Set.of(left, up, right)),
                    entry(55, Set.of(left)), entry(65, Set.of(up, down)),
                    entry(16, Set.of(up)), entry(26, Set.of(up, right)),
                    entry(36, Set.of(left, right)), entry(46, Set.of(left, right)),
                    entry(56, Set.of(left, right)), entry(66, Set.of(left, up)))),
            new Maze(Set.of(35, 51), Map.ofEntries(
                    entry(11, Set.of(down)), entry(21, Set.of(down, right)),
                    entry(31, Set.of(left, down)), entry(41, Set.of(right)),
                    entry(51, Set.of(left, right, down)), entry(61, Set.of(left, down)),
                    entry(12, Set.of(up, down)), entry(22, Set.of(up, down)),
                    entry(32, Set.of(up, down)), entry(42, Set.of(down, right)),
                    entry(52, Set.of(left, up)), entry(62, Set.of(up, down)),
                    entry(13, Set.of(up, right, down)), entry(23, Set.of(left, up)),
                    entry(33, Set.of(up)), entry(43, Set.of(up, down)),
                    entry(53, Set.of(down, right)), entry(63, Set.of(left, up)),
                    entry(14, Set.of(up, right)), entry(24, Set.of(left, down)),
                    entry(34, Set.of(right, down)), entry(44, Set.of(up, left, down)),
                    entry(54, Set.of(up, down)), entry(64, Set.of(down)),
                    entry(15, Set.of(right, down)), entry(25, Set.of(left, up)),
                    entry(35, Set.of(up)), entry(45, Set.of(up, down)),
                    entry(55, Set.of(up, right)), entry(65, Set.of(left, up, down)),
                    entry(16, Set.of(up, right)), entry(26, Set.of(right, left)),
                    entry(36, Set.of(right, left)), entry(46, Set.of(left, up)),
                    entry(56, Set.of(right)), entry(66, Set.of(up, left)))),
            new Maze(Set.of(21, 26), Map.ofEntries(
                    entry(11, Set.of(right, down)), entry(21, Set.of(left, right)),
                    entry(31, Set.of(left, right)), entry(41, Set.of(left, down)),
                    entry(51, Set.of(right, down)), entry(61, Set.of(left, down)),
                    entry(12, Set.of(up, down)), entry(22, Set.of(down, right)),
                    entry(32, Set.of(left)), entry(42, Set.of(up, right)),
                    entry(52, Set.of(left, up)), entry(62, Set.of(up, down)),
                    entry(13, Set.of(up, right)), entry(23, Set.of(left, up)),
                    entry(33, Set.of(down, right)), entry(43, Set.of(left)),
                    entry(53, Set.of(down, right)), entry(63, Set.of(left, up)),
                    entry(14, Set.of(down, right)), entry(24, Set.of(left, down)),
                    entry(34, Set.of(up, right, down)), entry(44, Set.of(left, right)),
                    entry(54, Set.of(left, up)), entry(64, Set.of(down)),
                    entry(15, Set.of(up, down)), entry(25, Set.of(up)),
                    entry(35, Set.of(up, right)), entry(45, Set.of(left, right)),
                    entry(55, Set.of(left, down)), entry(65, Set.of(up, down)),
                    entry(16, Set.of(up, right)), entry(26, Set.of(left, right)),
                    entry(36, Set.of(left, right)), entry(46, Set.of(left, right)),
                    entry(56, Set.of(left, up, right)), entry(66, Set.of(left, up)))),
            new Maze(Set.of(34, 41), Map.ofEntries(
                    entry(11, Set.of(down)), entry(21, Set.of(down, right)),
                    entry(31, Set.of(left, right)), entry(41, Set.of(left, down)),
                    entry(51, Set.of(down, right)), entry(61, Set.of(left, down)),
                    entry(12, Set.of(up, right, down)), entry(22, Set.of(left, up, right)),
                    entry(32, Set.of(left)), entry(42, Set.of(up, right)),
                    entry(52, Set.of(left, up)), entry(62, Set.of(up, down)),
                    entry(13, Set.of(up, down)), entry(23, Set.of(down, right)),
                    entry(33, Set.of(left, right)), entry(43, Set.of(left, right)),
                    entry(53, Set.of(left, down)), entry(63, Set.of(up, down)),
                    entry(14, Set.of(up, down)), entry(24, Set.of(up, right)),
                    entry(34, Set.of(left, down)), entry(44, Set.of(right)),
                    entry(54, Set.of(left, up, right)), entry(64, Set.of(left, up)),
                    entry(15, Set.of(up, down)), entry(25, Set.of(down)),
                    entry(35, Set.of(up, right)), entry(45, Set.of(left, right)),
                    entry(55, Set.of(left, right)), entry(65, Set.of(left)),
                    entry(16, Set.of(up, right)), entry(26, Set.of(left, up, right)),
                    entry(36, Set.of(left, right)), entry(46, Set.of(left, right)),
                    entry(56, Set.of(left, right)), entry(66, Set.of(left)))),
            new Maze(Set.of(15, 32), Map.ofEntries(
                    entry(11, Set.of(down)), entry(21, Set.of(down, right)),
                    entry(31, Set.of(left, right)), entry(41, Set.of(left, right)),
                    entry(51, Set.of(left, right, down)), entry(61, Set.of(left, down)),
                    entry(12, Set.of(up, down)), entry(22, Set.of(up, down)),
                    entry(32, Set.of(down, right)), entry(42, Set.of(left)),
                    entry(52, Set.of(up, down)), entry(62, Set.of(up, down)),
                    entry(13, Set.of(up, right, down)), entry(23, Set.of(left, up, right)),
                    entry(33, Set.of(left, up)), entry(43, Set.of(down, right)),
                    entry(53, Set.of(left, up)), entry(63, Set.of(up, down)),
                    entry(14, Set.of(up, down)), entry(24, Set.of(down)),
                    entry(34, Set.of(down, right)), entry(44, Set.of(left, up)),
                    entry(54, Set.of(right)), entry(64, Set.of(left, up, down)),
                    entry(15, Set.of(up, down)), entry(25, Set.of(up, down)),
                    entry(35, Set.of(up, down)), entry(45, Set.of(down, right)),
                    entry(55, Set.of(left, down)), entry(65, Set.of(up)),
                    entry(16, Set.of(up, right)), entry(26, Set.of(left, up)),
                    entry(36, Set.of(up, right)), entry(46, Set.of(left, up)),
                    entry(56, Set.of(up, right)), entry(66, Set.of(left))))
    );

    // EFFECTS: constructor
    private MazeSolver(String input) {
        super(input);
    }

    // EFFECTS: returns single instance of the maze solver
    public static MazeSolver getInstance() {
        if (instance == null) {
            instance = new MazeSolver(null);
        }
        return instance;
    }

    // EFFECTS: solves the module
    public String solve() throws OutOfBoundsException {
        input = input.trim().toLowerCase();
        if (input.equals(EXIT_KEY)) {
            return EXIT_MESSAGE;
        }
        ArrayList<String> inputs = convert(NEXT_INPUT_KEY);
        if (inputs.size() != 4) {
            throw new OutOfBoundsException("\nYour input should have 4 two-digit numbers (both circles, target, "
                    + "your location), please try again.");
        }
        int circle1 = Integer.parseInt(inputs.get(0));
        int circle2 = Integer.parseInt(inputs.get(1));
        int target = Integer.parseInt(inputs.get(2));
        int start = Integer.parseInt(inputs.get(3));
        Maze correctMaze = null;
        for (Maze maze : MAZES) {
            if (maze.getCircles().containsAll(List.of(circle1, circle2))) {
                correctMaze = maze;
                break;
            }
        }
        return convertOutput(findPath(correctMaze, target, start, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }

    // EFFECTS: finds the correct path through the maze
    private ArrayList<Moves> findPath(
            Maze maze, int target, int cell, ArrayList<Moves> path, ArrayList<Integer> cellWorklist,
            ArrayList<ArrayList<Moves>> pathWorkList, ArrayList<Integer> visited) throws OutOfBoundsException {
        try {
            if (cell == target) {
                return path;
            }
            if (visited.contains(cell)) {
                return checkAnyMovesLeft(maze, target, cellWorklist, pathWorkList, visited);
            }
            visited.add(cell);
            generateNextMoves(maze, cell, path, cellWorklist, pathWorkList);
            return checkAnyMovesLeft(maze, target, cellWorklist, pathWorkList, visited);
        } catch (NullPointerException e) {
            throw new OutOfBoundsException("\nOne or more of your inputs were not correct, please try again.");
        }
    }

    private ArrayList<Moves> checkAnyMovesLeft(Maze maze, int target, ArrayList<Integer> cellWorklist,
                                               ArrayList<ArrayList<Moves>> pathWorkList,
                                               ArrayList<Integer> visited) throws OutOfBoundsException {
        if (cellWorklist.size() == 0) {
            throw new OutOfBoundsException("\nA path could not be found, "
                    + "please check your inputs and try again.");
        }
        return findPath(maze, target, cellWorklist.remove(0),
                pathWorkList.remove(0), cellWorklist, pathWorkList, visited);
    }

    // EFFECTS: finds all possible next moves
    private void generateNextMoves(Maze maze, int cell, ArrayList<Moves> path, ArrayList<Integer> cellWorklist,
                                   ArrayList<ArrayList<Moves>> pathWorkList) {
        for (Moves nextMove : maze.getCells().get(cell)) {
            switch (nextMove) {
                case up:
                    cellWorklist.add(0, cell - 1);
                    break;
                case down:
                    cellWorklist.add(0, cell + 1);
                    break;
                case right:
                    cellWorklist.add(0, cell + 10);
                    break;
                case left:
                    cellWorklist.add(0, cell - 10);
                    break;
            }
            ArrayList<Moves> nextPath = new ArrayList<>(path);
            nextPath.add(nextMove);
            pathWorkList.add(0, nextPath);
        }
    }

    // EFFECTS: converts output into string format
    private String convertOutput(ArrayList<Moves> moves) {
        String outputString = "";
        String start = "\nMove (in this order): ";
        for (Moves move : moves) {
            outputString = outputString.concat(start).concat(move.toString());
            start = ", ";
        }
        return outputString.concat(".");
    }
}
