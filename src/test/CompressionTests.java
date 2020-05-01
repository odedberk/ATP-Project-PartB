package test;
import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import java.io.*;
import java.util.Arrays;

public class CompressionTests {
    public static void main(String[] args) {
        int[] size = new int[100];

        double[] ratios = new double[size.length];
        String output = "output.csv";
        FileWriter file =null ;
        try {
            file= new FileWriter(output,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printer = new PrintWriter(file);

        for (int i =0 ; i <size.length; i++) {
            size[i]=10*(i+1);
            ratios[i]=test(size[i]);
            System.out.println("---------------------------");
            System.out.println("Maze size: "+size[i]+"x"+size[i]);
            System.out.println("Compression Ration: "+ratios[i]);
            System.out.println("---------------------------");
            printer.write(size[i]+", "+ratios[i]+"\n");
        }
        printer.close();


//maze should be equal to loadedMaze
    }

    public static double test (int row){
        String mazeFileName = "compressedMaze"+row+"x"+row+".maze";
        String uncompressed = "rawMaze"+row+"x"+row+".maze"; //TESTING
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = mazeGenerator.generate(row, row); //Generate new maze
        try {
            // save maze to a file
            OutputStream compressed = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
            //TESTING
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
//        System.out.println(String.format("Mazes equal: %s",areMazesEquals));

        long uncompressedSize = new File(uncompressed).length();
        long compressedSize = new File(mazeFileName).length();
//        System.out.println("----------------------------");
//        System.out.println("Compression Summary for "+row+"x"+row+" maze :");
//        System.out.println("Uncompressed file size :"+ uncompressedSize+" bytes");
//        System.out.println("Compressed file size :"+compressedSize+" bytes");
//        System.out.println("Compression Ratio : "+ (100-((double)compressedSize/uncompressedSize)*100)+" %" );
//        System.out.println("----------------------------");
        if (new File(uncompressed).delete());
//            System.out.println("uncompressed deleted");
        if (new File(mazeFileName).delete());
//            System.out.println("compressed deleted");

        return (100-((double)compressedSize/uncompressedSize)*100);

    }
}