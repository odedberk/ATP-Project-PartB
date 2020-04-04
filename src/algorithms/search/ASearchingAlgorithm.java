package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    String name;
    public String getName(){return name;}
    public abstract int getNumberOfNodesEvaluated();
}
