package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class Maze implements Serializable {
    private Position start;
    private  Position goal;
    int[][] maze;

    public Maze(Position start, Position goal, int[][] maze) {
        this.start = start;
        this.goal = goal;
        this.maze = maze;
    }
    public Maze(byte [] byteMaze){
        int rowSize = ((byteMaze[0] & 0xFF) <<8) | (byteMaze[1] & 0xFF);
        int colSize = ((byteMaze[2] & 0xFF) <<8) | (byteMaze[3] & 0xFF);
        start = new Position(((byteMaze[4] & 0xFF) <<8) | (byteMaze[5] & 0xFF), ((byteMaze[6] & 0xFF) <<8) | (byteMaze[7] & 0xFF));
        goal = new Position(((byteMaze[8] & 0xFF) <<8) | (byteMaze[9] & 0xFF), ((byteMaze[10] & 0xFF) <<8) | (byteMaze[11] & 0xFF));
        maze = new int[rowSize][colSize];
        int pos=12;
        for(int i=0 ; i<rowSize; i++)
            for (int j=0; j<colSize; j++)
                maze[i][j] = byteMaze[pos++];
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
//        synchronized (printLock) {
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[i].length; j++) {
                    if (i == start.getRowIndex() && j == start.getColumnIndex())
                        System.out.print("S  ");
                    else if (i == goal.getRowIndex() && j == goal.getColumnIndex())
                        System.out.print("E  ");
                    else System.out.print(maze[i][j] + "  ");
                }
                System.out.println(); // print
            }
//        }
    }

    public byte[] toByteArray(){
        byte r1,r2,c1,c2,sr1,sr2,sc1,sc2,gr1,gr2,gc1,gc2;
        ArrayList<Byte> list = new ArrayList<>();

        r1=(byte)(maze.length >>8);
        r2=(byte)(maze.length);
        c1=(byte)(maze[0].length>>8);
        c2=(byte)(maze[0].length);
        sr1=(byte)(getStartPosition().getRowIndex()>>8);
        sr2=(byte)(getStartPosition().getRowIndex());
        sc1=(byte)(getStartPosition().getColumnIndex()>>8);
        sc2=(byte)(getStartPosition().getColumnIndex());
        gr1=(byte)(getGoalPosition().getRowIndex()>>8);
        gr2=(byte)(getGoalPosition().getRowIndex());
        gc1=(byte)(getGoalPosition().getColumnIndex()>>8);
        gc2=(byte)(getGoalPosition().getColumnIndex ());

        byte[] bytes = new byte[12+ maze.length*maze[0].length];
        bytes[0] = r1;
        bytes[1] = r2;
        bytes[2]= c1;
        bytes[3] = c2;
        bytes[4] = sr1;
        bytes[5] = sr2;
        bytes[6] = sc1;
        bytes[7] = sc2;
        bytes[8] = gr1;
        bytes[9] = gr2;
        bytes[10] = gc1;
        bytes[11] = gc2;
        int k=12;
        for (int[] row : maze) {
            for (int cell : row) {
                bytes[k++] = (byte) cell;
            }
        }
        return bytes;
    }


    @Override
    public boolean equals(Object o) {
        return Arrays.equals(this.toByteArray(),((Maze)o).toByteArray());
    }


}
