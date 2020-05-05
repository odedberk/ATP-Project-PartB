package algorithms.search;

import algorithms.mazeGenerators.Position;

    /**
     * a class representing a state inside a maze
     */
public class MazeState extends AState{
    Position pos; //row and column of the specific instance
    public MazeState(double cost, Position p, AState prev) {
        super(p.toString(),cost,prev);
        pos=p;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}
