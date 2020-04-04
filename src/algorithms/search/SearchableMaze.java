package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    private Maze maze;
    int move;
    int crossMove;

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
        return new MazeState(0,maze.getStartPosition(),null);
    }

    @Override
    public AState getGoalState() {

        return new MazeState(0,maze.getGoalPosition(),null);
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        return null;
    }

    public int getEdgeCost(Position from, Position to){
        return (from.getColIndex()!=to.getColIndex() && from.getRowIndex()!=to.getRowIndex()) ? move : crossMove;
    }
}
