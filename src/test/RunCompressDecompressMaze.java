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
        int row = 500; int col = 500;
        String mazeFileName = "compressedMaze"+row+"x"+col+".maze";
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

        long uncompressedSize = new File(uncompressed).length();
        long compressedSize = new File(mazeFileName).length();
        System.out.println("----------------------------");
        System.out.println("Compression Summary for "+row+"x"+col+" maze :");
        System.out.println("Uncompressed file size :"+ uncompressedSize+" bytes");
        System.out.println("Compressed file size :"+compressedSize+" bytes");
        System.out.println("Compression Ratio : "+ (100-((double)compressedSize/uncompressedSize)*100)+" %" );
        System.out.println("----------------------------");

        new File(uncompressed).delete();
        new File(mazeFileName).delete();
//maze should be equal to loadedMaze
    }
}