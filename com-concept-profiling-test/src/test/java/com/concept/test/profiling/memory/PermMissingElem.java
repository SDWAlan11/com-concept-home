package com.concept.test.profiling.memory;

import java.util.Arrays;
import java.util.Random;

//This problem has a mathematical solution, based on the fact that the sum of consecutive integers from 1 to n is equal to n(n+1)/2.
//https://en.wikipedia.org/wiki/1_%2B_2_%2B_3_%2B_4_%2B_%E2%8B%AF
public class PermMissingElem {

	public int solution(int[] arrayOfNums) {
		int sumE = Arrays.stream(arrayOfNums).sum();
		int n = arrayOfNums.length + 1;
		int missingNumber = n* (n+1)/2;
		return missingNumber - sumE;
	}

	public int solution2(int[] arrayOfNums) {
		if(arrayOfNums.length<= 0){ // un if solo por si llega vacio el array
			return 1;
		}
		int x1 = arrayOfNums[0]; //primer valor array 2 para el for de abajo
		int x2 = 1;				 //uno
		for (int i = 1; i < arrayOfNums.length; i++) {
			x1 ^= arrayOfNums[i];  //itero en 2 posicion por los valores del array aplicando XOR X1 = 5 --- 101
		}
		for (int i = 2; i <= arrayOfNums.length + 1; i++) {
			x2 ^= i;				//lo hace por el tamaño del array para saber el tamaño de bits del arreglo x2 = 1 - 001
		}
		return (x1 ^ x2);    //4 100

	}
	public static void main(String[] args) {
		PermMissingElem permMissingElem = new PermMissingElem();
		System.out.println(permMissingElem.solution(new int[] {2,3}));
		System.out.println(permMissingElem.solution2(new int[] {2,3}));
		System.out.println(permMissingElem.solution(new int[] {2,3,1}));
		System.out.println(permMissingElem.solution(new int[] {2,3,1}));
		System.out.println(permMissingElem.solution(new int[] {2,3,1,5})); //ejemplo
		System.out.println(permMissingElem.solution2(new int[] {2,3,1,4,5,7}));
		System.out.println(permMissingElem.solution(new int[] {2,3,1,5,4}));
		
		Random random = new Random();
		int[] array = random.ints(100000, 10, 100000).toArray();
		System.out.println(permMissingElem.solution(array));

	}

}
