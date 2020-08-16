package processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import processor.util.InputStreamParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;


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

    @Test
    @DisplayName("Determinant 3x3 matrix should be calculated correctly.")
    public void ThreeByThreeDeterminant() {
        Matrix mx = factory.create(3, 3, new double[]
                {-3, 9, 5, -4, 0, 1, 6, 3, 0});
        double expected = 3;
        double actual = mx.getDeterminant();
        assertEquals(expected, actual);

        mx = factory.create(3, 3, new double[]
                {2, -5, 3, 0, 7, -2, -1, 4, 1});
        expected = 41;
        actual = mx.getDeterminant();
        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("Determinant of higher dimensions should be calculated corectly")
    public void determinantFromFiles() throws FileNotFoundException {

        File fileFromResources = TestUtils.getFileFromResources("determinant/determinants.txt");
        List<Matrix> mxs = InputStreamParser.parse(new FileInputStream(fileFromResources));
        Matrix mx0 = mxs.get(0);
        assertEquals(191L, mx0.getDeterminant());

        Matrix mx1 = mxs.get(1);
        double det = mx1.getDeterminant();
        double scale = Math.pow(10, 4);
        assertEquals(45.2197, Math.round(det * scale) / scale);




    }
}
