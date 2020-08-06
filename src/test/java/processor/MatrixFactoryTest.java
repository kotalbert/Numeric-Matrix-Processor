package processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
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
        Matrix mx1 = MatrixFactory.create(n, m);
        Matrix mx2 = new Matrix(n, m);
        assertEquals(mx1, mx2);
    }

    @Test
    @DisplayName("Matrices should be initialized from dimensions and 1d vale array")
    void createFromArray() {
        Matrix mx11 = MatrixFactory.create(n, m, elements);
        Matrix mx12 = new Matrix(n, m, elements);
        assertEquals(mx11, mx12);
        fail();
    }

    @Test
    void createFromInputStream() {
    }

    @ParameterizedTest
    @CsvSource({
            "1, 4, 5",
            "2, 4, 5"
    })
    @DisplayName("Matrices should be created based on dimensions parameters and values read from file.")
    void createFromFile(int testFilesIndex, int n, int m) throws FileNotFoundException {

        // test for reading declared array
        ClassLoader classLoader = getClass().getClassLoader();
        URL inputUrl = classLoader.getResource(String.format("creation/input_%d.txt", testFilesIndex));
        URL outputUrl = classLoader.getResource(String.format("creation/input_%d.txt", testFilesIndex));

        assert inputUrl != null;
        File inputFile = new File(inputUrl.getPath());

        assert outputUrl != null;
        File outputFile = new File(outputUrl.getPath());

        assertTrue(inputFile.exists());
        assertTrue(outputFile.exists());

        Matrix imx1 = MatrixFactory.create(n, m, inputFile);
        Matrix imx2 = MatrixFactory.create(n, m, outputFile);


        assertEquals(imx1, imx2);

        // test guard against dimension mismatch
        assertThrows(IllegalArgumentException.class, () -> MatrixFactory.create(n + 1, m, inputFile));
        assertThrows(IllegalArgumentException.class, () -> MatrixFactory.create(n, m + 1, inputFile));
        assertThrows(IllegalArgumentException.class, () -> MatrixFactory.create(n + 1, m + 1, inputFile));

        assertEquals((n - 1) * (m - 1), MatrixFactory.create(n - 1, m - 1, inputFile).length);

    }

}