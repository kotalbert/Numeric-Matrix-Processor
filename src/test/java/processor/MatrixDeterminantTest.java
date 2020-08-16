package processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixDeterminantTest {

    MatrixFactory factory = new BasicMatrixFactory();

    @Test
    @DisplayName("Removing row and column should work correctly.")
    public void removeRowColumn() {
        Matrix base = new BasicMatrix(3, 3, new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9});

        Matrix expected00 = factory.create(2, 2, new double[]{5, 6, 8, 9});
        Matrix actual00 = base.removeRowColumn(0, 0);
        assertEquals(expected00, actual00);


        Matrix expected01 = factory.create(2, 2, new double[]{2, 3, 8, 9});
        Matrix actual01 = base.removeRowColumn(1, 0);

        assertEquals(expected01, actual01);

    }
    @Test
    @DisplayName("Determinant of 2x2 matrix should be calculated correctly.")
    public void TwoByTwoDeterminant() {
        Matrix mx = factory.create(2, 2, new double[] {3, 8, 4, 6});
        double det = -14d;
        assertEquals(det, mx.getDeterminant());

        mx = factory.create(2, 2, new double[] {4, 7, 2, 9});
        det = 22;
        assertEquals(det, mx.getDeterminant());

    }

}
