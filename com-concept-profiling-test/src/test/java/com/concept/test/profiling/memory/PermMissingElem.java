package com.concept.test.profiling.memory;

import java.util.Arrays;
import java.util.Random;

//https://en.wikipedia.org/wiki/1_%2B_2_%2B_3_%2B_4_%2B_%E2%8B%AF
public class PermMissingElem {

	public int solution(int[] arrayOfNums) {
		int sumE = Arrays.stream(arrayOfNums).sum();
		int len = arrayOfNums.length + 1;
		int missingNumber = len* (len+1)/2;
		return missingNumber - sumE;
	}
	public static void main(String[] args) {
		PermMissingElem permMissingElem = new PermMissingElem();
		System.out.println(permMissingElem.solution(new int[] {2,3}));
		System.out.println(permMissingElem.solution(new int[] {2,3,1}));
		System.out.println(permMissingElem.solution(new int[] {2,3,1,5}));
		System.out.println(permMissingElem.solution(new int[] {2,3,1,5,4}));
		
		Random random = new Random();
		int[] array = random.ints(100000, 10, 100000).toArray();
		System.out.println(permMissingElem.solution(array));

	}

}
