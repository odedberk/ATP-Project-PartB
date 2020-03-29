package algorithms.mazeGenerators;

public class Maze {
    private Position start;
    private  Position goal;
    private int maze[][];


    public void setStart(Position start) {
        this.start = start;
    }

    public void setGoal(Position goal) {
        this.goal = goal;
    }

    public Position getStartPosition() {
        return start;
    }

    public Position getGoalPosition() {
        return goal;
    }
}
