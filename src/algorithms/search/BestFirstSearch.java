package algorithms.search;

import java.util.*;

    /**
     * BestFirstSearch algorithm.
     * has the same functionality of the BreadthFirstSearch
     * while using a different data structure
     */
public class BestFirstSearch extends BreadthFirstSearch {

    public BestFirstSearch() {
        name="Best First Search";
        this.que= new PriorityQueue<>();
    }
}