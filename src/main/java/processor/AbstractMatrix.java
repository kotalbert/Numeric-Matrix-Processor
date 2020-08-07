package processor;

public abstract class AbstractMatrix {
    public final int n;
    public final int m;
    public final int length;

    protected AbstractMatrix(int n, int m) {
        validateDimensions(n, m);
        this.n = n;
        this.m = m;
        this.length = n * m;
    }

    /**
     * Check if legal matrix dimensions.
     */
    private static void validateDimensions(int n, int m) {
        if ((n < 0 || m < 0)
                || (n == 0 && m == 0))
            throw new IllegalArgumentException(String.format("Illegal dimensions %d x %d", n, m));

    }

    /**
     * Get representation of matrix as 2d array.
     * @return
     */
    public abstract  int[][] getMatrix();

    /**
     * Get elements as 1d array
     * @return
     */
    public abstract int[] getElements();

    /**
     * Get element based on sequential index in matrix.
     * @param index
     * @return
     */
    public abstract int getElement(int index);

    /**
     * Get element by row and column index.
     *
     * @param i
     * @param j
     * @return
     */
    public abstract int getElement(int i, int j);

}