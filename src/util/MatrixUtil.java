package util;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class MatrixUtil {
    public static int[] read(String fileName) throws IOException {
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(fileName))) {
            byte[] bytes = stream.readAllBytes();
            String s = new String(bytes);
            String[] mass = s.split(" ");
            int[] ints = new int[mass.length];
            for (int i = 0; i < mass.length; i++) {
                ints[i] = Integer.parseInt(mass[i]);
            }
            return ints;
        }
    }

    public static int dist(int[] chain, int a, int b) {
        return Math.min(Math.abs(a - b), Math.abs(chain.length - Math.max(a, b) + Math.min(a, b)));
    }

    public static int[][] getR(int[] chain) {
        int a = 0;
        int b;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < chain.length; i++) {
            b = a;
            a += chain[i];
            for (int j = b; j < a; j++) {
                map.put(j, i + 1);
            }
        }
        int[][] matrix = new int[chain.length][a];
        for (int i = 0; i < chain.length; i++) {
            for (int j = 0; j < a; j++) {
                matrix[i][j] = dist(chain, i + 1, map.get(j));
            }
        }
        return matrix;
    }

    public static void printR(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if (i != 0)
                System.out.println();
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
        }
    }

    /**
     * shiftN принимает на вход матрицу R, номер столбца j,
     * который необходимо циклически сдвинуть и логический параметр isUpper, если он истинный,
     * то выполняем циклический сдвиг j столбца вверх, если ложный – вниз.
     */
    public static int[][] shiftN(int[][] matrix, int j, boolean isUpper) {
        int a;
        if (isUpper) {
            a = matrix[0][j];
            for (int i = 0; i < matrix.length - 1; i++) {
                matrix[i][j] = matrix[i + 1][j];
            }
            matrix[matrix.length - 1][j] = a;
        } else {
            a = matrix[matrix.length - 1][j];
            for (int i = matrix.length - 1; i > 0; i--) {
                matrix[i][j] = matrix[i - 1][j];
            }
            matrix[0][j] = a;
        }
        return matrix;
    }

    /**
     * shiftHelp принимает на вход матрицу R, номер нулевой
     * строки для текущего столбца – возвращает isUpper (либо истину, либо ложь),
     * в зависимости от того, куда необходимо будет циклически сдвинуть j столбец
     */
    public static boolean shiftHelp(int[][] matrix, int num) {
        int strUp = 0;
        int strDown = 0;
        int u = num - 1;
        int d = num + 1;
        if (num == 0) {
            u = matrix.length - 1;
        }
        if (num == matrix.length - 1) {
            d = 0;
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[u][i] == 0)
                strUp++;
            if (matrix[d][i] == 0)
                strDown++;
        }
        return strUp >= strDown;
    }

    public static int[][] copy(int[][] matrix) {
        int[][] mass = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            mass[i] = Arrays.copyOf(matrix[i], matrix[0].length);
        }
        return mass;
    }

    public static int[][] shift(int[][] matrix) {
        int[][] newMatrix = MatrixUtil.copy(matrix);
        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][j] == 0) {
                    boolean has = MatrixUtil.shiftHelp(matrix, i);
                    MatrixUtil.shiftN(newMatrix, j, has);
                    break;
                }
            }
        }
        return newMatrix;
    }

    public static void write(int[][] matrix, String fileName) throws IOException {
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileName))) {
            String s = "";
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    s += matrix[i][j] + " ";
                }
                s += "\r\n";
            }
            stream.write(s.getBytes());
        }
    }
}

