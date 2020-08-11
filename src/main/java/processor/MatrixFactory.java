package processor;

import processor.util.InputStreamCopier;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Factory class for creating matrices.
 * <p>
 * Matrices can be created by requesting a type and dimensions (empty, identify), or by specifying data source
 * for values (input stream, file ...)
 */
public abstract class MatrixFactory {
    /**
     * Create empty n x m matrix.
     *
     * @param n row number
     * @param m column number
     * @return empty matrix
     */
    public  Matrix create(int n, int m) {
        return new BasicMatrix(n, m);
    }


    /**
     * Create matrix from dimensions and elements array.
     *
     * @param n        row number
     * @param m        column number
     * @param elements element array
     * @return matrix with elements put in row first order
     */
    public  Matrix create(int n, int m, float[] elements) {
        return new BasicMatrix(n, m, elements);
    }

    /**
     * Create matrix from inputStream.
     * <p>
     * Assuming first two integers in stream are matrix dimensions.
     *
     * @param inputStream
     * @return matrix with dimensions and elements read from input stream
     * @throws IndexOutOfBoundsException when matrix size gt elements number in input stream
     */
    public  Matrix create(InputStream inputStream) throws IndexOutOfBoundsException, IOException {

        InputStreamCopier inputStreamCopier = new InputStreamCopier(inputStream);

        // needs a copy as input stream already used to instantiate InputStreamCopier
        Scanner scanner = new Scanner(inputStreamCopier.getStreamCopy());
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // input stream needs to be traversed twice, so copy is made to read element values
        return create(n, m, inputStreamCopier.getStreamCopy(), true);
    }


    /**
     * Create n x m matrix from input stream.
     * <p>
     * Assuming input stream contains only values to be parsed.
     *
     * @param n           row number
     * @param m           column number
     * @param inputStream
     * @return matrix with n x m dimensions and elements read from input stream
     * @throws IndexOutOfBoundsException when matrix size gt elements in file
     */
    public  Matrix create(int n, int m, InputStream inputStream) {
        return create(n, m, inputStream, false);
    }
    /**
     * Create n x m matrix from input stream.
     * <p>
     * Assuming input stream contains dimension values to be skipped and values to be parsed.
     *
     * @param n           row number
     * @param m           column number
     * @param inputStream
     * @parame skipDimensions if true, first two ints in input stream are discarded.
     * @return matrix with n x m dimensions and elements read from input stream
     * @throws IndexOutOfBoundsException when matrix size gt elements in fil
     */
    public  Matrix create(int n, int m, InputStream inputStream, boolean skipDimensions) {
        Scanner scanner = new Scanner(inputStream);
        if (skipDimensions) {
            scanner.nextInt();
            scanner.nextInt();
        }

        float[] elements = new float[n * m];
        for (int i = 0; i < n * m; i++) {
            try {
                elements[i] = scanner.nextFloat();
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
    public  Matrix create(File file) throws IndexOutOfBoundsException, IOException {
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
    public  Matrix create(int n, int m, File file) throws IndexOutOfBoundsException, FileNotFoundException {
        return create(n, m, new FileInputStream(file));

    }


}
