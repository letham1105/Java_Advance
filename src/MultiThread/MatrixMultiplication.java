package MultiThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatrixMultiplication {
    static int n = 3;

    public static void main(String[] args) {
  int[][] matrix1 = generateRandomMatrix(n);
  int[][] matrix2 = generateRandomMatrix(n);
        System.out.println("Matrix 1");
        printMatrix(matrix1);
        System.out.println("\nMatrix 2");
        printMatrix(matrix2);
        int[][] result = multiplyMatrix(matrix1, matrix2);
        System.out.println("\nResult Matrix");
        printMatrix(result);
    }
    // hàm tạo ma trận ngẫu nhiên
    static int[][] generateRandomMatrix(int n ){
        int[][] matrix = new int[n][n];
        for ( int i =0; i <n;i++){
            for (int j =0; j <n; j++){
                matrix[i][j] = (int) (Math.random() *10);
            }
        }
        return matrix;
    }
    static int[][] multiplyMatrix(int[][] matrix1, int[][] matrix2){
        int[][] result = new int[n][n];
        // su dung ExecutorService de quan li cac luong
        ExecutorService executor = Executors.newFixedThreadPool(n);
        // lap qua tung phan tu cua ma tran ket qua va gui cho cac luong
        for (int i =0; i <n; i++){
            for (int j =0; j < n; j++){
                final int row = i;
                final int col = j;
                executor.submit(()->{
                    int sum =0;
                    for ( int k =0; k <n; k++){
                        sum += matrix1[row][k]*matrix2[k][row];
                    }
                    result[row][col] = sum;
                });
            }
        }
        // Dung ExecutorService sau khi tat ca cac tac vu duoc hoan thanh
        executor.shutdown();
        try{
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
    // ham in matran
    static void printMatrix(int[][] matrix){
        for(int[] row : matrix){
            for (int element : row){
                System.out.print(element+ " ");
            }
            System.out.println();
        }
    }
}
