package program;

import util.MatrixUtil;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            int[][] matrix = MatrixUtil.getR(MatrixUtil.read("data.txt"));
            MatrixUtil.printR(matrix);
            System.out.println();
            System.out.println();
            MatrixUtil.printR(MatrixUtil.shift(matrix));;
            int[][] mass = MatrixUtil.shift(matrix);
            MatrixUtil.write(mass, "output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
