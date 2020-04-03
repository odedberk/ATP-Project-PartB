package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    private Position pos;
    public MazeState(double cost, Position p, AState prev) {
        super(p.toString(),cost,prev);
        pos=p;
    }

    public Position getPos() {
        return pos;
    }
}
