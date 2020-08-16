package processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import processor.util.InputStreamParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestInputStreamParser {

    private final MatrixFactory matrixFactory = new BasicMatrixFactory();

    @Test
    @DisplayName("File input should be parsed to list of matrices.")
    public void parseFileInputStream() throws FileNotFoundException {
       File inputFile = TestUtils.getFileFromResources("stages/stage1_input.txt");
       File outputFile = TestUtils.getFileFromResources("stages/stage1_output.txt");

       List<Matrix> matrices = InputStreamParser.parse(new FileInputStream(inputFile));
       Matrix mx0 = matrices.get(0);
       Matrix mx1 = matrices.get(1);

       Matrix expectedMatrix = matrixFactory.create(mx0.n, mx0.m, new FileInputStream(outputFile));

       assertEquals(mx0.add(mx1), expectedMatrix);
    }
}
