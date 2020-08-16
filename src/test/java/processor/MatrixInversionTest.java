package processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatrixInversionTest {

   MatrixFactory factory = new BasicMatrixFactory();

   @Test
    public void simpleInversion() {
      Matrix mx = factory.create(3, 3, new double[] {2, -1, 0, 0, 1, 2, 1, 1, 0});
      System.out.println(mx.getInverse());
   }

}
