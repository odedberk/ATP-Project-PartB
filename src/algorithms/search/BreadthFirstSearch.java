package algorithms.search;

import java.util.*;

    /**
     * A searching algorithm implementing the ISearchingAlgorithm interface.
     * used to find a given position/"state" of an ISearchable object
     * using the "solve" method (the goal state)
     */

public class BreadthFirstSearch extends ASearchingAlgorithm {

    Collection<AState> que;
    Set<AState> visited=new HashSet<>(); //a set for nodes that were already visited during the solving process
    int nodesVisit=0;

    public BreadthFirstSearch() {
        name="BFS";
        this.que = new LinkedHashSet<>();
    }

    @Override
    public int getNumberOfNodesEvaluated(){ //return the number of nodes visited
        return nodesVisit;
    }

    @Override
    public Solution solve(ISearchable s) {
        if (s==null)
            return null;
        //Clear data in case this instance solves more than one ISearchable object
        que.clear();
        visited.clear();
        nodesVisit=0;

        AState goal = s.getGoalState(); //get target node
        AState current = s.getStartState(); //get starting node
        if (current.equals(goal))
            return new Solution(current);

        que.add(current);
        visited.add(current); //add starting node
        while (!que.isEmpty()) {
            nodesVisit++;
            current= getFirstInQue();
            ArrayList<AState> successors = s.getAllPossibleStates(current); // get current state's "neighbors"
            for (AState successor : successors) { //iterate over neighbors
                if(!visited.contains(successor)){
                    if(successor.equals(goal)){ //target node reached
                        return new Solution(successor);
                    }
                    que.add(successor);
                    visited.add(successor); //add current neighbor to the que

                }
            }
        }
        return new Solution(null);
    }

    private AState getFirstInQue(){
        AState first = que.iterator().next();
        que.remove(first);
        return first;
    }
}
