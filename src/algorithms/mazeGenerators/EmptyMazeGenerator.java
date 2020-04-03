package algorithms.mazeGenerators;

import java.util.Random;

public class EmptyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int row, int col) {
        if (row<=1 && col<=1)
            return new Maze(new Position(0,0),new Position(0,0),new int[1][1]);
        int[][] maze = new int[row][col];
        for (int i=0 ; i<row ; i++) {
            for (int j = 0; j < col; j++)
                maze[i][j] = 0;
        }
        Random rand = new Random();
        int sRow = rand.nextInt(row);
        int sCol = rand.nextInt(col);
        Position start = new Position(sRow,sCol); //start position

        int eRow = rand.nextInt(row);
        int eCol = rand.nextInt(col);
        while ((sRow==eRow && sCol==eCol)
                || (sCol==eCol && (sCol==0 || sCol==col-1))
                || (sRow==eRow && (sRow==0 || sRow==row-1))) { //make sure start!=end and not on same side
            eRow = rand.nextInt(row);
            eCol = rand.nextInt(col);
        }
        Position end = new Position(eRow,eCol); //end position
        return new Maze(start,end,maze);


    }


}
