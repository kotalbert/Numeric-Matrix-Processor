package processor;

public abstract class Matrix {
    public final int n;
    public final int m;
    public final int length;

    private final MatrixFactory matrixFactory;

    protected Matrix(int n, int m) {
        validateDimensions(n, m);
        this.n = n;
        this.m = m;
        this.length = n * m;
        matrixFactory = new BasicMatrixFactory();
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
    public abstract double[][] getMatrix();

    /**
     * Get elements as 1d array
     *
     * @return
     */
    public abstract double[] getElements();

    /**
     * Get n-th element of matrix, row first indexed.
     *
     * @param index
     * @return
     */
    public final double getElement(int index) {
        return getElement(getRowIndex(index), this.getColumnIndex(index));
    }

    /**
     * Set n-th element of matrix with value, row first indexed.
     *
     * @param index
     * @param value
     */
    public final void setElement(int index, double value) {
        setElement(getRowIndex(index), this.getColumnIndex(index), value);
    }

    /**
     * Set ij-th element of Matrix with value.
     *
     * @param i
     * @param j
     * @param value
     */
    abstract void setElement(int i, int j, double value);


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
    public abstract double getElement(int i, int j);

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
        double[] elementSums = new double[this.length];
        for (int i = 0; i < this.length; i++) {
            elementSums[i] = this.getElement(i) + other.getElement(i);
        }
//        return new BasicMatrix(this.n, this.m, resultElements);
        return matrixFactory.create(n, m, elementSums);

    }

    /**
     * Multiplication by scalar.
     *
     * @param scalar
     * @return
     */
    public Matrix multiply(double scalar) {
        double[] elements = getElements();
        for (int i = 0; i < length; i++) {
            elements[i] *= scalar;
        }
        return matrixFactory.create(n, m, elements);
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

        double[] elements = new double[n * k];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                Vector row = this.getRow(i);
                Vector column = other.getColumn(j);
                elements[other.getIndex(i, j)] = row.dotProduct(column);
            }
        }

        return matrixFactory.create(n, k, elements);

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
        double[] row = new double[m];
        for (int j = 0; j < m; j++) {
            row[j] = getElement(i, j);
        }
        return new BasicVector(row);
    }

    private Vector getColumn(int j) {
        double[] column = new double[n];
        for (int i = 0; i < n; i++) {
            column[i] = getElement(i, j);
        }
        return new BasicVector(column);
    }

    public Matrix transpose() {
        Matrix t = matrixFactory.create(this.m, this.n);
        for (int i = 0; i < t.n; i++) {
            for (int j = 0; j < t.m; j++) {
                t.setElement(j, i, this.getElement(i, j));
            }
        }
        return t;
    }

    public Matrix reflectSideDiagonal() {
        // https://stackoverflow.com/a/5916919
        Matrix t = matrixFactory.create(this.n, this.m);
        for (int i = 0; i < t.n; i++) {
            for (int j = 0; j < t.m; j++) {
                t.setElement(i, j, this.getElement(t.n - 1 - j, t.m - 1 - i));
                t.setElement(t.n - 1 - j, t.m - 1 - i, this.getElement(i, j));
            }
        }
        return t;
    }

    public Matrix reflectVertical() {

        Matrix t = matrixFactory.create(this.n, this.m);

        for (int i = 0; i < t.n; i++) {
            for (int j = 0; j < t.m; j++) {
                t.setElement(i, j, this.getElement(i, this.m - 1 - j));
            }
        }
        return t;
    }

    public Matrix reflectHorizontal() {
        Matrix t = matrixFactory.create(this.n, this.m);

        for (int i = 0; i < t.n; i++) {
            for (int j = 0; j < t.m; j++) {
                t.setElement(i, j, this.getElement(this.n - 1 - i,  j));
            }
        }
        return t;
    }

    /**
     * Return new Matrix, after removing i-th row and j-th column.
     *
     * @param i
     * @param j
     * @return
     */
    public Matrix removeRowColumn(int i, int j) {
        checkIfRemovedInMatrix(i, j);
        double[] elements = new double[length - n - m + 1];
        int c = 0;
        for (int k = 0; k < n; k++) {
            for (int l = 0; l < m; l++) {
                if (k != i && l != j)
                    elements[c++] = getElement(k, l);
            }
        }
       return matrixFactory.create(n -1, m - 1, elements);

    }

    private void checkIfRemovedInMatrix(int i, int j) {
        if (i > this.n || j > this.m)
            throw new IllegalArgumentException("Cannot remove row or column outside of matrix.");
    }

    private void checkIsSquare() {
        if (n != m)
            throw new IllegalStateException("Operation possible only on square matrix.");
    }


    /**
     * Get determinant of Matrix.
     */
    public double getDeterminant() {
        checkIsSquare();
        if (n == 2) {
            return getElement(0, 0) * getElement(1, 1)
                    - getElement(1, 0) * getElement(0, 1);
        }
        else {
            // assuming, first row is removed
            double[] minors = new double[n];
            for (int i = 0; i < n; i++) {
               minors[i] = getElement(0,i) * removeRowColumn(0, i).getDeterminant();
            }
            double determinant = 0;
            for (int i = 0; i < n; i++) {
                determinant += Math.pow(-1, i) * minors[i];
            }
            return determinant;
        }

    }

    public Matrix getInverse() {
        checkIsSquare();
        Matrix ct = getCofactorMatrix().transpose();
        return ct.multiply(1/this.getDeterminant());
    }

    private double getCofactor(int i, int j) {
        return Math.pow(-1, i + j) * removeRowColumn(i, j).getDeterminant();
    }

    private Matrix getCofactorMatrix() {
        double[] elements = new double[n * n];
        int c = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                elements[c++] = getCofactor(i, j);
            }
        }
        return matrixFactory.create(n, n, elements);
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                double element = getElement(i, j);
                String elementString = String.format("%.2f", element);
                if (j == 0)
                    sb.append(elementString);
                else
                    sb.append("\t").append(elementString);

                if (j == m - 1)
                    sb.append("\n");
            }
        }
        return sb.toString();

    }
}