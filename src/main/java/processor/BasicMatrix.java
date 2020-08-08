package processor;


/**
 * Basic matrix implementation.
 */
public class BasicMatrix extends Matrix {
    private final int[][] matrix;

    /**
     * New empty matrix.
     *
     * @param n row number
     * @param m column number
     */
    public BasicMatrix(int n, int m) {
        super(n, m);
        this.matrix = new int[n][m];
    }

    /**
     * New matrix from elements array.
     *
     * @param n        row number
     * @param m        column number
     * @param elements array of elements to put in the matrix
     * @throws IllegalArgumentException when elements length lt matrix size
     */
    public BasicMatrix(int n, int m, int[] elements) {
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
    public int[][] getMatrix() {
        return matrix.clone();
    }

    /**
     * Get matrix elements.
     *
     * @return row first 1d array of matrix element
     */
    @Override
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


    @Override
    void setElement(int i, int j, int value) {
        matrix[i][j] = value;
    }

    @Override
    public int getElement(int i, int j) {
        return matrix[i][j];
    }

    @Override
    protected Matrix addOther(Matrix other) {
        int[] resultElements = new int[this.length];
        for (int i = 0; i < this.length; i++) {
           resultElements[i] = this.getElement(i) + other.getElement(i);
        }
        return new BasicMatrix(this.n, this.m, resultElements);
    }


}

