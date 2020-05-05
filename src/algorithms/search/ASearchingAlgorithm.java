package algorithms.search;

    /**
     * abstract class for searching algorithms,
     * implementing the ISearchingAlgorithm interface
    */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    String name; //algorithm name
    public String getName(){return name;}
    public abstract int getNumberOfNodesEvaluated();




}
