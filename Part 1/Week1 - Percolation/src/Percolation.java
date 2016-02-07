import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF ufFull;
    private WeightedQuickUnionUF ufPercolate;
    private int virtualTop;
    private int virtualBottom;
    private int size;
    private boolean[] nodeState;

    public Percolation(int N) {

        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        size = N;
        ufFull = new WeightedQuickUnionUF(N * N + 1);
        ufPercolate = new WeightedQuickUnionUF(N * N + 2);

        nodeState = new boolean[N * N];

        virtualTop = N * N;
        virtualBottom = N * N + 1;
    }

    private void checkIndex(int i, int j) {
        if (i <= 0 || i > size || j <= 0 || j > size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    private int getIndex(int i, int j) {
        return --i * size + --j;
    }

    private void union(int p, int q) {
        ufFull.union(p, q);
        ufPercolate.union(p, q);
    }

    public void open(int i, int j) {
        checkIndex(i, j);

        int y = i - 1;
        int x = j - 1;
        int current = getIndex(i, j);
        nodeState[current] = true;

        // top
        if (y > 0 && isOpen(i - 1, j)) {
            union(current, current - size);
        }
        // bottom
        if (i < size && isOpen(i + 1, j)) {
            union(current, current + size);
        }
        // left
        if (x > 0 && isOpen(i, j - 1)) {
            union(current, current - 1);
        }
        // right
        if (j < size && isOpen(i, j + 1)) {
            union(current, current + 1);
        }

        // virtualTop
        if (i == 1) {
            ufFull.union(current, virtualTop);
            ufPercolate.union(current, virtualTop);
        }

        // virtualBottom
        if (i == size) {
            ufPercolate.union(current, virtualBottom);
        }
    }

    public boolean isOpen(int i, int j) {
        checkIndex(i, j);
        return nodeState[getIndex(i, j)];
    }

    public boolean isFull(int i, int j) {
        checkIndex(i, j);
        return isOpen(i, j) && ufFull.connected(getIndex(i, j), virtualTop);
    }

    public boolean percolates() {
        return ufPercolate.connected(virtualTop, virtualBottom);
    }

    public static void main(String[] args) {

    }
}
