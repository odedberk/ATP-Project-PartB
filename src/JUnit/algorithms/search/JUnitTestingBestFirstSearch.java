package algorithms.search;

import algorithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JUnitTestingBestFirstSearch {

    static BestFirstSearch test = new BestFirstSearch();
    @Test
    void getNumberOfNodesEvaluated() {
        SearchableMaze maze = new SearchableMaze(new Maze(new Position(0,0), new Position(0,0),new int[0][0]));

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
        assertEquals("Best First Search",test.getName());

    }

    @Test
    void solve() {
        //no solution
        AMazeGenerator mg = new SimpleMazeGenerator();
        Maze tester = mg.generate(100,100);
        for(int i=-1; i<=1;i++)
            for (int j=-1 ; j<=1; j++)
                if(i != 0 || j != 0 )
                    tester.getMaze()[50+i][50+j]=1;
        tester.getMaze()[50][50]=0;
        tester.setGoal(new Position(50,50));
        //tester.print();
        ISearchable s = new SearchableMaze(tester);
        Solution sol =test.solve(s);
//        if(sol == null)
        assertNull(sol.getSolutionPath());

        //goal at start point
        tester.setGoal(tester.getStartPosition());
        ((SearchableMaze)s).setMaze(tester);
        sol = test.solve(s);
        assertEquals(tester.getGoalPosition().toString(), sol.getSolutionPath().get(0).getState());

        //solution for simple maze
        mg = new SimpleMazeGenerator();
        tester=mg.generate(1000,1000);
        ((SearchableMaze)s).setMaze(tester);
        sol = test.solve(s);
        assertEquals(tester.getGoalPosition().toString(), sol.getSolutionPath().get(0).getState());

        //solution for empty maze
        mg = new EmptyMazeGenerator();
        tester=mg.generate(1000,1000);
        ((SearchableMaze)s).setMaze(tester);
        sol = test.solve(s);
        assertEquals(tester.getGoalPosition().toString(),sol.getSolutionPath().get(0).getState() );

        //solution for myGenerator maze
        mg = new MyMazeGenerator();
        tester=mg.generate(1000,1000);
        ((SearchableMaze)s).setMaze(tester);
        sol = test.solve(s);
        assertEquals(tester.getGoalPosition().toString(),sol.getSolutionPath().get(0).getState());


    }

}