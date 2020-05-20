package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

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
        ISearchingAlgorithm solver = new BreadthFirstSearch(); //change to config.getProperty("algorithm")
        Solution sol = solver.solve(new SearchableMaze(toSolve));
        try {
                toClient.writeObject(sol);
                toClient.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
