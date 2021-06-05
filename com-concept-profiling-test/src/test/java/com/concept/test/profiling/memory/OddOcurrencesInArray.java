package com.concept.test.profiling.memory;

import java.util.Arrays;

public class OddOcurrencesInArray {
	int solution(int[] array) {
		Arrays.sort(array);
		for (int i = 0; i < array.length; i+=2) {
			if( i+1 == array.length )
				return array[i];
			if( array[i] != array[i+1] )
				return array[i];
		}
		return 0;
	}

	int otherSolution(int[] array) {
		int result= 0;
		for (int i : array) {
			result ^= i;
		}
		return result;
	}

	public static void main(String[] args) {
		OddOcurrencesInArray oddOcurrencesInArray = new OddOcurrencesInArray();
		System.out.println(oddOcurrencesInArray.solution(new int[] {1,2,3,4,4,3,2}));
		System.out.println(oddOcurrencesInArray.solution(new int[] {4,1,3,4,1,3,2}));
		System.out.println(oddOcurrencesInArray.solution(new int[] {4,1,2,4,1,3,2}));
		System.out.println(oddOcurrencesInArray.solution(new int[] {1,2,3,4,1,3,2}));

		System.out.println(oddOcurrencesInArray.otherSolution(new int[] {1,2,1}));
		System.out.println(oddOcurrencesInArray.otherSolution(new int[] {1,2,3,4,4,3,2}));
		System.out.println(oddOcurrencesInArray.otherSolution(new int[] {4,1,3,4,1,3,2}));
		System.out.println(oddOcurrencesInArray.otherSolution(new int[] {4,1,2,4,1,3,2}));
		System.out.println(oddOcurrencesInArray.otherSolution(new int[] {1,2,3,4,1,3,2}));
		System.out.println(oddOcurrencesInArray.otherSolution(new int[] {1,2,3,4,4,1,3,2}));
		System.out.println(oddOcurrencesInArray.otherSolution(new int[] {1,2,3,4,4,1}));




	}

}
