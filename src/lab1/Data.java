package lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Data {
    public static int N;
    public static int[] B;
    public static int[] C;
    public static int[] D;
    public static int[] E;
    public static int[][] MA;
    public static int[][] MB;
    public static int x;
    public static AtomicInteger a = new AtomicInteger();

    public static ArrayList<Integer> Z = new ArrayList<>();

    public static Semaphore S0 = new Semaphore(1);




    public static Semaphore S1 = new Semaphore(0);
    public static Semaphore S2 = new Semaphore(0);
    public static Semaphore S3 = new Semaphore(0);
    public static Semaphore S4 = new Semaphore(0);




    public static Semaphore S5 = new Semaphore(0);
    public static Semaphore S6 = new Semaphore(0);
    public static Semaphore S7 = new Semaphore(0);
    public static Semaphore S8 = new Semaphore(0);


    public static Semaphore S9 = new Semaphore(0);
    public static Semaphore S10 = new Semaphore(0);
    public static Semaphore S11 = new Semaphore(0);
    public static Semaphore S12 = new Semaphore(0);


    public static void generateVector(int[] vector) {
        Random random = new Random();
        for (int i = 0; i < vector.length; i++) {
            vector[i] = 1;
        }
    }
    public static void fillVectorFromCommandLine(int[] vector) {
        Scanner scanner = new Scanner(System.in);
        String readVector = scanner.nextLine();
        String[] strVectorValues = readVector.split(" ");
        for (int i = 0; i < vector.length; i++) {
            vector[i] = Integer.parseInt(strVectorValues[i]);
        }
    }

    public static void fillVector(int[] vector, String vectorName) {
        if (vector.length > 0 && vector.length <= 3) {
            System.out.print("Enter vector '" + vectorName + "': ");
            fillVectorFromCommandLine(vector);
        } else if (vector.length > 3) {
            generateVector(vector);
        }
        System.out.println("Vector '" + vectorName + "': " + Arrays.toString(vector));
    }

    public static int enterNumber(String numberName) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number '" + numberName + "': ");
        int number = scanner.nextInt();
        return number;
    }

    public static void generateMatrix(int[][] matrix) {
        Random random = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = 1;
            }
        }
    }

    public static void fillMatrixFromCommandLine(int[][] matrix) {
        Scanner scanner = new Scanner(System.in);
        String strRow;
        String[] strRowElements;
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("Enter row №" + (i + 1) + ": ");
            strRow = scanner.nextLine();
            strRowElements = strRow.split(" ");
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Integer.parseInt(strRowElements[j]);
            }
        }
    }

    public static void fillMatrix(int[][] matrix, String matrixName) {
        if (matrix.length > 0 && matrix.length <= 3) {
            System.out.println("<<<<<<<<<<<<Enter matrix '" + matrixName + "'>>>>>>>>>>>>");
            fillMatrixFromCommandLine(matrix);
        } else if (matrix.length > 3) {
            generateMatrix(matrix);
        }
        System.out.println("<<<<<<<<<<<<Matrix '" + matrixName + "'>>>>>>>>>>>>");
        printMatrix(matrix);
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(String.format("%3d ", matrix[i][j]));
            }
            System.out.println();
        }
    }
    public static void printVector(ArrayList<Integer> vector) {
        System.out.print("[ ");
        for (Integer vectorElement : vector) {
            System.out.print(vectorElement + " ");
        }

        System.out.println("]");
    }


    public static int multiplyVectors(int[] v1, int[] v2) {
        int result = 0;
        for (int i = 0; i < v1.length; i++) {
            result += v1[i] * v2[i];
        }
        return result;
    }
    public static int[] getSubVector(int[] vector, int startIndex, int endIndex) {
        int[] subVector;
        if (endIndex < startIndex) {
            subVector = new int[0];
        } else {
            subVector = new int[endIndex - startIndex];
        }
        int temp = 0;
        for (int i = startIndex; i < endIndex; i++) {
            subVector[temp++] = vector[i];
        }
        return subVector;
    }

    public static int[] multiplyNumberByVector(int number, int[] vector) {
        int[] newVector = new int[vector.length];
        for (int i = 0; i < vector.length; i++) {
            newVector[i] = vector[i] * number;
        }
        return newVector;
    }

    public static int[][] getSubMatrix(int[][] matrix, int startColumn, int endColumn) {
        int[][] subMatrix;
        if (endColumn < startColumn) {
            subMatrix = new int[matrix.length][0];
        } else {
            subMatrix = new int[matrix.length][endColumn - startColumn];
        }

        int temp;
        for (int i = 0; i < matrix.length; i++) {
            temp = 0;
            for (int j = startColumn; j < endColumn; j++) {
                subMatrix[i][temp++] = matrix[i][j];
            }
        }
        return subMatrix;
    }

    public static int[] multiplyVectorByMatrix(int[] vector, int[][] matrix) {
        int[] resVector = new int[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < vector.length; j++) {
                resVector[i] += vector[j] * matrix[j][i];
            }
        }
        return resVector;
    }

    public static int[][] multiplyMatrixByMatrix(int[][] m1, int[][] m2) {
        int[][] resMatrix = new int[m1.length][m2[0].length];

        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    resMatrix[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return resMatrix;
    }

    public static int[] addVectors(int[] v1, int[] v2) {
        int[] resV = new int[v1.length];
        for (int i = 0; i < v1.length; i++) {
            resV[i] = v1[i] + v2[i];
        }
        return resV;
    }

    public static void addZ(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            Z.add(vector[i]);
        }
    }
}
