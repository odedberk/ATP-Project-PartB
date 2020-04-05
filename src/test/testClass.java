package test;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.util.*;

public class testClass {
    public static void timeTaken (IMazeGenerator gen, int row, int col){
        System.out.println(String.format("Time taken to generate a %dx%d maze : %d mills",row,col,gen.measureAlgorithmTimeMillis(row,col)));

    }

    public static void whosTheBest(IMazeGenerator generator, int numberOfTest, int size){ //test out number of test and decides whos the fastes on average
        int sum;
        int avg;
        int min=0;
        ISearchable maze;
        ASearchingAlgorithm fastest=new BreadthFirstSearch();
        ASearchingAlgorithm[] searchers= {new BreadthFirstSearch(), new BestFirstSearch(), new DepthFirstSearch()};
        System.out.println("Running "+numberOfTest+" random Maze solving for each algorithm:");
        for (ASearchingAlgorithm searcher : searchers){
            sum=0;
            System.out.println(searcher.getName()+ " algorithm\n-------------------");
            for (int j=0; j<numberOfTest ; j++) {
                Maze m = generator.generate(size, size);
                maze = new SearchableMaze(m);
                long start = System.currentTimeMillis();
                searcher.solve(maze);
                long length=System.currentTimeMillis()-start;
                sum+=length;
                System.out.println(length+" mills");
//                System.out.println("nodes visited : " + searcher.getNumberOfNodesEvaluated());
            }
            avg=sum/numberOfTest;
            if (min==0 || avg<min) {
                min=avg;
                fastest=searcher;
            }
            System.out.println(String.format("Average time taken to solve a %dx%d maze with %s : %d mills\n-------------------", size, size, searcher.getName(), avg ));

        }
        System.out.println("Fastest algorithm was "+ fastest.getName()+" with "+ min+ " mills\n");
    }

    public static void main(String[] args) {

        AMazeGenerator mm =new  SimpleMazeGenerator();
        Maze tt = mm.generate(1000,1000);
        tt.print();

        ISearchingAlgorithm Bestfs = new BreadthFirstSearch();

        IMazeGenerator empty = new EmptyMazeGenerator();
        Maze testEmpty = empty.generate(10,10);
        timeTaken(empty,1000,1000);
        testEmpty.print();
        System.out.println();
        ISearchable SMaze = new SearchableMaze(testEmpty);
        Solution sol = Bestfs.solve(SMaze);
        ArrayList<AState> solutionPath = sol.getSolutionPath();
        for (int i = solutionPath.size()-1; i >=0 ; i--) {
            System.out.println(String.format("%s.%s", (solutionPath.size()-i), solutionPath.get(i)));
        }
        System.out.println();
        System.out.println();
        System.out.println(testEmpty.getGoalPosition().toString());
        IMazeGenerator simple = new SimpleMazeGenerator();
        Maze testSimple = simple.generate(10,10);
        timeTaken(simple,1000,1000);
        testSimple.print();
        System.out.println();
//        SMaze = new SearchableMaze(testSimple);
//        sol = bfs.solve(SMaze);
//        solutionPath = sol.getSolutionPath();
//        for (int i = solutionPath.size()-1; i >=0 ; i--) {
//            System.out.println(String.format("%s.%s", (solutionPath.size()-i), solutionPath.get(i)));
//        }
//        System.out.println();

        IMazeGenerator my = new MyMazeGenerator();


        whosTheBest(my, 5,1000);
//        myMaze.print();


        timeTaken(my,1000,1000);
        Maze myMaze = my.generate(1000  ,1000);
        System.out.println("Steps taken to generate: "+my.getSteps());
        SMaze = new SearchableMaze(myMaze);
        long start=System.currentTimeMillis();
        Bestfs.solve(SMaze);
        System.out.println(String.format("Time taken to solve a %dx%d maze with BFS : %d mills",myMaze.getMaze().length,myMaze.getMaze()[0].length,((System.currentTimeMillis()-start))));
        System.out.println("nodes visited : "+ Bestfs.getNumberOfNodesEvaluated());
        start=System.currentTimeMillis();
        Bestfs=new BestFirstSearch();
        Bestfs.solve(SMaze);
        System.out.println(String.format("Time taken to solve a %dx%d maze with Best First Search : %d mills",myMaze.getMaze().length,myMaze.getMaze()[0].length,((System.currentTimeMillis()-start))));
        System.out.println("nodes visited : "+ Bestfs.getNumberOfNodesEvaluated());
        Bestfs=new DepthFirstSearch();
        Bestfs.solve(SMaze);
        System.out.println(String.format("Time taken to solve a %dx%d maze with DFS : %d mills",myMaze.getMaze().length,myMaze.getMaze()[0].length,((System.currentTimeMillis()-start))));
        System.out.println("nodes visited : "+ Bestfs.getNumberOfNodesEvaluated());



//        solutionPath = sol.getSolutionPath();
//        if (solutionPath!=null)
//            for (int i = solutionPath.size()-1; i >=0 ; i--) {
//                System.out.println(String.format("%s.%s", (solutionPath.size()-i), solutionPath.get(i)));
//            }
//        System.out.println();

//        System.out.println(myMaze.getGoalPosition().toString());

//        AState a = new MazeState(1,new Position(0,0), null);
//        AState b = new MazeState(1,new Position(0,1), null);
//        AState c = new MazeState(1,new Position(1,0), null);
//        AState d = new MazeState(1,new Position(1,1), null);
//        Map q = new LinkedHashMap<String,AState>();
//        Set<AState> q1 = new LinkedHashSet<>();
////        q.put(b.toString(),b);
////        q.put(a.toString(),a);
////        q.put(c.toString(),c);
////        q.put(d.toString(),d);
////        System.out.println(q.isEmpty());
////        Object first = ((Map.Entry)q.entrySet().iterator().next()).getValue();
////        AState firstState= (AState)first;
//        q1.add(a);
//        q1.add(b);
//        q1.add(c);
//        q1.add(d);
//        AState firstState= q1.iterator().next();
//        System.out.println(firstState.toString());
//        System.out.println(firstState==null);
//        System.out.println(q1.contains(a));
//        Queue<AState> que =  new PriorityQueue<AState>();
//        AState one = new MazeState(10,new Position(0,0),null);
//        AState two = new MazeState(1,new Position(0,0),null);
//        AState three = new MazeState(90,new Position(0,0),null);
//        que.add(one);
//        que.add(two);
//        que.add(three);
//        while (!que.isEmpty()) {
//            System.out.println(que.poll().toString());
//        }
    }


}