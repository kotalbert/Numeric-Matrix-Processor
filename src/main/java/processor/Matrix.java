package processor;

public abstract class Matrix {
    public final int n;
    public final int m;
    public final int length;

    protected Matrix(int n, int m) {
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
     *
     * @return
     */
    public abstract int[][] getMatrix();

    /**
     * Get elements as 1d array
     *
     * @return
     */
    public abstract int[] getElements();

    /**
     * Get n-th element of matrix, row first indexed.
     *
     * @param index
     * @return
     */
    public final int getElement(int index) {
        return getElement(getRowIndex(index), this.getColumnIndex(index));
    }

    /**
     * Set n-th element of matrix with value, row first indexed.
     *
     * @param index
     * @param value
     */
    public final void setElement(int index, int value) {
        setElement(getRowIndex(index), this.getColumnIndex(index), value);
    }

    /**
     * Set ij-th element of Matrix with value.
     *
     * @param i
     * @param j
     * @param value
     */
    abstract void setElement(int i, int j, int value);


    /**
     * Get row i-index, based on element index.
     *
     * @param index
     * @return matrix row index
     */
    private int getRowIndex(int index) {
        return index / m;
    }

    /**
     * Get column j-index based on element index.
     *
     * @param index
     * @return matrix column j-index
     */
    private int getColumnIndex(int index) {
        return index % m;
    }

    /**
     * Get element by row and column index.
     *
     * @param i
     * @param j
     * @return
     */
    public abstract int getElement(int i, int j);

    @Override
    public final boolean equals(Object obj) {
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

    private void validateAddition(Matrix other) {
        if (this.length != other.length ||
                this.n != other.n ||
                this.m != other.m) {
            throw new IllegalArgumentException("Matrices of different dimensions cannot be added.");
        }
    }

    public final Matrix add(Matrix other) throws IllegalArgumentException {
        validateAddition(other);
        int[] elementSums = new int[this.length];
        for (int i = 0; i < this.length; i++) {
            elementSums[i] = this.getElement(i) + other.getElement(i);
        }
//        return new BasicMatrix(this.n, this.m, resultElements);
        return MatrixFactory.create(n, m, elementSums);

    }

    /**
     * Multiplication by scalar.
     *
     * @param scalar
     * @return
     */
    public Matrix multiply(int scalar) {
        int[] elements = getElements();
        for (int i = 0; i < length; i++) {
            elements[i] *= scalar;
        }
        return MatrixFactory.create(n, m, elements);
    }


    /**
     * Get dot product of this (left) and the other matrix.
     *
     * @param other
     * @return dot product
     * @throws IllegalArgumentException if dimensions mismatch
     */
    public Matrix dotProduct(Matrix other) {
        validateDotProductDimensions(other);
        final int n = this.n;
        final int k = other.m;

        int[] elements = new int[n * k];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                Vector row = this.getRow(i);
                Vector column = other.getColumn(j);
                elements[other.getIndex(i, j)] = row.dotProduct(column);
            }
        }

        return MatrixFactory.create(n, k, elements);

    }

    /**
     * Get sequential index from this this matrix row and column i, j indices.
     *
     * @param i
     * @param j
     * @return
     */
    protected int getIndex(int i, int j) {

        return i * this.m + j;
    }


    private void validateDotProductDimensions(Matrix other) {
        if (this.m != other.n)
            throw new IllegalArgumentException(
                    String.format("Dimensions mismatch for dot product: %d != %d", this.m, other.n)
            );
    }

    private Vector getRow(int i) {
        int[] row = new int[m];
        for (int j = 0; j < m; j++) {
            row[j] = getElement(i, j);
        }
        return new BasicVector(row);
    }

    private Vector getColumn(int j) {
        int[] column = new int[n];
        for (int i = 0; i < n; i++) {
            column[i] = getElement(i, j);
        }
        return new BasicVector(column);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0)
                    sb.append(getElement(i, j));
                else
                    sb.append(" ").append(getElement(i, j));

                if (j == m - 1)
                    sb.append("\n");
            }
        }
        return sb.toString();

    }
}