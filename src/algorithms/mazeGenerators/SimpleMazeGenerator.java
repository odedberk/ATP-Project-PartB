package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int row, int col) {
        Random rand = new Random();
        int[][] maze = new int[row][col];
        for (int i=0 ; i<row ; i++) {
            for (int j = 0; j < col; j++)
                maze[i][j] = rand.nextInt(2);
        }
        int sRow = rand.nextInt(row);
        int sCol = rand.nextInt(col);
        Position start = new Position(sRow,sCol); //start position

        int eRow = rand.nextInt(row);
        int eCol = rand.nextInt(col);
        while (sRow==eRow && sCol==eCol) { //make sure start!=end
            eRow = rand.nextInt(row);
            eCol = rand.nextInt(col);
        }
        Position end = new Position(eRow,eCol); //end position
        return new Maze(start,end,maze);
    }
}
