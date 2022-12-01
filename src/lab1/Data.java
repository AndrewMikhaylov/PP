package lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Data {
    public static int N;
    public static int[][]MA;
    public static int[][]MX;
    public static int[][]MC;
    public static int[] Z;

    //Спільні ресурси
    private static int[][] MD;
    private static int a = 0;
    private static int d;
    private static int p;


    public synchronized int get_a() {
        return a;
    }

    public synchronized void set_a(int a) {
        this.a = a;
    }

    public synchronized int get_d() {
        return d;
    }
    public synchronized void set_d(int d){
        this.d=d;
    }

    public synchronized int get_p() {
        return p;
    }
    public synchronized void set_p(int p){
        this.p = p;
    }

    public synchronized int[][] get_MD() {
        return MD;
    }
    public synchronized void set_MD(int[][] MD){
        this.MD=MD;
    }

    public static void generateVector(int[] vector) {
        Random random = new Random();
        for (int i = 0; i < vector.length; i++) {
            vector[i] = 1;
        }
    }

    public int max_a_Value(int aTi){
        return Math.max(this.get_a(), aTi);
    }

    public static int getLowestValueFromVector(int[] vector){
        int value = Integer.MAX_VALUE;
        for (int i = 0; i<vector.length; i++){
            if (vector[i]<value){
                value = vector[i];
            }
        }
        return value;
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
        System.out.println(vectorName + " generated");
    }

    public static int enterNumber(String numberName) {
        System.out.println(numberName +" generated");
        int number = 1;
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
        System.out.println(matrixName+" generated");
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(String.format("%3d ", matrix[i][j]));
            }
            System.out.println();
        }
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

    public static int[][] multiplyNumberByMatrix(int[][] matrix, int scalar){
        int[][] newMatrix = matrix;
        for (int i = 0; i<matrix.length; i++){
            for (int j = 0; j<matrix[i].length; j++){
                newMatrix[i][j]=newMatrix[i][j]*scalar;
            }
        }
        return newMatrix;
    }

    public static int multiplyNumberByNumber(int a, int b){
        int result = a*b;
        return result;
    }

    public static int[][] addMatrixAndMatrix(int[][]matrix1, int[][]matrix2){
        int[][] finalMatrix = new int[matrix1.length][matrix2[0].length];
        for (int i = 0; i<finalMatrix.length; i++){
            for (int j = 0; j<finalMatrix[i].length;j++){
                finalMatrix[i][j] = matrix1[i][j]+matrix2[i][j];
            }
        }
        return finalMatrix;
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

    public static void writeToMA(int[][] matrix, int start, int end) {
        for (int i=0;i< matrix.length;i++)
        {
            int k = 0;
            for (int j = start; j<end; j++){
                MA[i][j] = matrix[i][k];
            }
        }
    }
}
