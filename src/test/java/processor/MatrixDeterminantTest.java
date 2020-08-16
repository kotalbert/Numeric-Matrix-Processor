package processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

}
