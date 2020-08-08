package processor.util;

import processor.Matrix;
import processor.MatrixFactory;

import java.io.InputStream;
import java.util.*;

/**
 * Helper class to parse console input used for checking the assignment.
 */
public class InputStreamParser {
    /**
     * Will read all the matrices that are present in input stream and return
     * a list of matrices read.
     * <p>
     * In case of error, the list with elements read so far will be returned,
     * ignoring offending data
     *
     * @param inputStream input stream with matrices to be extracted.
     * @return
     */
    public static List<Matrix> parse(InputStream inputStream) {
        List<Matrix> list = new ArrayList<>();
        Scanner scn = new Scanner(inputStream);
        try {
            while (true) {
                int n = scn.nextInt();
                int m = scn.nextInt();
                int[] elements = new int[n * m];
                for (int i = 0; i < n * m; i++) {
                    elements[i] = scn.nextInt();
                }
                // Factory could be moved to constructor or to method parameter.
                list.add(MatrixFactory.create(n, m, elements));
            }
        } catch (NoSuchElementException ignored) {}
        return list;
    }
}
