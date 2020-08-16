package processor;


import java.util.Scanner;

public class ConsoleInterface {

    private enum Operation {
        NONE,
        ADD,
        SCALE,
        MULTIPLY,
        REFLECT,
        DETERMINE,
        INVERSE,
        EXIT
    }

    private enum Reflection {
        DIAGONAL, SIDE, VERTICAL, HORIZONTAL
    }

    private final MatrixFactory matrixFactory;
    private Operation currentOperation;
    private Reflection currentReflection;
    private final Scanner scanner;

    ConsoleInterface() {
        matrixFactory = new BasicMatrixFactory();
        currentOperation = Operation.NONE;
        scanner = new Scanner(System.in);
    }

    public void readInput(int integerInput) {
        switch (integerInput) {
            case 1:
                currentOperation = Operation.ADD;
                break;
            case 2:
                currentOperation = Operation.SCALE;
                break;
            case 3:
                currentOperation = Operation.MULTIPLY;
                break;
            case 4:
                currentOperation = Operation.REFLECT;
                break;
            case 5:
                currentOperation = Operation.DETERMINE;
                break;
            case 6:
                currentOperation = Operation.INVERSE;
                break;
            case 0:
                currentOperation = Operation.EXIT;
                break;
            default:
                throw new AssertionError("unknown operation.");
        }
        performOperation();

    }


    private void performOperation() {
        switch (currentOperation) {
            case EXIT:
                break;
            case ADD:
                doAdd();
                break;
            case SCALE:
                doScale();
                break;
            case MULTIPLY:
                doMultiply();
                break;
            case REFLECT:
                doReflect();
                break;
            case DETERMINE:
                doDetermine();
                break;
            case INVERSE:
                doInverse();
                break;
            default:
                throw new AssertionError("unknown operation");
        }
    }

    private void resetState() {
        showMainMenu();
    }

    private void doInverse() {
        int[] dim = readDimensions("Enter matrix size: ");
        Matrix matrix = readData("Enter matrix:", dim[0], dim[1]);
        Matrix inverse = matrix.getInverse();
        System.out.println("The result is:");
        System.out.println(inverse);
        resetState();
    }

    private void doDetermine() {
        int[] dim = readDimensions("Enter matrix size: ");
        Matrix matrix = readData("Enter matrix:", dim[0], dim[1]);
        double det = matrix.getDeterminant();
        System.out.println("The result is:");
        System.out.println(det);
        resetState();
    }

    private void doReflect() {
        chooseReflectionMethod();

        int[] dim = readDimensions("Enter matrix size: ");
        Matrix matrix = readData("Enter matrix:", dim[0], dim[1]);
        Matrix reflected;

        switch (currentReflection) {
            case DIAGONAL:
                reflected = matrix.transpose();
                break;
            case SIDE:
                reflected = matrix.reflectSideDiagonal();
                break;
            case VERTICAL:
                reflected = matrix.reflectVertical();
                break;
            case HORIZONTAL:
                reflected = matrix.reflectHorizontal();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + currentReflection);
        }

        System.out.println("The result is:");
        System.out.println(reflected);
        resetState();

    }

    private void showReflectionOptions() {
        System.out.println("1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line");
    }

    private void chooseReflectionMethod() {
        showReflectionOptions();
        switch (scanner.nextInt()) {
            case 1:
                currentReflection = Reflection.DIAGONAL;
                break;
            case 2:
                currentReflection = Reflection.SIDE;
                break;
            case 3:
                currentReflection = Reflection.VERTICAL;
                break;
            case 4:
                currentReflection = Reflection.HORIZONTAL;
                break;
            default:
                throw new AssertionError("unknown reflection");
        }


    }

    private Matrix[] readTwoMatrices() {
        Matrix[] out = new Matrix[2];
        int[] leftDim = readDimensions("Enter size of first matrix: ");
        out[0] = readData("Enter first matrix:", leftDim[0], leftDim[1]);

        int[] rightDim = readDimensions("Enter size of second matrix: ");
        out[1] = readData("Enter Second matrix:", rightDim[0], rightDim[1]);
        return out;
    }

    private void doMultiply() {
        Matrix[] summands = readTwoMatrices();
        System.out.println("The multiplication result is:");
        System.out.println(summands[0].dotProduct(summands[1]).toString());
        resetState();
    }

    private void doScale() {
        int[] dim = readDimensions("Enter size of matrix: ");
        Matrix mx = readData("Enter matrix: ", dim[0], dim[1]);
        System.out.println("Enter scalar value:");
        int scalar = scanner.nextInt();
        System.out.println("The multiplication by number result is:");
        System.out.println(mx.multiply(scalar).toString());
        resetState();
    }

    private void doAdd() {
        Matrix[] factors = readTwoMatrices();
        System.out.println("The addition result is:");
        System.out.println(factors[0].add(factors[1]).toString());
        resetState();
    }

    private int[] readDimensions(String prompt) {
        System.out.print(prompt);
        int[] dim = new int[2];
        dim[0] = scanner.nextInt();
        dim[1] = scanner.nextInt();
        return dim;

    }

    private Matrix readData(String prompt, int n, int m) {
        System.out.println(prompt);
        double[] elements = new double[n * m];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = scanner.nextDouble();
        }
        return matrixFactory.create(n, m, elements);

    }

    public void run() {
        resetState();
        do {
            readInput(scanner.nextInt());
        } while (currentOperation != Operation.EXIT);
    }

    private void showMainMenu() {
        System.out.println("1. Add matrices\n" +
                "2. Multiply matrix to a constant\n" +
                "3. Multiply matrices\n" +
                "4. Transpose matrix\n" +
                "5. Calculate a determinant\n" +
                "6. Inverse matrix\n" +
                "0. Exit");
    }

    public static void main(String[] args) {
        ConsoleInterface consoleInterface = new ConsoleInterface();
        consoleInterface.run();

    }

}
