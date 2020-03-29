package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    @Override
    public long measureAlgorithmTimeMillis(int row, int col) {
        long start = System.currentTimeMillis();
        generate(row,col);
        long end = System.currentTimeMillis();
        return System.end()-start;

    }
}
