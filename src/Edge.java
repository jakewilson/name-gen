/**
 * Created by jakewilson on 12/17/16.
 */
public class Edge {

    private int prob; // transition probability
    private Node n; // the node this edge points to
    private int timesSeen; // number of times this node has been seen

    public Edge(int prob, Node n) {
        this.prob = prob;
        this.n = n;
        this.timesSeen = 0;
    }

    public Edge(Node n) {
        this(0, n);
    }

    public Node getNode() {
        return n;
    }

    public int getProb() {
        return prob;
    }

    public void setProb(int prob) {
        this.prob = prob;
    }

    @Override
    public String toString() {
        return n.getState() + ": " + prob;
    }
}
