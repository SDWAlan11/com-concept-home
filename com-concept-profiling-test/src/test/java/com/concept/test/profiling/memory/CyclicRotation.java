package com.concept.test.profiling.memory;

import java.util.Arrays;

public class CyclicRotation {

	int[] solution(int array[], int times) {
		
		int [] rotatedA = new int[array.length];
		   
	    for(int i=0; i<array.length; i++) {
	      int rotatedIndex = (i + times) % array.length;

	      rotatedA[rotatedIndex] = array[i];
	    }
	    return rotatedA;
	}
	
	public static void main(String[] args) {
		CyclicRotation cyclicRotation = new CyclicRotation();
		System.out.println(Arrays.toString(cyclicRotation.solution(new int[] {1,2,3,4}, 1)));
		System.out.println(Arrays.toString(cyclicRotation.solution(new int[] {1,2,3,4}, 2)));

	}

}
