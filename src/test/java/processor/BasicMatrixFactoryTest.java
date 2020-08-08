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
import static processor.TestUtils.getFileFromResources;

@DisplayName("Matrix Factory class test case")
class BasicMatrixFactoryTest {

    private final int n;
    private final int m;
    private final int[] elements;


    BasicMatrixFactoryTest() {

        Random rand = new Random();

        // fixme: basic parameters for testing, can be moved to before type method
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
        Matrix mx1 = MatrixFactory.create(n, m);
        BasicMatrix mx2 = new BasicMatrix(n, m);
        assertEquals(mx1, mx2);
    }

    @Test
    @DisplayName("Matrices should be initialized from dimensions and 1d vale array")
    void createFromArray() {
        Matrix mx11 = MatrixFactory.create(n, m, elements);
        BasicMatrix mx12 = new BasicMatrix(n, m, elements);
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

        Matrix mxIn = MatrixFactory.create(n, m, inputFile);
        Matrix mxOut = MatrixFactory.create(n, m, outputFile);

        assertEquals(mxOut, mxIn);

        // test guard against dimension mismatch
        assertThrows(IllegalArgumentException.class, () -> MatrixFactory.create(n + 1, m, inputFile));
        assertThrows(IllegalArgumentException.class, () -> MatrixFactory.create(n, m + 1, inputFile));
        assertThrows(IllegalArgumentException.class, () -> MatrixFactory.create(n + 1, m + 1, inputFile));

        // test if matrix is created using smaller matrix size
        assertEquals((n - 1) * (m - 1), MatrixFactory.create(n - 1, m - 1, inputFile).length);

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

        Matrix mxIn = MatrixFactory.create(inputFile);
        Matrix mxOut = MatrixFactory.create(n, m, outputFile);

        assertEquals(mxOut, mxIn);

    }

}