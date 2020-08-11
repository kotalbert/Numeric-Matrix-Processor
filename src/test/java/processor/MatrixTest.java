package processor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    public void equals() {
        final int n = 3;
        final float[] elements = new float[n * n];

        for (int i = 0; i < n * n; i++) {
            elements[i] = i;
        }

        // testing empty matrices
        BasicMatrix mx1 = new BasicMatrix(n, n);
        BasicMatrix mx2 = new BasicMatrix(n, n);
        assertEquals(mx1, mx2);

        BasicMatrix mx3 = new BasicMatrix(n, n + 1);
        assertNotEquals(mx1, mx3);

        // testing matrices with element sequence
        BasicMatrix mx11 = new BasicMatrix(n, n, elements);
        BasicMatrix mx12 = new BasicMatrix(n, n, elements);

        assertEquals(mx11, mx12);

    }

    @Test
    public void emptyMatrixConstructor() {
        final int n = 5;
        final int m = 3;

        BasicMatrix mx = new BasicMatrix(n, m);
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
        BasicMatrix mx = new BasicMatrix(3, 3);
        for (float element : mx.getElements()) {
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
        assertThrows(IllegalArgumentException.class, () -> new BasicMatrix(n, m));
    }

    @Test
    public void setElement() {
        BasicMatrix mx = new BasicMatrix(4, 5);
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

        float[] arrOk = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        BasicMatrix mx = new BasicMatrix(n, m, arrOk);
        float[][] rawMatrix = mx.getMatrix();
        int k = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                assertEquals(rawMatrix[i][j], arrOk[k++]);
            }
        }

        assertThrows(IllegalArgumentException.class, () -> new BasicMatrix(n, m, new float[n * m - 1]));


    }

}
