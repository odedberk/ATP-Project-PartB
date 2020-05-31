package Server;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A server strategy used to receive a decompressed maze and return a solution for it.
 * Saves each solved maze (in each run) in a file, to quickly draw the solution from the disk instead
 * of having to solve it all over again.
 */
public class ServerStrategySolveSearchProblem implements IServerStrategy {
    public static Hashtable<Integer, String> solvedMazes= new Hashtable<>(); //<Maze,filePath>
    private static AtomicInteger fileID = new AtomicInteger(0);

    @Override
    public void handleClient(InputStream inputStream, OutputStream outputStream) throws IOException {
        ObjectInputStream fromClient = new ObjectInputStream(inputStream);
        ObjectOutputStream toClient = new ObjectOutputStream(outputStream);
        Maze toSolve=null;
        try {
            toSolve = (Maze)fromClient.readObject();//get the maze
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        SearchableMaze searchableMaze = new SearchableMaze(toSolve);
//        ISearchingAlgorithm solver = getSolver();
        ISearchingAlgorithm solver = new BreadthFirstSearch();  //DEBUG
        Solution sol = getOrSolve(searchableMaze,solver);
//        Solution sol2 = getOrSolve(searchableMaze,solver); //DEBUG

        try {
                toClient.writeObject(sol);//return the solution
                toClient.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * function to get the most update ISearchingAlgorithm from the Configurations file
     * @return
     */
    private ISearchingAlgorithm getSolver(){
        String algorithm = Server.Configurations.getProperty("algorithm");
        ISearchingAlgorithm solver=new BreadthFirstSearch();
        switch (algorithm) {
            case "BreadthFirstSearch":
                solver = new BreadthFirstSearch();
                break;
            case "BestFirstSearch":
                solver = new BestFirstSearch();
                break;
            case "DepthFirstSearch":
                solver = new DepthFirstSearch();
                break;
        }
        return solver;
    }

    /**
     *
     * chack if there is alrdy a solution for "toSearch" maze.
     * if there is, return the solution.
     * else solve save the solution and return solution it
     */
    private Solution getOrSolve(SearchableMaze toSearch, ISearchingAlgorithm solver){
        boolean exists=false;
        Solution solution=null;
        int hashcode = toSearch.getMaze().hashCode();//the key for the maze in the solvedMazes dictionary
        if (solvedMazes.containsKey(hashcode)){
            exists=true;
            try {
                FileInputStream fileInputStream = new FileInputStream(System.getProperty("java.io.tmpdir")+"\\"+solvedMazes.get(hashcode));
//                FileInputStream fileInputStream = new FileInputStream(System.getProperty("java.io.tmpdir")+"\\mazeSol_0"); //DEBUG
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                solution = (Solution)objectInputStream.readObject();//read the solution from file
//                if (solution!=null)
//                    System.out.println("Solution found on File"); // DEBUG
                objectInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (!exists) {//solve from scratch and save ths solution
//            System.out.println("This maze wasnt solved before"); // DEBUG
            solution=solver.solve(toSearch);
            saveToFile(hashcode, solution);
        }
        return solution;
    }

    /**
     *
     * save solution in a file
     */
    private void saveToFile (int toSave, Solution sol){
        String fileName = "mazeSol_"+fileID.getAndIncrement();//get new file name
        try {//write the solution to file
            FileOutputStream outputStream = new FileOutputStream(System.getProperty("java.io.tmpdir")+"\\"+fileName);
            ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);
            objectOutput.writeObject(sol);
            objectOutput.flush();
            objectOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        solvedMazes.put(toSave,fileName);
    }
}
