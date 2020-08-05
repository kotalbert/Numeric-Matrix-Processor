package processor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MatrixTest {

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

}
