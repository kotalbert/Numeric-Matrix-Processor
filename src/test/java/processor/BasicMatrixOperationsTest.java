package processor;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class BasicMatrixOperationsTest {

    @Test
    public void addition() throws IOException {
        File leftFile = TestUtils.getFileFromResources("addition/mx11_input.txt");
        File rightFile = TestUtils.getFileFromResources("addition/mx12_input.txt");
        File outputFile = TestUtils.getFileFromResources("addition/mx10_output.txt");

        assertTrue(leftFile.exists());
        assertTrue(rightFile.exists());
        assertTrue(outputFile.exists());

        Matrix mxLeft = MatrixFactory.create(leftFile);
        Matrix mxRight = MatrixFactory.create(rightFile);

        assertEquals(mxLeft.n, mxRight.n);
        assertEquals(mxLeft.m, mxRight.m);

        Matrix mxExpected = MatrixFactory.create(mxRight.n, mxRight.m, outputFile);

        assertEquals(mxRight.n, mxExpected.n);
        assertEquals(mxRight.m, mxExpected.m);

        Matrix mxActual = mxRight.add(mxLeft);
        assertEquals(mxExpected, mxActual);

    }


}
