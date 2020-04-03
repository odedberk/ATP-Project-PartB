package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    public int NodesVisited=0;
    public int getNumberOfNodesEvaluated(){
        return  NodesVisited;
    }
}
