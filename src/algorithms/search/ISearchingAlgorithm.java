package algorithms.search;

    /**
     * An interface for implementing algorithms that solve ISearchable problems
    */
public interface ISearchingAlgorithm {
    /**
     * The method for implementing the actual searching algorithm
     * @param s an ISearchable instance which we can preform our search over
     * @return a Solution including all steps in a path from start to goal
     * (if such exists, empty solution otherwise)
     */
    Solution solve(ISearchable s);
    int getNumberOfNodesEvaluated();
    String getName();
}
