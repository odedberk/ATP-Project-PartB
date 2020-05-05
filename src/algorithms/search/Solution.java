package algorithms.search;

import java.util.ArrayList;

public class Solution {
    ArrayList<AState> solutionPath;

    /**
     * Construct a Solution from a given goal state
     * containing pointers for successors all the way
     * back to the start state.
     * @param goal the goal state containing the relevant information
     */
    public Solution(AState goal) {
        if (goal!=null)
            solutionPath= new ArrayList<>();
        while(goal!=null){ //iterate back to the first State in the path
            solutionPath.add(0,goal);
            goal=goal.getCameFrom();
        }
    }

    public ArrayList<AState> getSolutionPath(){
        return solutionPath;
    }
}
