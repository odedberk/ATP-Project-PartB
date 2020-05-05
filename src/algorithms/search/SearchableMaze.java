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
        return maze!=null ? new MazeState(0,maze.getStartPosition(),null): null;
    }

    @Override
    public AState getGoalState() {
        return maze!=null ? new MazeState(0,maze.getGoalPosition(),null): null;
    }

    /**
     *
     * @param s The given state
     * @return an ArrayList containing all of the state neighbors in the maze
     */
    @Override
    public ArrayList<AState> getAllPossibleStates(AState s) {
        if (s == null || s.getState().equals(""))
            return null;
        ArrayList<AState> neighbors = new ArrayList<>();
        Position pos = ((MazeState) s).getPos();
        Position n =null;
        if(pos.getRowIndex()-1>=0 && pos.getColumnIndex()-1>=0 && maze.getMaze()[pos.getRowIndex() - 1][pos.getColumnIndex() - 1]==0
            && (maze.getMaze()[pos.getRowIndex()][pos.getColumnIndex() - 1]==0 || maze.getMaze()[pos.getRowIndex()-1 ][pos.getColumnIndex()]==0)) {
             n=new Position(pos.getRowIndex() - 1, pos.getColumnIndex() - 1);
            addNeighbor(s, neighbors, pos, n);
        }
        if(pos.getRowIndex()-1>=0 && maze.getMaze()[pos.getRowIndex() - 1][pos.getColumnIndex()]==0){
            n = new Position(pos.getRowIndex() - 1, pos.getColumnIndex());
            addNeighbor(s, neighbors, pos, n);
        }
        if(pos.getRowIndex()-1>=0 && pos.getColumnIndex()+1<maze.getMaze()[0].length && maze.getMaze()[pos.getRowIndex() - 1][pos.getColumnIndex() + 1]==0
                && (maze.getMaze()[pos.getRowIndex()][pos.getColumnIndex() + 1]==0 || maze.getMaze()[pos.getRowIndex()-1 ][pos.getColumnIndex()]==0)){
            n = new Position(pos.getRowIndex() - 1, pos.getColumnIndex()+1);
            addNeighbor(s, neighbors, pos, n);
        }
        if(pos.getColumnIndex()+1<maze.getMaze()[0].length && maze.getMaze()[pos.getRowIndex()][pos.getColumnIndex() + 1]==0){
            n = new Position(pos.getRowIndex() , pos.getColumnIndex()+1);
            addNeighbor(s, neighbors, pos, n);
        }
        if(pos.getRowIndex()+1<maze.getMaze().length && pos.getColumnIndex()+1<maze.getMaze()[0].length && maze.getMaze()[pos.getRowIndex() + 1][pos.getColumnIndex() + 1]==0
                && (maze.getMaze()[pos.getRowIndex()+1][pos.getColumnIndex()]==0 || maze.getMaze()[pos.getRowIndex() ][pos.getColumnIndex()+1]==0)){
            n = new Position(pos.getRowIndex()+1 , pos.getColumnIndex()+1);
            addNeighbor(s, neighbors, pos, n);
        }
        if(pos.getRowIndex()+1<maze.getMaze().length && maze.getMaze()[pos.getRowIndex() + 1][pos.getColumnIndex()]==0){
            n = new Position(pos.getRowIndex()+1 , pos.getColumnIndex());
            addNeighbor(s, neighbors, pos, n);
        }
        if(pos.getRowIndex()+1<maze.getMaze().length && pos.getColumnIndex()-1>=0 && maze.getMaze()[pos.getRowIndex() + 1][pos.getColumnIndex() - 1]==0
                && (maze.getMaze()[pos.getRowIndex()+1][pos.getColumnIndex()]==0 || maze.getMaze()[pos.getRowIndex() ][pos.getColumnIndex()-1]==0)){
            n = new Position(pos.getRowIndex()+1 , pos.getColumnIndex()-1);
            addNeighbor(s, neighbors, pos, n);
        }
        if(pos.getColumnIndex()-1>=0 && maze.getMaze()[pos.getRowIndex()][pos.getColumnIndex() - 1]==0){
            n = new Position(pos.getRowIndex(), pos.getColumnIndex()-1);
            addNeighbor(s, neighbors, pos, n);
        }
        return neighbors;
    }

    private void addNeighbor(AState s, ArrayList<AState> neighbors, Position pos, Position n) {
        if (((MazeState)s.getCameFrom())!=null && !n.equals(((MazeState)s.getCameFrom()).getPos()))
            neighbors.add(new MazeState(s.getCost() + getEdgeCost(pos, n), n, s));
        else if (((MazeState)s.getCameFrom())==null)
            neighbors.add(new MazeState(s.getCost() + getEdgeCost(pos, n), n, s));
    }

    public int getEdgeCost(Position from, Position to){
        return (from.getColumnIndex()!=to.getColumnIndex() && from.getRowIndex()!=to.getRowIndex()) ? move : crossMove;
    }

}
