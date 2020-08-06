package processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Factory class for creating matrices.
 *
 * Matrices can be created by requesting a type and dimensions (empty, identify), or by specifying data source
 * for values (input stream, file ...)
 *
 */
public class MatrixFactory {
    /**
     * Create empty n x m matrix.
     *
     * @param n row number
     * @param m column number
     * @return empty matrix
     */
    public static Matrix create(int n, int m) {
        return new Matrix(n, m);
    }


    /**
     * Create matrix from dimensions and elements array.
     *
     * @param n row number
     * @param m column number
     * @param elements element array
     * @return matrix with elements put in row first order
     */
    public static Matrix create(int n, int m, int[] elements) {
        return new Matrix(n, m, elements);
    }


    /**
     * Create matrix from inputStream.
     *
     * Assuming first two integers in stream are dimensions.
     *
     * @param inputStream
     * @throws IndexOutOfBoundsException when matrix size gt elements number in input stream
     * @return matrix with dimensions and elements read from input stream
     */
    public static Matrix create(InputStream inputStream) throws IndexOutOfBoundsException {
        Scanner scanner = new Scanner(inputStream);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // fixme: duplicate code in  create(int n, int m, InputStream inputStream)
        int[] elements = new int[n * m];
        for (int i = 0; i < n * m; i++) {
            try {
                elements[i] = scanner.nextInt();
            } catch (NoSuchElementException e) {
                throw new IllegalArgumentException("Provided source has less elements value than matrix length: " + i);
            }
        }
        return create(n, m, elements);
    }

    /**
     * Create n x m matrix from input stream.
     *
     * @param n row number
     * @param m column number
     * @param inputStream
     * @throws IndexOutOfBoundsException when matrix size gt elements in file
     * @return matrix with n x m dimensions and elements read from input stream
     */
    public static Matrix create(int n, int m, InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        int[] elements = new int[n * m];
        for (int i = 0; i < n * m; i++) {
            try {
                elements[i] = scanner.nextInt();
            } catch (NoSuchElementException e) {
                throw new IllegalArgumentException("Provided source has less elements value than matrix length: " + i);
            }
        }
        return create(n, m, elements);
    }

    /**
     * Create matrix from file.
     * <p>
     * Assuming first two integers in file are dimensions.
     *
     * @param file file with matrix dimensions and elements
     * @return matrix with dimensions and elements read from file
     * @throws IndexOutOfBoundsException when matrix size gt elements number in file
     */
    public static Matrix create(File file) throws IndexOutOfBoundsException, FileNotFoundException {
        return create(new FileInputStream(file));
    }

    /**
     * Crate n x m matrix from file.
     *
     * @param n    row number
     * @param m    column number
     * @param file file to read data
     * @return matrix with n x m dimensions and elements read from file
     * @throws IndexOutOfBoundsException when matrix size gt elements number in file
     */
    public static Matrix create(int n, int m, File file) throws IndexOutOfBoundsException, FileNotFoundException {
        return create(n, m, new FileInputStream(file));

    }


}
