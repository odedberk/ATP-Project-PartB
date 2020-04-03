package test;

import algorithms.mazeGenerators.*;

public class testClass {
    public static void timeTaken (IMazeGenerator gen, int row, int col){
        System.out.println(String.format("Time taken to generate a %dx%d maze : %d mills",row,col,gen.measureAlgorithmTimeMillis(row,col)));

    }
    public static void main(String[] args) {
        IMazeGenerator empty = new EmptyMazeGenerator();
        Maze testEmpty = empty.generate(10,10);
        timeTaken(empty,1000,1000);
        testEmpty.print();
        System.out.println();

        IMazeGenerator simple = new SimpleMazeGenerator();
        Maze testSimple = simple.generate(10,10);
        timeTaken(simple,1000,1000);
        testSimple.print();
        System.out.println();

        IMazeGenerator my = new MyMazeGenerator();
        timeTaken(my,1000,1000);
        Maze myMaze = my.generate(45,45);
        myMaze.print();
        System.out.println("Steps taken: "+my.getSteps());


    }
}