package algorithms.search;

import java.util.HashSet;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    private HashSet<AState> visited;
    private int nodesVisit;

    @Override
    public Solution solve(ISearchable s) {
        if(s.getStartState().equals(s.getGoalState()))
            return new Solution(s.getStartState());
        visited= new HashSet<AState>();
        nodesVisit=0;
        Stack<AState> rStack = new Stack<AState>();
        rStack.push(s.getStartState());
        while (!rStack.empty()){
            AState state = rStack.pop();
            if(visited.contains(state))
                continue;
            nodesVisit++;
            //System.out.println(nodesVisit);
            for(AState son : s.getAllSuccessors(state)) {
                if(son.equals(s.getGoalState()))
                    return new Solution(son);
                if (!visited.contains(son))
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


    @Override
    public String getName() {
        return "DFS";
    }
}
