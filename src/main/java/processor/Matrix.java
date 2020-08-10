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
        return getElement(getRowIndex(index), getColumn(index));
    }

    /**
     * Set n-th element of matrix with value, row first indexed.
     *
     * @param index
     * @param value
     */
    public final void setElement(int index, int value) {
        setElement(getRowIndex(index), getColumn(index), value);
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
    private int getColumn(int index) {
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
        return this.addOther(other);
    }


    protected abstract Matrix addOther(Matrix other);
    protected abstract Matrix multiply(int scalar);


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