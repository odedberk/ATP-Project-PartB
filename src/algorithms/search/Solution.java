package algorithms.search;

import java.util.ArrayList;

public class Solution {
    ArrayList<AState> solutionPath;

    public Solution(AState goal) {
        if (goal!=null)
            solutionPath= new ArrayList<>();
        while(goal!=null){
            solutionPath.add(goal);
            goal=goal.getCameFrom();
        }
    }

    public ArrayList<AState> getSolutionPath(){
        return solutionPath;
    }
}
