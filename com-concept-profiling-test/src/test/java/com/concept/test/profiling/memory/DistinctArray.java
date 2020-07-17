package com.concept.test.profiling.memory;

import java.util.Arrays;
import java.util.Random;

public class DistinctArray {

	public int solution(int[] A) {
		return Arrays.stream(A).min().getAsInt();
		
	}
	
	public static void main(String[] args) {
		DistinctArray distinctArray = new DistinctArray();
		System.out.println(distinctArray.solution(new int[] {2,1,1}));
		System.out.println(distinctArray.solution(new int[] {2,1,1,2,3,1,1,4}));
		Random random = new Random();
		System.out.println(distinctArray.solution(random.ints(100000, 10, 100000).toArray()));

	}

}
