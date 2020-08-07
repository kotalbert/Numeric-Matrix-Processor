package processor;


/**
 * Basic matrix implementation.
 */
public class Matrix extends AbstractMatrix {
    private final int[][] matrix;

    /**
     * New empty matrix.
     *
     * @param n row number
     * @param m column number
     */
    public Matrix(int n, int m) {
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
    public Matrix(int n, int m, int[] elements) {
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
    public AbstractMatrix add(AbstractMatrix left, AbstractMatrix right) throws ArithmeticException {
        return null;
    }


}

