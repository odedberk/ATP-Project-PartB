package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.Collection;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    //private AMazeGenerator generator;

//    public ServerStrategyGenerateMaze() {
//    }
//
//    public AMazeGenerator getGenerator() {
//        return generator;
//    }
//
//    public void setGenerator(AMazeGenerator generator) {
//        this.generator = generator;
//    }

    @Override
    public void handleClient(InputStream inputStream, OutputStream outputStream) {
        MyCompressorOutputStream out = new MyCompressorOutputStream(outputStream);
        try {
            ObjectInputStream input= new ObjectInputStream(inputStream);
            int [] sizes = (int[]) input.readObject();
            AMazeGenerator generator=fGenerator();
            Maze newMaze = generator.generate(sizes[0],sizes[1]);
            out.write(newMaze.toByteArray());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private AMazeGenerator fGenerator(){
        String generatorType = Configurations.getProperty("generator");
        AMazeGenerator generator;
        if(generatorType.equals("MyMazeGenerato")){
            generator = new MyMazeGenerator();
        }
        else if(generatorType.equals("SimpleMazeGenerator")){
            generator = new SimpleMazeGenerator();
        }
        else {
            generator = new EmptyMazeGenerator();
        }
        return generator;
    }

}
