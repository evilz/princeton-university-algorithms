import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Vincent on 2/8/2016.
 */
public class Subset {

    public static void main(String[] args) {

        // args = new String[1];
        // args[0] = "3";

        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();


        for (String s : StdIn.readAllStrings()) {
            randomizedQueue.enqueue(s);
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
}
