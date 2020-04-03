package algorithms.mazeGenerators;
import java.util.Random;


public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int row, int col) {
        steps=0;
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

//      printArray(maze); // Debug

        int sRow,sCol,eRow,eCol; //create start and goal positions
        if (col>3 && row>3) { //sufficient size
            sRow = rand.nextInt(row / 2);
            sCol = rand.nextInt(col / 2);
            while (maze[sRow][sCol] == 1) {
                sRow = rand.nextInt(row / 2);
                sCol = rand.nextInt(col / 2);
            }

            eRow = rand.nextInt(row / 2);
            eCol = rand.nextInt(col / 2) + col / 2;
            while (maze[eRow][eCol] == 1) {
                eRow = rand.nextInt(row / 2);
                eCol = rand.nextInt(col / 2) + col / 2;
            }
        }
        else { //small mazes
            sRow=0;
            sCol=0;
            eCol=col-1;
            eRow=row-1;
        }
        Position start = new Position(sRow,sCol); //start position
        Position end = new Position(eRow,eCol); //end position
        return new Maze(start,end,maze);
    }

    private void BuildWalls (int[][] currMaze, int left, int right, int top, int bottom){
        if (left+1>=right || top+1>=bottom)
            return;
        Random rand = new Random();
        int vertical, horizontal, passH,passV1,passV2;

        vertical = rand.nextInt(right - left) + left;
        while (vertical%2==0) // dont build walls on passage indices
            vertical = rand.nextInt(right - left) + left;

        horizontal = rand.nextInt(bottom - top) + top;
        while (horizontal%2==0) // dont build walls on passage indices
            horizontal = rand.nextInt(bottom - top) + top;

        for (int i=top; i<=bottom; i++) //partition vertically
            currMaze[i][vertical]=1;
        for (int i = left; i <= right; i++) //partition horizontally
            currMaze[horizontal][i] = 1;

        passH = rand.nextInt(bottom-horizontal+1)+horizontal; // horizontal passage
        while (passH==horizontal || passH %2 ==1)
            passH = rand.nextInt(bottom-horizontal+1)+horizontal;

        passV1 = rand.nextInt(vertical-left+1)+left; //first vertical passage
        while (passV1==vertical || passV1 %2 ==1 )
            passV1 = rand.nextInt(vertical-left+1)+left;

        passV2 = rand.nextInt(right-vertical+1)+vertical; //second vertical passage
        while (passV2==vertical || passV2 %2 ==1)
            passV2 = rand.nextInt(right-vertical+1)+vertical;

        currMaze[horizontal][passV1]=0;
        currMaze[horizontal][passV2]=0;
        currMaze[passH][vertical]=0;

//        printArray(currMaze); // debug
        steps++; //count steps for debugging
        BuildWalls(currMaze,left,vertical-1,top,horizontal-1);
        BuildWalls(currMaze,vertical+1,right,top,horizontal-1);
        BuildWalls(currMaze, left,vertical-1, horizontal+1, bottom);
        BuildWalls(currMaze, vertical+1,right, horizontal+1, bottom);

    }

    public void printArray(int[][] maze){
        for (int[] ints : maze) {
            for (int anInt : ints) {
                System.out.print(anInt + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getSteps() {
        return steps;
    }
}
