package processor;


import java.util.Scanner;

public class ConsoleInterface {

    enum state {
        READING_DIMENSIONS,
        CHOOSING_OPERATION,
        READING_DATA,
    }

    enum operation {
        ADD,
        SCALE,
        MULTIPLY
    }

    private state currentState;

    ConsoleInterface() {
        currentState = state.CHOOSING_OPERATION;
    }

    public void readInput(int integerInput) {
        switch (currentState) {
            case READING_DIMENSIONS:
                break;
            case CHOOSING_OPERATION:
                chooseOperation(integerInput);
            case READING_DATA:
                break;
        }

    }

    private void chooseOperation(int integerInput) {
        switch (integerInput) {
            case 1:
            case 2:
            default:
                throw new AssertionError("unknown operation");
        }

    }

    public void run() {
        showMainMenu();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            readInput(scanner.nextInt());
        }
    }

    private void showMainMenu() {
        System.out.println("Main menu: 1 2 3 4 0 (exit)");
    }

    public static void main(String[] args) {
        ConsoleInterface consoleInterface = new ConsoleInterface();
        consoleInterface.run();

    }

}
