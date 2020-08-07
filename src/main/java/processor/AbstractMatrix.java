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
        return getElement(getRow(index), getColumn(index));
    }

    /**
     * Set n-th element of matrix with value, row first indexed.
     *
     * @param index
     * @param value
     */
    public final void setElement(int index, int value) {
        setElement(getRow(index), getColumn(index), value);
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
        final AbstractMatrix other = (AbstractMatrix) obj;

        if (this.n != other.n || this.m != other.m) return false;
        for (int i = 0; i < n * m; i++) {
            if (this.getElements()[i] != other.getElements()[i])
                return false;
        }
        return true;
    }

    private void validateAddition(AbstractMatrix other) {
        if (this.length != other.length ||
                        this.n != other.n ||
                        this.m != other.m) {
            throw new IllegalArgumentException("Matrices of different dimensions cannot be added.");
        }
    }

   public final AbstractMatrix add(AbstractMatrix other) {
        validateAddition(other);
        return this.addOther(other);
   }

    protected abstract AbstractMatrix addOther(AbstractMatrix other);

/*
    protected AbstractMatrix add(AbstractMatrix other) {

       this.add(other);
    }
*/

//    public abstract AbstractMatrix add(AbstractMatrix other)  throws IllegalArgumentException;

}