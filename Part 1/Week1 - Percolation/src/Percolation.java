import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private  WeightedQuickUnionUF uf;
    private int virtualTop;
    private int size;
    private boolean[] nodeState;

    public Percolation(int N)    {

        if (N <= 0)  { throw new java.lang.IllegalArgumentException(); }

        size = N;
        uf = new  WeightedQuickUnionUF(N * N + 1);

        nodeState = new boolean[N * N];

        virtualTop = N * N;
    }

    private void checkIndex(int i, int j) {
        if (i <= 0 || i > size || j <= 0 || j > size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    public void open(int i, int j)       {
        checkIndex(i, j);

        int y = i - 1;
        int x = j - 1;
        int current = y * size + x;
        nodeState[current] = true;

        // top
        if (y > 0 &&  isOpen(i-1, j)) { uf.union(current, current - size); }
        // bottom
        if (i < size && isOpen(i+1, j)) { uf.union(current, current + size); }
        // left
        if (x > 0 && isOpen(i, j-1)) { uf.union(current, current - 1); }
        // right
        if (j < size && isOpen(i, j+1)) { uf.union(current, current + 1); }
        // virtualTop
        if (i == 1) { uf.union(current, virtualTop); }
    }

    public boolean isOpen(int i, int j)  {
        checkIndex(i, j);

        --i;
        --j;
        if (i < 0 || i >= size || j < 0 || j >= size) return false;
        int current = i * size + j;

        return nodeState[current];
    }

    public boolean isFull(int i, int j) {
        checkIndex(i, j);
        if (!isOpen(i, j)) { return false; }

        --i;
        --j;
        int current = i * size + j;

        return uf.connected(current, virtualTop);
    }

    public boolean percolates()      {

        for (int x = 1; x <= size; x++) {
            if (isOpen(size, size - x+1) && uf.connected(virtualTop, virtualTop - x))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
