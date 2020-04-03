package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    private Maze maze;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public AState getStartState() {
        return null;
    }

    @Override
    public AState getGoalState() {

        return null;
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        return null;
    }

    public double getEdgeCost(Position from, Position to){
        return 0;
    }
}
