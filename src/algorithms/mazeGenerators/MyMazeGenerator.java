package algorithms.mazeGenerators;

public class MyMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int row, int col) {
        int[][] newMaze =new int[row][col];
        for (int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(i%2==0)
                    newMaze[i][j]=1;
                else
                    newMaze[i][j]=0;
            }
        }
        for (int j=0; j<col; j++){
            for(int i=0; i<row; i++){
                if(j%2==0)
                    newMaze[i][j]=1;
                else
                    newMaze[i][j]=0;
            }
        }
        return null;
    }
}
