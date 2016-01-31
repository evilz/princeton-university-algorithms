import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by Vincent on 30/01/2016.
 */
public class PercolationStats {

    private int[] percolationValues;
    private int t;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
        { throw new java.lang.IllegalArgumentException(); }

        t = T;

        percolationValues = new int[T];
        for (int i = 0; i < T; i++) {

            Percolation p = new Percolation(N);

            while (!p.percolates()) {
                int toOpenX =  StdRandom.uniform(N+1);
                int toOpenY =  StdRandom.uniform(N+1);
                if (!p.isOpen(toOpenY, toOpenX)) {
                    p.open(toOpenY, toOpenX);
                    percolationValues[i]++;
                }
            }
        }
    }

    public double mean()
    {
        return StdStats.mean(percolationValues);
    }

    public double stddev() {
        return StdStats.stddev(percolationValues);
    }

    public double confidenceLo() {
        return mean() -(1.96 * stddev() / Math.sqrt(t));
    }

    public double confidenceHi() {
        return mean() +(1.96 * stddev() / Math.sqrt(t));
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(N, T);

        System.out.println("mean\t\t\t= " + stats.mean());
        System.out.println("stddev\t\t\t= " + stats.stddev());
        System.out.println("95% confidence interval\t= " + stats.confidenceLo() + ", " + stats.confidenceHi());
    }
}