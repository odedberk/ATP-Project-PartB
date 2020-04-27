package algorithms.search;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.BitSet;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    String name;
    public String getName(){return name;}
    public abstract int getNumberOfNodesEvaluated();




}
