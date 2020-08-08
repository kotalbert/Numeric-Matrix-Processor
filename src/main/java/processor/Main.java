package processor;


import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {


        Scanner scanner = new Scanner(System.in);

        // todo: write method reading all matrices in input stream
        //  and returning array or matrices

        int n1 = scanner.nextInt();
        int m1 = scanner.nextInt();
        int[] elements1 = new int[n1 * m1];

        for (int i = 0; i < n1 * m1; i++) {
            elements1[i] = scanner.nextInt();
        }

        AbstractMatrix mx1 = MatrixFactory.create(n1, m1, elements1);

        int n2 = scanner.nextInt();
        int m2 = scanner.nextInt();
        int[] elements2 = new int[n2 * m2];

        for (int i = 0; i < n2 * m2; i++) {
            elements2[i] = scanner.nextInt();
        }

        AbstractMatrix mx2 = MatrixFactory.create(n2, m2, elements2);

        try {
            AbstractMatrix mxOutput = mx1.add(mx2);
            System.out.println(mxOutput);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR");
        }


    }
}
