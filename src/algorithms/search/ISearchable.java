package algorithms.search;

import java.util.ArrayList;

    /**
     * An interface for objects representing a searchable problem
     */

public interface ISearchable {
    // return start state of the problem
    AState getStartState();

    // return goal state of the problem
    AState getGoalState();

        /**
         * find all "neighbors" of a given state in the problem
         * @param s The given state
         * @return An array list including all neighbors - not in a specific order
         */
    ArrayList<AState> getAllPossibleStates(AState s);
}
