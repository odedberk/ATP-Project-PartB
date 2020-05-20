package Server;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {

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
        ISearchingAlgorithm solver = getSolver();
        Solution sol = solver.solve(new SearchableMaze(toSolve));
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
        if(algorithm.equals("BreadthFirstSearch")){
            solver = new BreadthFirstSearch();
        }
        else if(algorithm.equals("BestFirstSearch")){
            solver = new BestFirstSearch();
        }
        else if (algorithm.equals("DepthFirstSearch")) {
            solver = new DepthFirstSearch();
        }
        return solver;
    }
}
