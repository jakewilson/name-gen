import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

/**
 * Created by jakewilson on 12/17/16.
 */
public class MarkovChain {

    private Hashtable<Character, Node> chain;
    private static final int PROBABILITY_MULTIPLIER = 1000000;

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

        while (true) {
            int num = r.nextInt(PROBABILITY_MULTIPLIER) + 1;

            // pick the edge whose probability matches
            Edge edge = null;
            for (Enumeration<Edge> e = n.getEdges().elements(); e.hasMoreElements() && num > 0; )
                num -= (edge = e.nextElement()).getProb() * PROBABILITY_MULTIPLIER;

            n = edge.getNode();

            if (n.getState() == '$')
                if (name.length() == 1) // can't have a one character name
                    n = chain.get(name.charAt(0)); // start again at the previous character
                else if (everyCharIsVowel(name)) // if the name is made up of vowels
                    n = chain.get(name.charAt(name.length() - 1)); // start again at the last character
                else
                    break;
            else
                name += n.getState(); // add the character to the name
        }

        // remove the ending '$'
        return name;
    }

    private boolean everyCharIsVowel(String name) {
        for (int i = 0; i < name.length(); i++)
            if (!isVowel(name.charAt(i)))
                return false;

        return true;
    }

    private boolean isVowel(char c) {
        switch (c) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
        }

        return false;
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

}
