package processor;


import java.util.Scanner;

public class ConsoleInterface {

    private enum Operation {
        NONE,
        ADD,
        SCALE,
        MULTIPLY,
        EXIT
    }

    private final MatrixFactory matrixFactory;
    private Operation currentOperation;
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
            default:
                throw new AssertionError("unknown operation");
        }
    }

    private void resetState() {
        showMainMenu();
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
        System.out.println("The scalar multiplication result is:");
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
        float[] elements = new float[n * m];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = scanner.nextFloat();
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
                "0. Exit");
    }

    public static void main(String[] args) {
        ConsoleInterface consoleInterface = new ConsoleInterface();
        consoleInterface.run();

    }

}
