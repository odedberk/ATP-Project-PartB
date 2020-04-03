package algorithms.search;

import java.util.Objects;

public abstract class AState {
    private String state;
    private double cost;
    private AState ameFrom;

    public AState(String state, double cost, AState ameFrom) {
        this.state = state;
        this.cost = cost;
        this.ameFrom = ameFrom;
    }

    public String getState() {
        return state;
    }

    public double getCost() {
        return cost;
    }

    public AState getAmeFrom() {
        return ameFrom;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setAmeFrom(AState ameFrom) {
        this.ameFrom = ameFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState aState = (AState) o;
        return Double.compare(aState.cost, cost) == 0 &&
                Objects.equals(state, aState.state) &&
                Objects.equals(ameFrom, aState.ameFrom);
    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(state, cost, ameFrom);
//    }
}
