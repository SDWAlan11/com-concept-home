package com.concept.test.profiling.memory.matriz;

public class MatrizExample {
    public void fillMatrix(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++ ){
            for (int j = 0; j < matriz[0].length; j++){
                matriz[i][j] = (i* matriz[0].length) + (j+1);
                System.out.print(matriz[i][j]+ "y ");
            }
            System.out.println("x ");
        }
    }

    public void fillInverseMatrix(int[][] matriz) {
        for (int i = matriz.length -1 ; i >= 0; i-- ){
            for (int j = matriz[0].length - 1; j >= 0; j--){
                matriz[i][j] = (i* matriz[0].length) + (j+1);
                System.out.print(matriz[i][j]+ "y ");
            }
            System.out.println("x ");
        }
    }

    public static void main(String[] args){
        MatrizExample matrizExample = new MatrizExample();
        matrizExample.fillMatrix(new int [4][3]);
        System.out.println("");
        matrizExample.fillInverseMatrix(new int [4][3]);
    }
}
