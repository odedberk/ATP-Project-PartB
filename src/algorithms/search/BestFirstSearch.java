package algorithms.search;

import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch {
    public BestFirstSearch() {
        this.que= new PriorityQueue<>();
    }

    @Override
    public String getName(){ return "Best First Search";}

}