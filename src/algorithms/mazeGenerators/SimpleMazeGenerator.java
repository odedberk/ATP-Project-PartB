package algorithms.mazeGenerators;

import java.nio.file.Paths;
import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int row, int col) {
        if (row<=1 && col<=1)
            return new Maze(new Position(0,0),new Position(0,0),new int[1][1]);
        Random rand = new Random();
        int[][] maze = new int[row][col];
        for (int i=1 ; i<row ; i+=1) {
            for (int j = 0; j < col; j++)
                maze[i][j] = rand.nextInt(2);
        }
        int sRow = rand.nextInt(row);
        int sCol = rand.nextInt(col);
        Position start = new Position(sRow,sCol); //start position

        int eRow = rand.nextInt(row);
        int eCol = rand.nextInt(col);
//        while ((sRow==eRow && sCol==eCol)
//                || (sCol==eCol && (sCol==0 || sCol==col-1))
//                || (sRow==eRow && (sRow==0 || sRow==row-1))) { //make sure start!=end and not on same side
//            eRow = rand.nextInt(row);
//            eCol = rand.nextInt(col);
//        }
        while ((sRow==eRow || sCol==eCol)) { //make sure start!=end and not on same side
            eRow = rand.nextInt(row);
            eCol = rand.nextInt(col);
        }
//        System.out.println(""+sRow+" "+sCol+ " " + eRow + " "+eCol);
        if(sRow<eRow){
            int r=rand.nextInt(eRow-sRow+1)+sRow;
            if(sCol<eCol){
                int c=rand.nextInt(eCol-sCol+1)+sCol;
                //System.out.println(r+" "+c);
                Path(maze,sRow,r,sCol,true);
                Path(maze,sCol,c,r,false);
                Path(maze,r,eRow,c,true);
                Path(maze,c,eCol,eRow,false);
            }
            else{
                int c=rand.nextInt(sCol-eCol+1)+eCol;
                Path(maze,sRow,r,sCol,true);
                Path(maze,c,sCol,r,false);
                Path(maze,r,eRow,c,true);
                Path(maze,eCol,c,eRow,false);
            }
        }
        else{
            int r=rand.nextInt(sRow-eRow+1)+eRow;
            if(sCol<eCol){
                int c=rand.nextInt(eCol-sCol+1)+sCol;
                Path(maze,eRow,r,eCol,true);
                Path(maze,c,eCol,r,false);
                Path(maze,r,sRow,c,true);
                Path(maze,sCol,c,sRow,false);

            }
            else{
                int c=rand.nextInt(sCol-eCol+1)+eCol;
                Path(maze,eRow,r,eCol,true);
                Path(maze,eCol,c,r,false);
                Path(maze,r,sRow,c,true);
                Path(maze,c,sCol,sRow,false);
            }
        }
        Position end = new Position(eRow,eCol); //end position
        return new Maze(start,end,maze);
    }
    private void Path(int [][]maze,int s,int f,int constant ,Boolean Row){
        if(Row){
            for(int i = s; i<=f; i++)
                maze[i][constant]=0;
        }
        else {
            for(int j = s; j<=f; j++)
                maze[constant][j]=0;
        }
    }
}
