package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {
//    Queue<AState> que = new LinkedList<AState>();
    Set<AState> que1;
    Set<AState> visited;
    int nodesVisit=0;
    public int getNumberOfNodesEvaluated(){
        return  nodesVisit;
    }

    public String getName(){ return "BFS";}

    @Override
    public Solution solve(ISearchable s) {
        nodesVisit=0;
        que1 = new LinkedHashSet<>();
        visited = new HashSet<>();
        AState goal = s.getGoalState();
        AState current = s.getStartState();
        current.setVisited(true);
        que1.add(current);
        visited.add(current);
        while (!que1.isEmpty()) {
            nodesVisit++;
            current= que1.iterator().next();
            que1.remove(current);
            ArrayList<AState> successors = s.getAllSuccessors(current);
            for (AState successor : successors) {
                if(!visited.contains(successor)){
                    successor.setVisited(true);
//                    successor.setCameFrom(current); //happens in get all successors
                    if(successor.equals(goal)){
                        return new Solution(successor);
                    }
                    que1.add(successor);
                    visited.add(successor);

                }
            }
        }
        return new Solution(null);
    }
}
