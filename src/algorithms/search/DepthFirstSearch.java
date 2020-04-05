package algorithms.search;

import java.util.HashSet;
import java.util.Queue;
import java.util.Stack;

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
        visited.clear();
        rStack.clear();
        nodesVisit=0;
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

}
