package com.concept.test.profiling.memory;

import java.util.*;

public class IndexTest {

	int solution(int... A) {

		int variableReturn = 1;
		Arrays.sort(A);
		for (int j = 0; j < A.length; j++) {
			if (variableReturn == A[j])
				variableReturn = A[j] + 1;
		}
		return variableReturn;
	}

	public static void main(String[] args) {
		System.out.println(new IndexTest().solution(3, 1, 1, 2, 2, 6, 4));

		System.out.println(new IndexTest().solution(1, 3, 6, 4, 1, 2));

		System.out.println(new IndexTest().solution(-3, -1));

		System.out.println(new IndexTest().solution(-3, -1, 1));

		Thread t = new Thread(() -> {
			Random random = new Random();
			int[] array = random.ints(100000, 10, 100000).toArray();
			Arrays.sort(array);
			System.out.println(array.length);
			long startTime = System.currentTimeMillis();
			System.out.println(new IndexTest().solution(array));
			long endTime = System.currentTimeMillis();
			System.err.println(endTime - startTime);
		});
		t.start();
		t.run();

	}

}
