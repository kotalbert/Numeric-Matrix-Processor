package processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import processor.util.InputStreamParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixOperationTest {

    private final MatrixFactory matrixFactory = new BasicMatrixFactory();

    @Test
    @DisplayName("Matrices addition  should give correct result and guard against dimensions mismatch.")
    public void addition() throws IOException {
        File leftFile = TestUtils.getFileFromResources("addition/mx11_input.txt");
        File rightFile = TestUtils.getFileFromResources("addition/mx12_input.txt");
        File outputFile = TestUtils.getFileFromResources("addition/mx10_output.txt");

        assertTrue(leftFile.exists());
        assertTrue(rightFile.exists());
        assertTrue(outputFile.exists());

        Matrix mxLeft = matrixFactory.create(leftFile);
        Matrix mxRight = matrixFactory.create(rightFile);

        assertEquals(mxLeft.n, mxRight.n);
        assertEquals(mxLeft.m, mxRight.m);

        Matrix mxExpected = matrixFactory.create(mxRight.n, mxRight.m, outputFile);

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

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("Matrices dot product should produce correct result and guard against dimensions mismatch")
    public void dotProduct(int testId) throws IOException {

        File testInputFile = TestUtils.getFileFromResources(testId, "dotproduct/test%d.txt");
        List<Matrix> matrices = InputStreamParser.parse(new FileInputStream(testInputFile));

        for (Matrix m : matrices)
            assertNotNull(m);

        Matrix left = matrices.get(0);
        Matrix right = matrices.get(1);
        Matrix expected = matrices.get(2);

        Matrix actual = left.dotProduct(right);
        assertEquals(expected, actual);


        // todo: test if error is thrown
//        assertThrows(IllegalArgumentException.class, () -> right.dotProduct(left));

    }

    private List<Matrix> getTestMatrices(String resourceName) throws FileNotFoundException {
        File testInputFile = TestUtils.getFileFromResources(resourceName);
        return InputStreamParser.parse(new FileInputStream(testInputFile));
    }

    @Test
    @DisplayName("Main diagonal transposition.")
    public void mainDiagonalTransposition() throws FileNotFoundException {
        List<Matrix> matrices = getTestMatrices("transposition/main_diagonal.txt");
        Matrix actual = matrices.get(0).transpose();
        Matrix expected = matrices.get(1);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Side diagonal transposition.")
    public void sideDiagonalTransposition() throws FileNotFoundException {

        List<Matrix> matrices = getTestMatrices("transposition/side_diagonal.txt");
        Matrix actual = matrices.get(0).sideTranspose();
        Matrix expected = matrices.get(1);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Horizontal line transposition.")
    public void HorizontalLineTransposition() throws FileNotFoundException {

        List<Matrix> matrices = getTestMatrices("transposition/horizontal_line.txt");
        Matrix actual = matrices.get(0).horizontalTranspose();
        Matrix expected = matrices.get(1);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Vertical  line transposition.")
    public void verticalLineTransposition() throws FileNotFoundException {

        List<Matrix> matrices = getTestMatrices("transposition/vertical_line.txt");
        Matrix actual = matrices.get(0).verticalTranspose();
        Matrix expected = matrices.get(1);
        assertEquals(expected, actual);
    }


}
