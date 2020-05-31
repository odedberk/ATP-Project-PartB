package algorithms.mazeGenerators;

/**
 * Interface of a maze generator
 */
public interface IMazeGenerator {
    Maze generate(int row, int col);
    long measureAlgorithmTimeMillis(int row, int col);
    int getSteps();//Debug
}
