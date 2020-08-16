package processor;


/**
 * Basic matrix implementation.
 */
public class BasicMatrix extends Matrix {
    private final double[][] matrix;

    /**
     * New empty matrix.
     *
     * @param n row number
     * @param m column number
     */
    public BasicMatrix(int n, int m) {
        super(n, m);
        this.matrix = new double[n][m];
    }

    /**
     * New matrix from elements array.
     *
     * @param n        row number
     * @param m        column number
     * @param elements array of elements to put in the matrix
     * @throws IllegalArgumentException when elements length lt matrix size
     */
    public BasicMatrix(int n, int m, double[] elements) {
        this(n, m);
        if (elements.length < n * m)
            throw new IllegalArgumentException(
                    String.format("Elements count not matching dimensions: %d x %d != %d", n, m, elements.length));
        for (int i = 0; i < elements.length; i++) {
            setElement(i, elements[i]);

        }

    }

    /**
     * Matrix getter.
     *
     * @return copy of matrix
     */
    @Override
    public double[][] getMatrix() {
        return matrix.clone();
    }

    /**
     * Get matrix elements.
     *
     * @return row first 1d array of matrix element
     */
    @Override
    public double[] getElements() {
        double[] elements = new double[n * m];
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                elements[k++] = matrix[i][j];
            }
        }
        return elements;
    }


    @Override
    void setElement(int i, int j, double value) {
        matrix[i][j] = value;
    }

    @Override
    public double getElement(int i, int j) {
        return matrix[i][j];
    }

}

