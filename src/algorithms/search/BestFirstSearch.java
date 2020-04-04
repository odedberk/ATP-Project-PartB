package algorithms.search;

import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch {

    public BestFirstSearch() {
        name="Best First Search";
        this.que= new PriorityQueue<>();
    }
}