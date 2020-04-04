package algorithms.search;

import java.util.HashSet;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    //private HashSet<AState> Blacks;
    //private HashSet<AState>Grays;
    private HashSet<AState> visited;
    private int nodesVisit;

    @Override
    public Solution solve(ISearchable s) {
        //Blacks = new HashSet<AState>();
       // Grays = new HashSet<AState>() ;
        visited= new HashSet<AState>();
        nodesVisit=0;
        Stack<AState> rStack = new Stack<AState>();
        rStack.push(s.getStartState());
        while (!rStack.empty()){
            AState state = rStack.pop();
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

//    private Solution visit(ISearchable s,AState state){
//        nodesVisit++;
//        System.out.println(nodesVisit);
//        if(state.equals(s.getGoalState()))
//            return new Solution(state);
//        visited.add(state);
//        Solution sol=null;
//        for(AState son : s.getAllSuccessors(state)) {
//            if (!visited.contains(son)) {
//                sol = visit(s, son);
//            }
//            if(sol!=null)
//                break;
//        }
//        return sol;
//    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return nodesVisit;
    }
}
