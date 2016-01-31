import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private  WeightedQuickUnionUF uf;
    private int virtualTop;
    private int virtualBottom;
    private int size;
    private boolean[] nodeState;

    public Percolation(int N)    {

        if (N <= 0)  { throw new java.lang.IllegalArgumentException(); }

        size = N;
        uf = new  WeightedQuickUnionUF(N * N + 2);

        nodeState = new boolean[N * N];

        virtualTop = N * N;
        virtualBottom = N * N + 1;
    }

    private void checkIndex(int i, int j) {
        if (i <= 0 || j <= 0) {
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
        if (y <= size && isOpen(i+1, j)) { uf.union(current, current + size); }
        // left
        if (x > 0 && isOpen(i, j-1)) { uf.union(current, current - 1); }
        // right
        if (x <= size && isOpen(i, j+1)) { uf.union(current, current + 1); }

        // virtualTop
        if (y == 0) {
            uf.union(current, virtualTop);
        }

        // virtualBottom
        if (y == size - 1) {
            uf.union(current, virtualBottom);
        }
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
        return uf.connected(virtualTop, virtualBottom);
    }

    public static void main(String[] args) {

    }
}
