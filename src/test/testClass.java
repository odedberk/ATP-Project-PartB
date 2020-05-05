package test;

import algorithms.mazeGenerators.*;
import algorithms.search.*;
import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class testClass {
    public static void timeTaken (IMazeGenerator gen, int row, int col){
        System.out.println(String.format("Time taken to generate a %dx%d %s maze : %d mills",row,col,gen.getClass(), gen.measureAlgorithmTimeMillis(row,col)));
    }
//
//    public static void whosTheBest(IMazeGenerator generator, int numberOfTest, int size){ //test out number of test and decides whos the fastes on average
//        int[] sum = new int[3];
//        int[] avg = new int[3];
//        int min=0;
//        ISearchable maze;
//        ASearchingAlgorithm fastest=new BreadthFirstSearch();
//        ASearchingAlgorithm[] searchers= {new DepthFirstSearch(), new BreadthFirstSearch(), new BestFirstSearch()};
//        System.out.println("Running "+numberOfTest+" random Maze solving for each algorithm\n-------------------");
//        for (int j=1; j<=numberOfTest ; j++) {
//            Maze m = generator.generate(size, size);
//            maze = new SearchableMaze(m);
//            for (int i=0 ; i<searchers.length; i++){
//                sum[i]=0;
//                long start = System.currentTimeMillis();
//                searchers[i].solve(maze);
//                long length=System.currentTimeMillis()-start;
//                sum[i]+=length;
//                avg[i]=((avg[i]*j-1)+sum[i])/j;
//            }
//        }
//        System.out.println(String.format("Average time taken to solve %d %dx%d mazes:", numberOfTest,size,size));
//        for (int i=0 ; i<searchers.length; i++){
//            System.out.println(String.format("With %s : %d mills\n--------------------------------", searchers[i].getName(), avg[i] ));
//            if (min==0 || avg[i] <min) {
//                fastest = searchers[i];
//                min=avg[i];
//            }
//        }
//        System.out.println("Fastest algorithm was "+ fastest.getName()+" with "+ min+ " mills\n");
//    }
//
    public static void main(String[] args) {

        byte[] b = {0,0,1,1,1,0,0,0,2};
        Map<String,Integer> codes = new HashMap();
        ArrayList<Pair<Integer,Integer>> array = new ArrayList<>();
        int arrIndex=0;
        int k=0;
        codes.put(String.valueOf(b[k++]),arrIndex);
        array.add(arrIndex++,new Pair(b[k],-1));
        for (; k<b.length; k++){
            int pointer=-1;
            String current = String.valueOf(b[k]);
            while (codes.containsKey(current) && k<b.length-1){ // 0 00 01 010
                pointer=codes.get(current);
                k++;
                current+=String.valueOf(b[k]);
            }
            if (codes.containsKey(current) && k==b.length-1) {
                array.add(arrIndex, new Pair(2, codes.get(current)));
                codes.put(current,arrIndex);
            }
            else{
                array.add(arrIndex,new Pair(b[k],pointer));
                codes.put(current,arrIndex++);
            }
        }

//        byte b = 12;
//        System.out.println(String.valueOf(b));
//
//
//        MyMazeGenerator maze = new MyMazeGenerator();
//        Maze m = maze.generate(900 ,200);
////        m.setGoal(new Position(30,8));
////        m.setStart(new Position(2,4));
//        byte[] bytes = m.toByteArray();
////        for (byte b : bytes) {
////            System.out.print(b+", ");
////        }
//        System.out.println();
////        m.print();
//
//        System.out.println();
//        Maze m1= new Maze(bytes);
//        System.out.println(m.equals(m1));
//        bytes=m1.toByteArray();
////        for (byte b : bytes) {
////            System.out.print(b+", ");
////        }


//        AMazeGenerator mm =new SimpleMazeGenerator();
//        Maze tt = mm.generate(20,20);
////        tt.print();
//        whosTheBest(mm,5,1000);
//
//        File file = new File("C:\\Users\\User\\IdeaProjects\\ATP-Project-PartA\\test.txt");
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        int a = 2;
//        byte b=(int)a;
//        System.out.println(b);

//        int a =10;
//        String binary ="";
//        while (a!=0){
//            binary=a%2+binary;
//            a=a/2;
//        }

//        String binary = "110101";
//        int l = binary.length();
//        byte val=0;
//        while (l>0){
//            if(binary.charAt(l-1)=='1')
//                val+=Math.pow(2,binary.length()-l);
//            l--;
//        }
//        System.out.println(val );

//        int sum =0, i=0;
//        while (sum<1000000){
//            sum+=i*Math.pow(2,i);
//            i++;
//        }
//        System.out.println(sum+" "+i);
//
//        sum =0;
//        int j=0;
//        while (j<i){
//            sum+=Math.pow(2,j);
//            j++;
//        }
//        System.out.println(sum);


//
//        FileOutputStream f=null;
//        try {
//         f= new FileOutputStream("C:\\Users\\User\\IdeaProjects\\ATP-Project-PartA\\test.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        byte byte1 = 127;
//        try {
//            f.write(byte1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        FileInputStream f1=null;
//        try {
//           f1 = new FileInputStream("C:\\Users\\User\\IdeaProjects\\ATP-Project-PartA\\test.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
////
//        int byte2=0;
//        try {
//            byte2 = f1.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(byte2);


//
//        ISearchingAlgorithm Bestfs = new BreadthFirstSearch();
//
//        IMazeGenerator my = new MyMazeGenerator();
//        Maze testEmpty = my.generate(20,30);
////        timeTaken(my,1000,1000);
//        testEmpty.print();
//        System.out.println();
//        ISearchable SMaze = new SearchableMaze(testEmpty);
//        Solution sol = Bestfs.solve(SMaze);
//        ArrayList<AState> solutionPath = sol.getSolutionPath();
//        for (int i = 0; i < solutionPath.size(); i++) {
//            System.out.println(String.format("%s.   %s",i,solutionPath.get(i)));
//        }
//        System.out.println();


//        System.out.println();
//        System.out.println(testEmpty.getGoalPosition().toString());
//        IMazeGenerator simple = new SimpleMazeGenerator();
//        Maze testSimple = simple.generate(20,20);
//        timeTaken(simple,1000,1000);
//        testSimple.Print();
//        System.out.println();
////        SMaze = new SearchableMaze(testSimple);
////        sol = bfs.solve(SMaze);
////        solutionPath = sol.getSolutionPath();
////        for (int i = solutionPath.size()-1; i >=0 ; i--) {
////            System.out.println(String.format("%s.%s", (solutionPath.size()-i), solutionPath.get(i)));
////        }
////        System.out.println();
//
//        IMazeGenerator my = new MyMazeGenerator();
//
//
//        whosTheBest(my, 5,1000);
////        myMaze.print();
//
//
//        timeTaken(my,1000,1000);
//        Maze myMaze = my.generate(1000  ,1000);
//        System.out.println("Steps taken to generate: "+my.getSteps());
//        SMaze = new SearchableMaze(myMaze);
//        long start=System.currentTimeMillis();
//        Bestfs.solve(SMaze);
//        System.out.println(String.format("Time taken to solve a %dx%d maze with BFS : %d mills",myMaze.getMaze().length,myMaze.getMaze()[0].length,((System.currentTimeMillis()-start))));
//        System.out.println("nodes visited : "+ Bestfs.getNumberOfNodesEvaluated());
//        start=System.currentTimeMillis();
//        Bestfs=new BestFirstSearch();
//        Bestfs.solve(SMaze);
//        System.out.println(String.format("Time taken to solve a %dx%d maze with Best First Search : %d mills",myMaze.getMaze().length,myMaze.getMaze()[0].length,((System.currentTimeMillis()-start))));
//        System.out.println("nodes visited : "+ Bestfs.getNumberOfNodesEvaluated());
//        Bestfs=new DepthFirstSearch();
//        Bestfs.solve(SMaze);
//        System.out.println(String.format("Time taken to solve a %dx%d maze with DFS : %d mills",myMaze.getMaze().length,myMaze.getMaze()[0].length,((System.currentTimeMillis()-start))));
//        System.out.println("nodes visited : "+ Bestfs.getNumberOfNodesEvaluated());
//
//
//
////        solutionPath = sol.getSolutionPath();
////        if (solutionPath!=null)
////            for (int i = solutionPath.size()-1; i >=0 ; i--) {
////                System.out.println(String.format("%s.%s", (solutionPath.size()-i), solutionPath.get(i)));
////            }
////        System.out.println();
//
////        System.out.println(myMaze.getGoalPosition().toString());
//
////        AState a = new MazeState(1,new Position(0,0), null);
////        AState b = new MazeState(1,new Position(0,1), null);
////        AState c = new MazeState(1,new Position(1,0), null);
////        AState d = new MazeState(1,new Position(1,1), null);
////        Map q = new LinkedHashMap<String,AState>();
////        Set<AState> q1 = new LinkedHashSet<>();
//////        q.put(b.toString(),b);
//////        q.put(a.toString(),a);
//////        q.put(c.toString(),c);
//////        q.put(d.toString(),d);
//////        System.out.println(q.isEmpty());
//////        Object first = ((Map.Entry)q.entrySet().iterator().next()).getValue();
//////        AState firstState= (AState)first;
////        q1.add(a);
////        q1.add(b);
////        q1.add(c);
////        q1.add(d);
////        AState firstState= q1.iterator().next();
////        System.out.println(firstState.toString());
////        System.out.println(firstState==null);
////        System.out.println(q1.contains(a));
////        Queue<AState> que =  new PriorityQueue<AState>();
////        AState one = new MazeState(10,new Position(0,0),null);
////        AState two = new MazeState(1,new Position(0,0),null);
////        AState three = new MazeState(90,new Position(0,0),null);
////        que.add(one);
////        que.add(two);
////        que.add(three);
////        while (!que.isEmpty()) {
////            System.out.println(que.poll().toString());
        }
//    }
//
//
}