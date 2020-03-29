package algorithms.mazeGenerators;

public class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRowIndex() {
        return row;
    }

    public int getColIndex() {
        return col;
    }
    public void print(){
        System.out.println("Row: "+row+" col: "+col);
    }
}
