package algorithms.mazeGenerators;

import javafx.util.Pair;

import java.util.Random;
import java.util.Stack;


public class MyMazeGenerator extends AMazeGenerator {

//    @Override
//    public Maze generate(int row, int col) {
//        Random rand = new Random();
//        int[][] maze = new int[row][col];
//        for(int i=0 ; i<row ; i++){
//            for (int j=0; j<col ; j++){
//                maze[i][j]=1;
//            }
//        }
//        for(int j=0 ; j<row ; j+=2){
//            for (int i=0; i<col ; i++){
//                if (i%2==0)
//                    maze[i][j]=0;
//            }
//        }
//        int sRow = 1;
//        int sCol =0;
//        while (maze[sRow][sCol]==1){
//           sRow= rand.nextInt(row/2);
//           sCol= rand.nextInt(col/2);
//        }
//        Position start = new Position(sRow,sCol); //start position
//
//        int eRow = rand.nextInt(row/2) + row/2-1;
//        int eCol = rand.nextInt(col/2) + col/2-1;
//        while ((maze[eRow][eCol]==1)
////                ||(sRow==eRow && sCol==eCol)
////                || (sCol==eCol && (sCol==0 || sCol==col-1))
////                || (sRow==eRow && (sRow==0 || sRow==row-1))) { //make sure start!=end and not on same side
//        ){
//            eRow = rand.nextInt(row/2) + row/2-1;
//            eCol =rand.nextInt(col/2) + col/2-1;
//        }
//        Position end = new Position(eRow,eCol); //end position
//        return new Maze(start,end,maze);
//    }

    @Override
    public Maze generate(int row, int col) {
        if (row<=1 && col<=1)
            return new Maze(new Position(0,0),new Position(0,0),new int[1][1]);
        Random rand = new Random();

        int[][] maze = new int[row][col];
        for(int i=0 ; i<row ; i++){
            for (int j=0; j<col ; j++){
                maze[i][j]=0;
            }
        }
        BuildWalls(maze,0,col-1,0,row-1);
        int sRow= rand.nextInt(row/2);
        int sCol= rand.nextInt(col/2);
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
//        printArray(maze);
        return new Maze(start,end,maze);
    }
//    private void BuildWalls (int[][] currMaze, int left, int right, int top, int bottom,int direction){
//        if (left>=right || top>=bottom || (direction!=1 && direction != -1))
//            return;
//        Random rand = new Random();
//        int split;
//        if (direction==1) {  // vertical
//            split = rand.nextInt(right - left) + left;
//            for (int i=top; i<=bottom; i++)
//                currMaze[i][split]=1;
//            int pass = rand.nextInt(bottom-top)+top;
//            currMaze[pass][split]=0;
//            printArray(currMaze);
//            BuildWalls(currMaze,left,split-1,top,bottom,-1);
//            BuildWalls(currMaze,split+1,right,top,bottom,-1);
//        }
//        else {  // vertical
//            split = rand.nextInt(bottom - top) + top;
//            for (int i = left; i <= right; i++)
//                currMaze[split][i] = 1;
//            int pass = rand.nextInt(right - left) + left;
//            currMaze[pass][split] = 0;
//            printArray(currMaze);
//            BuildWalls(currMaze, left, right, top, split-1, 1);
//            BuildWalls(currMaze, left, right, split+1, bottom, 1);
//        }
//    }


    private void BuildWalls (int[][] currMaze, int left, int right, int top, int bottom){
        if (left>=right || top>=bottom)
            return;
        Random rand = new Random();
        int vertical, horizontal, passH,passV1,passV2;
//        if (direction==1) {  // vertical
        vertical = rand.nextInt(right - left) + left;
        horizontal = rand.nextInt(bottom - top) + top;

        for (int i=top; i<=bottom; i++)
            currMaze[i][vertical]=1;
        for (int i = left; i <= right; i++)
            currMaze[horizontal][i] = 1;
        passH = rand.nextInt(bottom-horizontal+1)+horizontal;
        while (passH==horizontal)
            passH = rand.nextInt(bottom-horizontal+1)+horizontal;
        passV1 = rand.nextInt(vertical-left+1)+left;
        while (passV1==vertical && vertical!=left)
            passV1 = rand.nextInt(vertical-left+1)+left;
        passV2 = rand.nextInt(right-vertical+1)+vertical;
        while (passV2==vertical)
            passV2 = rand.nextInt(right-vertical+1)+vertical;
        currMaze[horizontal][passV1]=0;
        currMaze[horizontal][passV2]=0;
        currMaze[passH][vertical]=0;

//        printArray(currMaze);
        BuildWalls(currMaze,left,vertical-2,top,horizontal-2);
        BuildWalls(currMaze,vertical+2,right,top,horizontal-2);
        BuildWalls(currMaze, left,vertical-2, horizontal+2, bottom);
        BuildWalls(currMaze, vertical+2,right, horizontal+2, bottom);

    }
    public void printArray(int[][] maze){
        for (int[] ints : maze) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println(); // print
        }
        System.out.println(); // print

    }


}
