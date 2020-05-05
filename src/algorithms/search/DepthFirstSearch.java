package algorithms.search;

import java.util.HashSet;
import java.util.Queue;
import java.util.Stack;

    /**
     * A searching algorithm implementing the ISearchingAlgorithm interface.
     * used to find a given position/"state" of an ISearchable object
     * using the "solve" method (the goal state)
     */

public class DepthFirstSearch extends ASearchingAlgorithm {
    private HashSet<AState> visited;
    Stack<AState> rStack ;
    private int nodesVisit;

    public DepthFirstSearch() {
        name="DFS";
        rStack = new Stack<>();
        visited= new HashSet<>();
    }

    @Override
    public Solution solve(ISearchable s) {
        if (s==null)
            return null;
        if(s.getStartState().equals(s.getGoalState()))
            return new Solution(s.getStartState());

        //Clear data in case this instance solves more than one ISearchable object
        visited.clear();
        rStack.clear();
        nodesVisit=0;

        rStack.push(s.getStartState()); // initialize stack
        while (!rStack.empty()){
            AState state = rStack.pop();
            if(visited.contains(state))
                continue;
            nodesVisit++;
            for(AState son : s.getAllPossibleStates(state)) {
                if(son.equals(s.getGoalState()))
                    return new Solution(son); //goal state found
                if (!visited.contains(son)) //add current states neighbor to the stack
                    rStack.push(son);
            }
            visited.add(state);
        }
        return new Solution(null);
      }


    @Override
    public int getNumberOfNodesEvaluated() {
        return nodesVisit;
    }

}
