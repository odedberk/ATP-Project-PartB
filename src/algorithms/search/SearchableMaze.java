package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    private Maze maze;
    private int move;
    private int crossMove;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
        this.move = 10;
        this.crossMove = 15;
    }

    public int getMove() {
        return move;
    }

    public int getCrossMove() {
        return crossMove;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public void setCrossMove(int crossMove) {
        this.crossMove = crossMove;
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
        if(s==null || s.getState()=="")
            return null;
        ArrayList<AState> neighbors=new ArrayList<AState>();
        Position pos = ((MazeState)s).getPos();
        if(pos.getRowIndex()>0 && pos.getColIndex()){

        }

    public int getEdgeCost(Position from, Position to){
        return (from.getColIndex()!=to.getColIndex() && from.getRowIndex()!=to.getRowIndex()) ? move : crossMove;
    }
}
