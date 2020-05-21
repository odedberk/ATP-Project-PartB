package Server;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    public static Hashtable<String, String> solvedMazes= new Hashtable<>(); //<Maze,filePath>
    private static AtomicInteger fileID = new AtomicInteger(0);
    @Override
    public void handleClient(InputStream inputStream, OutputStream outputStream) throws IOException {
        ObjectInputStream fromClient = new ObjectInputStream(inputStream);
        ObjectOutputStream toClient = new ObjectOutputStream(outputStream);
        Maze toSolve=null;
        try {
            toSolve = (Maze)fromClient.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        SearchableMaze searchableMaze = new SearchableMaze(toSolve);
//        ISearchingAlgorithm solver = getSolver();
        ISearchingAlgorithm solver = new BreadthFirstSearch();  //DEBUG
        Solution sol = getOrSolve(searchableMaze,solver);
        Solution sol2 = getOrSolve(searchableMaze,solver);

        try {
                toClient.writeObject(sol);
                toClient.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ISearchingAlgorithm getSolver(){
        String algorithm = Configurations.getProperty("algorithm");
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

    private Solution getOrSolve(SearchableMaze toSearch, ISearchingAlgorithm solver){
        boolean exists=false;
        Solution solution=null;
        byte[] maze = toSearch.getMaze().toByteArray();
        if (solvedMazes.contains(maze.toString())){
            exists=true;
            try {
                FileInputStream fileInputStream = new FileInputStream(System.getProperty("java.io.tmpdir")+"\\"+solvedMazes.get(maze));
//                FileInputStream fileInputStream = new FileInputStream(System.getProperty("java.io.tmpdir")+"\\mazeSol_0"); //DEBUG
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                solution = (Solution)objectInputStream.readObject();
                objectInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (!exists) {
            System.out.println("This maze wasnt solved before"); // DEBUG
            solution=solver.solve(toSearch);
            saveToFile(toSearch.getMaze(), solution);
        }
        return solution;
    }

    private void saveToFile (Maze toSave, Solution sol){
        String fileName = "mazeSol_"+fileID.getAndIncrement();
        try {
            FileOutputStream outputStream = new FileOutputStream(System.getProperty("java.io.tmpdir")+"\\"+fileName);
            ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);
            objectOutput.writeObject(sol);
            objectOutput.flush();
            objectOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        solvedMazes.put(toSave.toByteArray().toString(),fileName);
    }
}
