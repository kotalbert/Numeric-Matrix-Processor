package processor;


import processor.util.InputStreamParser;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Matrix> matrices = InputStreamParser.parse(System.in);

        Matrix mx0 = matrices.get(0);
        Matrix mx1 = matrices.get(1);

        try {
            Matrix mxOutput = mx0.add(mx1);
            System.out.println(mxOutput);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR");
        }


    }
}
