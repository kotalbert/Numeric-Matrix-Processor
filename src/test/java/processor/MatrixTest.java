package processor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    public void equals() {
        final int n = 3;
        final int[] elements = new int[n * n];

        for (int i = 0; i < n * n; i++) {
            elements[i] = i;
        }

        // testing empty matrices
        Matrix mx1 = new Matrix(n, n);
        Matrix mx2 = new Matrix(n, n);
        assertEquals(mx1, mx2);

        Matrix mx3 = new Matrix(n, n + 1);
        assertNotEquals(mx1, mx3);

        // testing matrices with element sequence
        Matrix mx11 = new Matrix(n, n, elements);
        Matrix mx12 = new Matrix(n, n, elements);

        assertEquals(mx11, mx12);

    }

    @Test
    public void emptyMatrixConstructor() {
        final int n = 5;
        final int m = 3;

        Matrix mx = new Matrix(n, m);
        assertEquals(mx.n, n);
        assertEquals(mx.m, m);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                assertEquals(0, mx.getMatrix()[i][j]);
            }
        }
    }

    @Test
    public void getElementsOfZeroMatrix() {
        Matrix mx = new Matrix(3, 3);
        for (int element : mx.getElements()) {
            assertEquals(0, element);
        }
    }

    @Test
    public void illegalDimensionValidation() {
        assertIllegalDimensions(-1, 1);
        assertIllegalDimensions(1, -1);
        assertIllegalDimensions(-1, -1);
        assertIllegalDimensions(0, 0);
    }

    /**
     * Will not fail only if illegal dimensions.
     */
    private void assertIllegalDimensions(int n, int m) {
        assertThrows(IllegalArgumentException.class, () -> new Matrix(n, m));
    }

    @Test
    public void setElement() {
        Matrix mx = new Matrix(4, 5);
        for (int i = 0; i < mx.length; i++) {
            mx.setElement(i, i);
        }
        for (int i = 0; i < mx.length; i++) {
            assertEquals(mx.getElement(i), i);
        }

    }

    @Test
    public void matrixFromArrayConstructor() {
        final int n = 3;
        final int m = 3;

        int[] arrOk = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        Matrix mx = new Matrix(n, m, arrOk);
        int[][] rawMatrix = mx.getMatrix();
        int k = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                assertEquals(rawMatrix[i][j], arrOk[k++]);
            }
        }

        assertThrows(IllegalArgumentException.class, () -> new Matrix(n, m, new int[n * m - 1]));


    }

}
