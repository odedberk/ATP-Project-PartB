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

    @Override
    public ArrayList<AState> getAllPossibleStates(AState s) {
        if (s == null || s.getState().equals(""))
            return null;
        ArrayList<AState> neighbors = new ArrayList<>();
        Position pos = ((MazeState) s).getPos();
        Position n =null;
        if(pos.getRowIndex()-1>=0 && pos.getColumnlIndex()-1>=0 && maze.getMaze()[pos.getRowIndex() - 1][pos.getColumnlIndex() - 1]==0
            && (maze.getMaze()[pos.getRowIndex()][pos.getColumnlIndex() - 1]==0 || maze.getMaze()[pos.getRowIndex()-1 ][pos.getColumnlIndex()]==0)) {
             n=new Position(pos.getRowIndex() - 1, pos.getColumnlIndex() - 1);
            if (((MazeState)s.getCameFrom())!=null && !n.equals(((MazeState)s.getCameFrom()).getPos()))
                neighbors.add(new MazeState(s.getCost() + getEdgeCost(pos, n), n, s));
        }
        if(pos.getRowIndex()-1>=0 && maze.getMaze()[pos.getRowIndex() - 1][pos.getColumnlIndex()]==0){
            n = new Position(pos.getRowIndex() - 1, pos.getColumnlIndex());
            if (((MazeState)s.getCameFrom())!=null && !n.equals(((MazeState)s.getCameFrom()).getPos()))
                neighbors.add(new MazeState(s.getCost() + getEdgeCost(pos, n), n, s));
            else if (((MazeState)s.getCameFrom())==null)
                neighbors.add(new MazeState(s.getCost() + getEdgeCost(pos, n), n, s));
        }
        if(pos.getRowIndex()-1>=0 && pos.getColumnlIndex()+1<maze.getMaze()[0].length && maze.getMaze()[pos.getRowIndex() - 1][pos.getColumnlIndex() + 1]==0
                && (maze.getMaze()[pos.getRowIndex()][pos.getColumnlIndex() + 1]==0 || maze.getMaze()[pos.getRowIndex()-1 ][pos.getColumnlIndex()]==0)){
            n = new Position(pos.getRowIndex() - 1, pos.getColumnlIndex()+1);
            if (((MazeState)s.getCameFrom())!=null && !n.equals(((MazeState)s.getCameFrom()).getPos()))
                neighbors.add(new MazeState(s.getCost() + getEdgeCost(pos, n), n, s));
            else if (((MazeState)s.getCameFrom())==null)
                neighbors.add(new MazeState(s.getCost() + getEdgeCost(pos, n), n, s));
        }
        if(pos.getColumnlIndex()+1<maze.getMaze()[0].length && maze.getMaze()[pos.getRowIndex()][pos.getColumnlIndex() + 1]==0){
            n = new Position(pos.getRowIndex() , pos.getColumnlIndex()+1);
            if (((MazeState)s.getCameFrom())!=null && !n.equals(((MazeState)s.getCameFrom()).getPos()))
                neighbors.add(new MazeState(s.getCost() + getEdgeCost(pos, n), n, s));
            else if (((MazeState)s.getCameFrom())==null)
                neighbors.add(new MazeState(s.getCost() + getEdgeCost(pos, n), n, s));
        }
        if(pos.getRowIndex()+1<maze.getMaze().length && pos.getColumnlIndex()+1<maze.getMaze()[0].length && maze.getMaze()[pos.getRowIndex() + 1][pos.getColumnlIndex() + 1]==0
                && (maze.getMaze()[pos.getRowIndex()+1][pos.getColumnlIndex()]==0 || maze.getMaze()[pos.getRowIndex() ][pos.getColumnlIndex()+1]==0)){
            n = new Position(pos.getRowIndex()+1 , pos.getColumnlIndex()+1);
            if (((MazeState)s.getCameFrom())!=null && !n.equals(((MazeState)s.getCameFrom()).getPos()))
                neighbors.add(new MazeState(s.getCost() + getEdgeCost(pos, n), n, s));
            else if (((MazeState)s.getCameFrom())==null)
                neighbors.add(new MazeState(s.getCost() + getEdgeCost(pos, n), n, s));
        }
        if(pos.getRowIndex()+1<maze.getMaze().length && maze.getMaze()[pos.getRowIndex() + 1][pos.getColumnlIndex()]==0){
            n = new Position(pos.getRowIndex()+1 , pos.getColumnlIndex());
            if (((MazeState)s.getCameFrom())!=null && !n.equals(((MazeState)s.getCameFrom()).getPos()))
                neighbors.add(new MazeState(s.getCost() + getEdgeCost(pos, n), n, s));
            else if (((MazeState)s.getCameFrom())==null)
                neighbors.add(new MazeState(s.getCost() + getEdgeCost(pos, n), n, s));
        }
        if(pos.getRowIndex()+1<maze.getMaze().length && pos.getColumnlIndex()-1>=0 && maze.getMaze()[pos.getRowIndex() + 1][pos.getColumnlIndex() - 1]==0
                && (maze.getMaze()[pos.getRowIndex()+1][pos.getColumnlIndex()]==0 || maze.getMaze()[pos.getRowIndex() ][pos.getColumnlIndex()-1]==0)){
            n = new Position(pos.getRowIndex()+1 , pos.getColumnlIndex()-1);
            if (((MazeState)s.getCameFrom())!=null && !n.equals(((MazeState)s.getCameFrom()).getPos()))
                neighbors.add(new MazeState(s.getCost() + getEdgeCost(pos, n), n, s));
            else if (((MazeState)s.getCameFrom())==null)
                neighbors.add(new MazeState(s.getCost() + getEdgeCost(pos, n), n, s));
        }
        if(pos.getColumnlIndex()-1>=0 && maze.getMaze()[pos.getRowIndex()][pos.getColumnlIndex() - 1]==0){
            n = new Position(pos.getRowIndex(), pos.getColumnlIndex()-1);
            if (((MazeState)s.getCameFrom())!=null && !n.equals(((MazeState)s.getCameFrom()).getPos()))
                neighbors.add(new MazeState(s.getCost() + getEdgeCost(pos, n), n, s));
            else if (((MazeState)s.getCameFrom())==null)
                neighbors.add(new MazeState(s.getCost() + getEdgeCost(pos, n), n, s));
        }
        return neighbors;
    }

    public int getEdgeCost(Position from, Position to){
        return (from.getColumnlIndex()!=to.getColumnlIndex() && from.getRowIndex()!=to.getRowIndex()) ? move : crossMove;
    }

}
