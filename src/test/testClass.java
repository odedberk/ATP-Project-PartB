package test;

import algorithms.mazeGenerators.*;

public class testClass {
    public static void main(String[] args) {
//        IMazeGenerator empty = new EmptyMazeGenerator();
//        Maze testEmpty = empty.generate(10,10);
//        System.out.println(empty.measureAlgorithmTimeMillis(1000,1000));
//        testEmpty.print();
//        System.out.println();
//
//        IMazeGenerator simple = new SimpleMazeGenerator();
//        Maze testSimple = simple.generate(10,10);
//        System.out.println(simple.measureAlgorithmTimeMillis(1000,1000));
//        testSimple.print();
//        System.out.println();

        IMazeGenerator my = new MyMazeGenerator();
        System.out.println(my.measureAlgorithmTimeMillis(100,100));
        Maze myMaze = my.generate(10,20);
        myMaze.print();
        System.out.println();



    }
}