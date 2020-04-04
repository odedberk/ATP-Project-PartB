package algorithms.search;

import java.util.Objects;

public abstract class AState {
    private String state;
    private double cost;
    private AState cameFrom;
    private boolean visited=false;
    private boolean finished=false;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public AState(String state, double cost, AState cameFrom) {
        this.state = state;
        this.cost = cost;
        this.cameFrom = cameFrom;
    }
    @Override
    public String toString() {
        return state;
    }

    public double getCost() {
        return cost;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState aState = (AState) o;
        return state!=null && state.equals(aState.state);
    }
    @Override
    public int hashCode() {
        return state.hashCode();
    }
}
