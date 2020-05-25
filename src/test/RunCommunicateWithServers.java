package test;

import IO.*;
import Server.*;
import Client.*;
import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
public class RunCommunicateWithServers {
    public static void main(String[] args) {
        //Initializing servers
        Server mazeGeneratingServer = new Server(5400, 2000, new ServerStrategyGenerateMaze());
        Server solveSearchProblemServer = new Server(5401, 2000, new ServerStrategySolveSearchProblem());
        //Server stringReverserServer = new Server(5402, 1000, new ServerStrategyStringReverser());

        //Starting servers
        solveSearchProblemServer.start();
        mazeGeneratingServer.start();
        //stringReverserServer.start();

        //Communicating with servers
//        for (int i =0 ; i<20; i++) {
            CommunicateWithServer_SolveSearchProblem();
            CommunicateWithServer_MazeGenerating();
//        }
        //CommunicateWithServer_StringReverser();

        //Stopping all servers
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        mazeGeneratingServer.stop();
        solveSearchProblemServer.stop();
        //stringReverserServer.stop();
    }
    private static void CommunicateWithServer_MazeGenerating() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5400, new
                    IClientStrategy() {
                        @Override
                        public void clientStrategy(InputStream inFromServer,
                                                   OutputStream outToServer) {
                            try {
                                ObjectOutputStream toServer = new
                                        ObjectOutputStream(outToServer);
                                ObjectInputStream fromServer = new
                                        ObjectInputStream(inFromServer);
                                toServer.flush();
                                int[] mazeDimensions = new int[]{2, 2};
                                toServer.writeObject(mazeDimensions); //send maze dimensions to server
                                toServer.flush();
                                byte[] compressedMaze = (byte[])
                                        fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server
                                InputStream is = new MyDecompressorInputStream(new
                                        ByteArrayInputStream(compressedMaze));
                                byte[] decompressedMaze = new byte[3000 /*CHANGE SIZE ACCORDING TO YOU MAZE SIZE*/]; //allocating byte[] for the decompressed  maze -
                                is.read(decompressedMaze); //Fill decompressedMaze with bytes
                                Maze maze = new Maze(decompressedMaze);
                                Thread.sleep(1000);
                                maze.print();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    private static void CommunicateWithServer_SolveSearchProblem() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5401, new
                    IClientStrategy() {
                        @Override
                        public void clientStrategy(InputStream inFromServer,
                                                   OutputStream outToServer) {
                            try {
                                ObjectOutputStream toServer = new
                                        ObjectOutputStream(outToServer);
                                ObjectInputStream fromServer = new
                                        ObjectInputStream(inFromServer);
                                toServer.flush();
                                MyMazeGenerator mg = new MyMazeGenerator();
                                Maze maze = mg.generate(2, 2);
                                maze.print();
                                toServer.writeObject(maze); //send maze to server
                                toServer.flush();
                                Solution mazeSolution = (Solution)
                                        fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server
                                //Print Maze Solution retrieved from the server
                                System.out.println(String.format("Solution steps: %s", mazeSolution));
                                ArrayList<AState> mazeSolutionSteps =  mazeSolution.getSolutionPath();
                                for (int i = 0; i < mazeSolutionSteps.size(); i++) {
                                    System.out.println(String.format("%s. %s", i,
                                            mazeSolutionSteps.get(i).toString()));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}