package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    private AMazeGenerator generator = new MyMazeGenerator();

    public ServerStrategyGenerateMaze() {}

    public AMazeGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(AMazeGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void handleClient(InputStream inputStream, OutputStream outputStream) {
        MyCompressorOutputStream out = new MyCompressorOutputStream(outputStream);
        try {
            ObjectInputStream input= new ObjectInputStream(inputStream);
            int [] sizes = (int[]) input.readObject();
            Maze newMaze = generator.generate(sizes[0],sizes[1]);
            out.write(newMaze.toByteArray());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
