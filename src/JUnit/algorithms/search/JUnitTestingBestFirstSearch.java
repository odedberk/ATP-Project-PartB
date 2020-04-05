package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JUnitTestingBestFirstSearch {

    @Test
    void getNumberOfNodesEvaluated() {
        SearchableMaze maze = new SearchableMaze(new Maze(new Position(0,0), new Position(0,0),new int[0][0]));
        BestFirstSearch test = new BestFirstSearch();

        assertEquals(0, test.getNumberOfNodesEvaluated());

        test.solve(maze); //empty maze
        assertEquals(0, test.getNumberOfNodesEvaluated());

        test.solve(null);
        assertEquals(0, test.getNumberOfNodesEvaluated());

        test.solve(new SearchableMaze(new Maze(new Position(-1,0), new Position(0,0),new int[0][0])));
        assertEquals(0, test.getNumberOfNodesEvaluated());

    }

    @Test
    void getName() {
        BestFirstSearch test = new BestFirstSearch();
        assertEquals("Best First Search",test.getName());

    }
}