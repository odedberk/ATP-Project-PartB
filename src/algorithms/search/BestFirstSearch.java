package algorithms.search;

import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch {
    Set<AState> visited = new HashSet<>();
    Queue<AState> que =  new PriorityQueue<>();

    @Override
    public String getName(){ return "Best First Search";}

    @Override
    public Solution solve(ISearchable s) {
        que.clear();
        visited.clear();
        nodesVisit=0;
        AState goal = s.getGoalState();
        AState current = s.getStartState();
        current.setVisited(true);
        que.add(current);
        visited.add(current);
        while (!que.isEmpty()) {
            nodesVisit++;
            current= getFirstInQue();
            ArrayList<AState> successors = s.getAllSuccessors(current);
            for (AState successor : successors) {
                if(!visited.contains(successor)){
                    successor.setVisited(true);
//                    successor.setCameFrom(current); //happens in get all successors
                    if(successor.equals(goal)){
                        return new Solution(successor);
                    }
                    que.add(successor);
                    visited.add(successor);

                }
            }
        }
        return new Solution(null);
    }

    private AState getFirstInQue(){
        return que.poll();
    }
}