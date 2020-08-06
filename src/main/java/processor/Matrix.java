package processor;


public class Matrix {
    public final int n;
    public final int m;
    public final int length;
    private final int[][] matrix;

    /**
     * New empty matrix.
     *
     * @param n row number
     * @param m column number
     */
    public Matrix(int n, int m) {
        validateDimensions(n, m);
        this.n = n;
        this.m = m;
        length = n * m;
        this.matrix = new int[n][m];
    }

    /**
     * New matrix from elements array.
     *
     * @param n        row number
     * @param m        column number
     * @param elements array of elements to put in the matrix
     */
    public Matrix(int n, int m, int[] elements) {
        this(n, m);
        if (elements.length != n * m)
            throw new IllegalArgumentException(String.format("Elements count not matching dimensions: %d x %d != %d", n, m, elements.length));
        for (int i = 0; i < elements.length; i++) {
            setElement(i, elements[i]);

        }

    }

    /**
     * Matrix getter.
     *
     * @return copy of matrix
     */
    public int[][] getMatrix() {
        return matrix.clone();
    }


    /**
     * Get matrix elements.
     *
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
     * Put scalar value into matrix.
     *
     * @param index 0 based index of matrix element
     * @param value value to put into matrix
     */
    public void setElement(int index, int value) {
        int j = getColumn(index);
        int i = getRow(index);
        matrix[i][j] = value;
    }

    /**
     * Get matrix element based on index.
     *
     * @param index
     * @return element value.
     */
    public int getElement(int index) {
        int i = getRow(index);
        int j = getColumn(index);
        return matrix[i][j];
    }

    /**
     * Get row number based on index.
     *
     * @param index
     * @return matrix row number
     */
    private int getRow(int index) {
        return index / m;
    }

    /**
     * Get column number based on index.
     *
     * @param index
     * @return matrix column number
     */
    private int getColumn(int index) {
        return index % m;
    }

    /**
     * Check if legal matrix dimensions.
     */
    private static void validateDimensions(int n, int m) {
        if ((n < 0 || m < 0)
                || (n == 0 && m == 0))
            throw new IllegalArgumentException(String.format("Illegal dimensions %d x %d", n, m));

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!getClass().isAssignableFrom(obj.getClass())) return false;
        final Matrix other = (Matrix) obj;

        if (this.n != other.n || this.m != other.m) return false;
        for (int i = 0; i < n * m; i++) {
           if (this.getElements()[i] != other.getElements()[i])
               return false;
        }
        return true;

        }

    }

