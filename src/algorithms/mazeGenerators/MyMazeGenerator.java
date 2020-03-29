package algorithms.mazeGenerators;

import javafx.util.Pair;

import java.util.Random;
import java.util.Stack;


public class MyMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int row, int col) {
        Stack<Position> dfsStack = new Stack<>();
        Random rand = new Random();
        int[][] maze = new int[row][col];
        for(int i=0 ; i<row ; i++){
            for (int j=0; j<col ; j++){
                maze[i][j]=1;
            }
        }
        for(int j=1 ; j<row ; j+=2){
            for (int i=1; i<col ; i++){
                if (i%2==1)
                    maze[i][j]=0;
            }
        }
        int sRow = 0;
        int sCol =0;
        while (maze[sRow][sCol]==1){
           sRow= rand.nextInt(row/2);
           sCol= rand.nextInt(col/2);
        }
        Position start = new Position(sRow,sCol); //start position

        int eRow = rand.nextInt(row/2) + row/2-1;
        int eCol = rand.nextInt(col/2) + col/2-1;
        while ((maze[eRow][eCol]==1)
//                ||(sRow==eRow && sCol==eCol)
//                || (sCol==eCol && (sCol==0 || sCol==col-1))
//                || (sRow==eRow && (sRow==0 || sRow==row-1))) { //make sure start!=end and not on same side
        ){
            eRow = rand.nextInt(row/2) + row/2-1;
            eCol =rand.nextInt(col/2) + col/2-1;
        }
        Position end = new Position(eRow,eCol); //end position
        return new Maze(start,end,maze);
    }
}
