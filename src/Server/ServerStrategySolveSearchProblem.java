package Server;

import algorithms.mazeGenerators.Maze;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {

    @Override
    public void handleClient(InputStream inputStream, OutputStream outputStream) throws IOException {
        ObjectInputStream fromClient = new ObjectInputStream(inputStream);
        ObjectOutputStream toClient = new ObjectOutputStream(outputStream);
        try {
            Maze toSolve = (Maze)fromClient.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String phrase;
        try {
            String reversedPhrase;
            while (!(phrase = fromClient.readLine()).equals("Thanks!")) {
                reversedPhrase = new StringBuilder(phrase).reverse().toString();
                toClient.writeObject(reversedPhrase+"\r\n");
                toClient.flush();
            }
            toClient.writeObject("you welcome, bye!\r\n");
            toClient.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
