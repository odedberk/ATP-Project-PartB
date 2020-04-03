package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    public MazeState(double cost, Position p, AState prev) {
        super(p.toString(),cost,prev);
    }
}
