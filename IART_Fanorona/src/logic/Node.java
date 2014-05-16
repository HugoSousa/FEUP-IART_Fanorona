package logic;

import java.util.Collection;

/**
 * Created by Francisco on 16/05/2014.
 */
import java.util.ArrayList;
import java.util.Arrays;

public interface Node {
    public boolean terminal();
    public int heuristicValue(int player);
    public ArrayList<Node> getChilds(int player);
    public Node clone();
    public Movement getPlay();
}