package test;
import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import java.io.*;
import java.util.Arrays;

public class RunCompressDecompressMaze {
    public static void main(String[] args) {
        int row = 1000; int col = 100;
        String mazeFileName = "compresseddMaze"+row+"x"+col+".maze";
        String uncompressed = "rawMaze"+row+"x"+col+".maze"; //TESTING
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = mazeGenerator.generate(row, col); //Generate new maze
        try {
            // save maze to a file
            OutputStream compressed = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
            //TESTING
//            ObjectOutputStream raw = new ObjectOutputStream(new FileOutputStream(uncompressed));
//            raw.writeObject(maze.getMaze());
            OutputStream raw = new FileOutputStream(uncompressed);
            raw.write(maze.toByteArray());
            raw.flush();
            raw.close();
            //TESTING
            compressed.write(maze.toByteArray());
            compressed.flush();
            compressed.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte savedMazeBytes[] = new byte[0];
        try {
            //read maze from file
            InputStream in = new MyDecompressorInputStream(new
                    FileInputStream(mazeFileName));
            savedMazeBytes = new byte[maze.toByteArray().length];
            in.read(savedMazeBytes);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Maze loadedMaze = new Maze(savedMazeBytes);
        boolean areMazesEquals =
                Arrays.equals(loadedMaze.toByteArray(),maze.toByteArray());
        System.out.println(String.format("Mazes equal: %s",areMazesEquals));
        File rawFile = new File(uncompressed);
        File compressedFile = new File(mazeFileName);
        System.out.println("Uncompressed file size :"+ rawFile.length()+" bytes");
        System.out.println("Compressed file size :"+compressedFile.length()+" bytes");
        System.out.println("Compression Ratio : "+ (100-((double)compressedFile.length()/rawFile.length())*100)+" %" );
//maze should be equal to loadedMaze
    }
}