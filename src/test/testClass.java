package test;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.SimpleMazeGenerator;

public class testClass {
    public static void main(String[] args) {
        IMazeGenerator empty = new EmptyMazeGenerator();
        Maze testEmpty = empty.generate(10,10);
        System.out.println(empty.measureAlgorithmTimeMillis(1000,1000));
        testEmpty.print();

        IMazeGenerator simple = new SimpleMazeGenerator();
        Maze testSimple = simple.generate(10,10);
        System.out.println(simple.measureAlgorithmTimeMillis(1000,1000));
        testSimple.print();
        System.out.println();

        IMazeGenerator my = new MyMazeGenerator();
        Maze myMaze = my.generate(10,10);
        myMaze.print();


    }
}
