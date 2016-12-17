/**
 * Created by jakewilson on 12/17/16.
 */
public class Edge {

    private float prob; // transition probability
    private Node n; // the node this edge points to
    private int timesSeen; // number of times this node has been seen

    public Edge(float prob, Node n) {
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

    public float getProb() {
        return prob;
    }

    public void setProb(float prob) {
        this.prob = prob;
    }

    public int getTimesSeen() {
        return timesSeen;
    }

    public void incTimesSeen() {
        timesSeen++;
    }

    @Override
    public String toString() {
        return n.getState() + ": " + prob;
    }
}
