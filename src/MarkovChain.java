import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

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
     * @param name the name to add (consisting of only lowercase letters)
     */
    public void train(String name) {
        String n = "^" + name.toLowerCase() + "$";
        for (int i = 0; i < n.length() - 1; i++) { // n.length() - 1 because we ignore the $
            char current = n.charAt(i), next = n.charAt(i + 1);

            // add to the chain if not already in it
            if (chain.get(next) == null)
                chain.put(next, new Node(next));

            // add an edge to the current node if it doesn't already have
            // an edge with that character
            if (chain.get(current).getEdges().get(next) == null)
                chain.get(current).getEdges().put(next, new Edge(chain.get(next)));

            chain.get(current).incLettersSeen();
            chain.get(current).getEdges().get(next).incTimesSeen();

            chain.get(current).updateProbabilities();
        }
    }

    /**
     * Generates a name from the chain
     *
     * @return the generated name
     */
    public String generate() {
        Random r = new Random();
        Node n = chain.get('^'); // start at the start state
        String name = ""; // the name to generate

        while (n.getState() != '$') {
            int num = r.nextInt(100) + 1; // generate random number between 1-100

            // pick the edge whose probability matches
            Edge edge = null;
            System.out.println("num: " + num);
            for (Enumeration<Edge> e = n.getEdges().elements(); e.hasMoreElements() && num > 0; )
                num -= (edge = e.nextElement()).getProb() * 100;

            System.out.println("edge: " + edge);
            n = edge.getNode();
            name += n.getState(); // add the character to the name
        }

        return name.substring(0, name.length() - 1);
    }

    public void print() {
        System.out.println(this.toString());
    }

    public String toString() {
        String ret = "";
        for (Enumeration<Node> e = chain.elements(); e.hasMoreElements(); ) {
            Node n = e.nextElement();
            ret += n.getState() + " -> | " + n.toString() + "\n";
        }

        return ret;
    }

    public static void main(String[] args) {
        MarkovChain mc = new MarkovChain();
        mc.train("jake");
        mc.train("matt");
        mc.train("kelly");
        mc.print();

        System.out.println("\n-----------------\n" + mc.generate());
        System.out.println("\n-----------------\n" + mc.generate());
        System.out.println("\n-----------------\n" + mc.generate());
        System.out.println("\n-----------------\n" + mc.generate());
    }

}
