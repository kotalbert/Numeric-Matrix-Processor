package processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import processor.util.InputStreamParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasicMatrixOperationsTest {

    @Test
    @DisplayName("Matrix addition operation should give correct result.")
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

    @Test
    @DisplayName("Matrix scalar multiplication should give correct result.")
    public void scalarMultiplication() throws FileNotFoundException {
        File inputFile = TestUtils.getFileFromResources("scalarmultiplication/input.txt");
        File outputFile = TestUtils.getFileFromResources("scalarmultiplication/output.txt");

        int[] scalars = {3, 0};

        List<Matrix> inputMatrices = InputStreamParser.parse(new FileInputStream(inputFile));
        List<Matrix> outputMatrices = InputStreamParser.parse(new FileInputStream(outputFile));

        for (int i = 0; i < inputMatrices.size(); i++) {
            Matrix actual = outputMatrices.get(i);
            Matrix expected = inputMatrices.get(i).multiply(scalars[i]);

            assertEquals(expected, actual);
        }


    }

}
