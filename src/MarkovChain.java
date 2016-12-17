import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by jakewilson on 12/17/16.
 */
public class MarkovChain {

    private Hashtable<Character, Node> chain;

    public MarkovChain() {
        chain = new Hashtable<Character, Node>(28);
        chain.put('^', new Node('^')); // the start state
        chain.put('$', new Node('$')); // the end state
    }

    /**
     * Trains the markov chain by adding a name to it, which will
     * adjust the state machine and all of its probabilities
     *
     * @param name the name to add
     */
    public void train(String name) {
        // TODO
    }

    public void print() {
        System.out.println(this.toString());
    }

    public String toString() {
        String ret = "";
        for (Enumeration<Node> e = chain.elements(); e.hasMoreElements(); ) {
            Node n = e.nextElement();
            ret += n.getState() + "[ " + n.toString() + "]\n";
        }

        return ret;
    }


    public static void main(String[] args) {
        MarkovChain mc = new MarkovChain();
        mc.print();
    }

}
