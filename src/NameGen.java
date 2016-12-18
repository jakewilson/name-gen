import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;


/**
 * Created by jakewilson on 12/17/16.
 */
public class NameGen {

    private MarkovChain chain;

    public NameGen() {
        chain = new MarkovChain();
    }

    /**
     * Reads a comma delimited file of names
     * @param path
     */
    public void train(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            Gson gson = new Gson();
            String[] names = gson.fromJson(reader, String[].class);
            for (String s : names)
                chain.train(s);
        } catch (FileNotFoundException e) {
            System.out.println(path + " not found.");
            System.exit(1);
        }
    }

    public String generate() {
        return chain.generate();
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a training file.");
            return;
        }

        NameGen gen = new NameGen();
        gen.train(args[0]);
        System.out.println(gen.generate());
    }

}
