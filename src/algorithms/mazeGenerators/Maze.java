package algorithms.mazeGenerators;

public class Maze {
    private Position start;
    private  Position goal;
    int[][] maze;

    public Maze(Position start, Position goal, int[][] maze) {
        this.start = start;
        this.goal = goal;
        this.maze = maze;
    }
    public Maze(byte [] byteMaze){
        int rowSize = binaryToInt(intToBinary(byteMaze[1],8)+intToBinary(byteMaze[0],8));
        int colSize = binaryToInt(intToBinary(byteMaze[3],8)+intToBinary(byteMaze[2],8));
        start=new Position(binaryToInt(intToBinary(byteMaze[5],8)+intToBinary(byteMaze[4],8)),binaryToInt(intToBinary(byteMaze[7],8)+intToBinary(byteMaze[6],8)));
        goal = new Position(binaryToInt(intToBinary(byteMaze[9],8)+intToBinary(byteMaze[8],8)),binaryToInt(intToBinary(byteMaze[11],8)+intToBinary(byteMaze[10],8)));
        maze = new int[rowSize][colSize];
        int pos=12;
        for(int i=0 ; i<rowSize; i++)
            for (int j=0; j<colSize; j++) {
                maze[i][j]=byteMaze[pos];
                pos++;
            }
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public void setGoal(Position goal) {
        this.goal = goal;
    }

    public Position getStartPosition() {
        return start;
    }

    public Position getGoalPosition() {
        return goal;
    }

    public void print(){
        for (int i=0 ; i<maze.length ; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (i==start.getRowIndex()&&j==start.getColumnIndex())
                    System.out.print("S  ");
                else if (i==goal.getRowIndex()&&j==goal.getColumnIndex())
                    System.out.print("E  ");
                else System.out.print(maze[i][j]+"  ");
            }
            System.out.println(); // print
        }
    }

    private String intToBinary(int a,int stringLength){
        String binary ="";
        while (a!=0){
            binary=a%2+binary;
            a=a/2;
        }
        while (binary.length()<stringLength)
            binary="0"+binary;
        return binary;
    }

    public int binaryToInt(String binary){
        int l = binary.length();
        int val=0;
        while (l>0){
            if(binary.charAt(l-1)=='1')
                val+=Math.pow(2,binary.length()-l);
            l--;
        }
        return val;
    }

    public byte[] toByteArray(){
        byte r1,r2,c1,c2,sr1,sr2,sc1,sc2,gr1,gr2,gc1,gc2;
        return null;
    }

    private byte[] splitInt(int input){
        String binary = intToBinary(input,16);
        String L = binary.substring(0,8), R=binary.substring(9);

        return null;
    }



}
