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
     * Get matrix elements.
     * @return row first 1d array of matrix element
     */
    public int[] getElements() {
        int[] elements = new int[n * m];
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
               elements[k++] = matrix[i][j];
            }
        }
        return elements;
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
