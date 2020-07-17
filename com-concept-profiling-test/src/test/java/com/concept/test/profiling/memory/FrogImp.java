package com.concept.test.profiling.memory;

public class FrogImp {

	public int solution(int startPoint, int endPoint, int jumpDistance) {
		if (startPoint > endPoint)
			throw new IllegalArgumentException("Y is greater than X");
		if (jumpDistance <= 0)
			throw new IllegalArgumentException("more jump");
		int jumps = 0;
		int distance = endPoint - startPoint;
		jumps = distance / jumpDistance; //2
		int jumpRemaning = distance % jumpDistance; //15
		jumps += jumpRemaning > 0 ? 1 : 0;
		
		return jumps;	
	}
	
	public static void main(String[] args) {
		FrogImp frogImp = new FrogImp();
		System.out.println(frogImp.solution(10, 85, 30));
		System.out.println(frogImp.solution(80, 85, 30));
		//System.out.println(frogImp.solution(85, 80, 30));
		//System.out.println(frogImp.solution(80, 85, 0));
		System.out.println(frogImp.solution(1, 100, 1));
		System.out.println(frogImp.solution(1, 1000000000, 2));
		


	}

}
