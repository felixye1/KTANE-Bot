package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MazeTest {
    private Maze maze;

    @BeforeEach
    public void setup() {
        maze = new Maze(new HashSet<>(), new HashMap<>());
    }

    @Test
    public void testConstructor() {
        assertEquals(0, maze.getCircles().size());
        assertEquals(0, maze.getCells().size());
    }
}
