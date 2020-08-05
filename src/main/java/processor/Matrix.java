package processor;



public class Matrix {
    public final int n;
    public final int m;
    private final int[][] matrix;

    /**
     * New empty matrix.
     * @param n row number
     * @param m column number
     */
    public Matrix(int n, int m) {
        validateDimensions(n, m);
        this.n = n;
        this.m = m;
        this.matrix = new int[n][m];
    }

    /**
     * Matrix getter.
     * @return copy of matrix
     */
    public int[][] getMatrix() {
        return matrix.clone();
    }

    /**
     * Check if legal matrix dimensions.
     */
    private static void validateDimensions(int n, int m) {
        if ((n < 0 || m < 0)
                || (n == 0 && m == 0))
            throw new IllegalArgumentException(String.format("Illegal dimensions %d x %d", n, m));

    }

}
