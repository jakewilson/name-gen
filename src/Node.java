import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by jakewilson on 12/17/16.
 */
public class Node {

    private char state;
    private int numLettersSeen;
    private Hashtable<Character, Edge> edges;

    public char getState() {
        return state;
    }

    public int getNumLettersSeen() {
        return numLettersSeen;
    }

    public void incLettersSeen() {
        numLettersSeen++;
    }

    public Hashtable<Character, Edge> getEdges() {
        return edges;
    }

    public Node(char state) {
        this.state = state;
        this.numLettersSeen = 0;
        this.edges = new Hashtable<Character, Edge>(); // initial capacity is 11
    }

    @Override
    public String toString() {
        String ret = "";
        for (Enumeration<Edge> e = edges.elements(); e.hasMoreElements(); )
            ret += e.nextElement().toString() + " | ";

        return ret;
    }

}
