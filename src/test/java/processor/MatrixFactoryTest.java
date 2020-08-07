package processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Matrix Factory class test case")
class MatrixFactoryTest {

    private final int n;
    private final int m;
    private final int[] elements;


    MatrixFactoryTest() {

        Random rand = new Random();

        // basic parameters for testing
        this.n = 5;
        this.m = 5;
        this.elements = new int[n * m];

        // init with random integers
        for (int i = 0; i < n * n; i++) {
            elements[i] = rand.nextInt();
        }
    }


    @Test
    @DisplayName("Empty Matrix should be created for given dimensions.")
    void createEmpty() {
        AbstractMatrix mx1 = MatrixFactory.create(n, m);
        Matrix mx2 = new Matrix(n, m);
        assertEquals(mx1, mx2);
    }

    @Test
    @DisplayName("Matrices should be initialized from dimensions and 1d vale array")
    void createFromArray() {
        AbstractMatrix mx11 = MatrixFactory.create(n, m, elements);
        Matrix mx12 = new Matrix(n, m, elements);
        assertEquals(mx11, mx12);
    }




    @ParameterizedTest
    @CsvSource({
            "11, 4, 5",
            "12, 4, 5"
    })
    @DisplayName("Matrices should be created based on dimensions parameters and values read from file.")
    void createFromDimensionsAndFile(int inputFileIndex, int n, int m) throws FileNotFoundException {

        int outputFileIndex = inputFileIndex % 10;
        File inputFile = getFileFromResources(inputFileIndex, "creation/input_%d.txt");
        File outputFile = getFileFromResources(outputFileIndex, "creation/output_%d.txt");

        AbstractMatrix mxIn = MatrixFactory.create(n, m, inputFile);
        AbstractMatrix mxOut = MatrixFactory.create(n, m, outputFile);

        assertEquals(mxOut, mxIn);

        // test guard against dimension mismatch
        assertThrows(IllegalArgumentException.class, () -> MatrixFactory.create(n + 1, m, inputFile));
        assertThrows(IllegalArgumentException.class, () -> MatrixFactory.create(n, m + 1, inputFile));
        assertThrows(IllegalArgumentException.class, () -> MatrixFactory.create(n + 1, m + 1, inputFile));

        // test if matrix is created using smaller matrix size
        assertEquals((n - 1) * (m - 1), MatrixFactory.create(n - 1, m - 1, inputFile).length);

    }

    private File getFileFromResources(int inputFileIndex, String formatString) {
        return TestUtils.getFileFromResources(String.format(formatString, inputFileIndex));
    }

    @ParameterizedTest
    @CsvSource({
            "21, 4, 5",
            "22, 4, 5"
    })
    void createFromFile(int inputFileIndex, int n, int m) throws IOException {
        int outputFileIndex = inputFileIndex % 10;
        File inputFile = getFileFromResources(inputFileIndex, "creation/input_%d.txt");
        File outputFile = getFileFromResources(outputFileIndex, "creation/output_%d.txt");

        AbstractMatrix mxIn = MatrixFactory.create(inputFile);
        AbstractMatrix mxOut = MatrixFactory.create(n, m, outputFile);

        assertEquals(mxOut, mxIn);

    }

}