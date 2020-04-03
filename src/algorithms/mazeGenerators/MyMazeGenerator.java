package algorithms.mazeGenerators;
import java.util.Random;


public class MyMazeGenerator extends AMazeGenerator {
    public static int steps=0;
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

//    @Override
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

        int sRow= rand.nextInt(row/3);
        int sCol= rand.nextInt(col/3);
        while (maze[sRow][sCol]==1){
           sRow= rand.nextInt(row/3);
           sCol= rand.nextInt(col/3);
        }
        Position start = new Position(sRow,sCol); //start position

        int eRow = rand.nextInt(row/3) + 2*row/3-1;
        int eCol = rand.nextInt(col/3) + 2*col/3-1;
        while (maze[eRow][eCol]==1){
            eRow = rand.nextInt(row/3) + 2*row/3-1;
            eCol =rand.nextInt(col/3) + 2*col/3-1;
        }
        Position end = new Position(eRow,eCol); //end position
//        printArray(maze);
        return new Maze(start,end,maze);
    }

    public int getSteps() {
        return steps;
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

        for (int i=top; i<=bottom; i++)
            currMaze[i][vertical]=1;
        for (int i = left; i <= right; i++)
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
        steps++;
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
            System.out.println(); // print
        }
        System.out.println(); // print

    }

//    @Override
//    public Maze generate (int row, int col){
//        Random rand=new Random();
//        ArrayList<Position> walls = new ArrayList<Position>();
//        int[][] maze = new int[row][col];
//        for (int i=0 ; i<maze.length; i++) {
//            for (int j=0; j<maze[0].length; j++)
//                maze[i][j] = 1;
//        }
//        printArray(maze);
//        maze[0][0]=0;
//        walls.add(new Position(1,0));
//        walls.add(new Position(0,1));
//
//
//
//
//
//
//        return new Maze(new Position(0,0),new Position(0,0),new int[1][1]);
//    }
//
//    public Position oneUnvisitedNeighbor(int[][] maze, Position p){
//        if (p.getRowIndex()<0 || p.getRowIndex()>=maze.length || p.getColIndex()<0 || p.getColIndex()>=maze[0].length)
//            return null;
//        int count=0;
//        int row=p.getRowIndex(), col=p.getColIndex();
//        int nRow, nCol;
//        if(col>=1 && col<maze[0].length-1 && row>=1 && row<maze.length-1 ){ //not in ends - 4 neighbors
//           count+= maze[row][col-1]==0 ? 1 : 0;
//           count+= maze[row][col+1]==0 ? 1 : 0 ;
//           count+= maze[row-1][col]==0 ? 1 : 0 ;
//           count+= maze[row+1][col]==0 ? 1 : 0 ;
//           return count==1 ? new Position(0,0), new Position(0,0);
//        }
//        return null;
//
//    }
}
