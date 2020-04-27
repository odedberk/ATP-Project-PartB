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
    public Maze(byte [] maze){
        int x = maze[0];



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
    private int margeTwoBytes(int a, int b){
//        String binary ="";
//        while (a!=0){
//            binary+=a%2;
//            a=a/10;
//        }
//        if(binary.length()<8){
//            for(int i=0; i<(8-binary.length()); i++)
//                binary+=0;
        //}
        return 0;
    }
    private String intToBinary(int a, int max){

        String binary ="";
        while (a!=0){
            binary=a%2+binary;
            a=a/2;
        }
        while (binary.length()<max)
            binary="0"+binary;
        return binary;
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
