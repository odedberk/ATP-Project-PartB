package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;
import java.util.Collection;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    /**
     * get maze size from client and return the a compressed maze to the client
     * @param inputStream
     * @param outputStream
     */
    @Override
    public void handleClient(InputStream inputStream, OutputStream outputStream) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            ObjectInputStream input= new ObjectInputStream(inputStream);

            ByteArrayOutputStream compressed = new ByteArrayOutputStream();
            MyCompressorOutputStream compress = new MyCompressorOutputStream(compressed);

            System.out.println("received streams in server strategy"); //DEBUG
            int [] sizes = (int[]) input.readObject();
            System.out.println(sizes[0]+" "+sizes[1]); //DEBUG
//            AMazeGenerator generator=fGenerator();
            AMazeGenerator generator=new MyMazeGenerator(); //DEBUG
            Maze newMaze = generator.generate(sizes[0],sizes[1]);
//            newMaze.print(); //DEBUG

            compress.write(newMaze.toByteArray());
            compress.flush();

            out.writeObject(compressed.toByteArray());
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * function to get the most update AMazeGenerator from the Configurations file
     * @return AMazeGenerator
     */
    private AMazeGenerator fGenerator(){
        String generatorType = Configurations.getProperty("generator");
        AMazeGenerator generator;
        if(generatorType.equals("MyMazeGenerator")){
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
