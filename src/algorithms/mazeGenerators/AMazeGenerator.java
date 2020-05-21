package algorithms.mazeGenerators;

import java.io.Serializable;

public abstract class AMazeGenerator implements IMazeGenerator, Serializable {
    public int steps=0; //Debug - recursions taken to create the maze

    @Override
    public long measureAlgorithmTimeMillis(int row, int col) {
        long start = System.currentTimeMillis();
        generate(row,col);
        long end = System.currentTimeMillis();
        return end-start;
    }

    public int getSteps() {
        return steps;
    }
}
