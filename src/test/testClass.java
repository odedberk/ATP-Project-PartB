package test;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.SimpleMazeGenerator;

public class testClass {
    public static void main(String[] args) {
        int[][] newMaze =new int[10][10];
        for (int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                if(i%2==0)
                    newMaze[i][j]=1;
                else
                    newMaze[i][j]=0;
            }
        }
        for (int j=0; j<10; j++){
            for(int i=0; i<10; i++){
                if(j%2==0)
                    newMaze[i][j]=1;
            }
        }


        IMazeGenerator empty = new EmptyMazeGenerator();
        Maze testEmpty = empty.generate(10,10);
        System.out.println(empty.measureAlgorithmTimeMillis(1000,1000));
        //testEmpty.print();

        IMazeGenerator simple = new SimpleMazeGenerator();
        Maze testSimple = simple.generate(10,10);
        System.out.println(simple.measureAlgorithmTimeMillis(1000,1000));
//        testSimple.print();
    }
}
