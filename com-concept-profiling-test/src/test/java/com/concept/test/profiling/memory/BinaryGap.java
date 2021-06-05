package com.concept.test.profiling.memory;

public class BinaryGap {
	
	int solution(int N) {
		System.out.print(Integer.toBinaryString(N) + "  ");
		int[] A = new int[32];
        int t = 0;
        for (int i = 0; i < 32; ++i)
            if (((N >> i) & 1) != 0) //Corrimiento por cada contador en 1 exemplo 5 101 >> 1 10 >> 2 1
                A[t++] = i;				// Al detectar que es un valor diferente de cero guarda el regristro con un incremental

        int ans = 0;
        for (int i = 0; i <= t; ++i)
            ans = Math.max(ans, A[i+1] - A[i]); //Aqui se recorre hasta que se encuentre el mayor salto
        return ans - 1;
	}

	public static void main(String[] args) {
		BinaryGap binaryGap = new BinaryGap();
		System.out.println(binaryGap.solution(5));
		System.out.println(binaryGap.solution(8));
		System.out.println(binaryGap.solution(9));
		System.out.println(binaryGap.solution(1041));
		System.out.println(binaryGap.solution(Integer.MAX_VALUE));
		System.out.println(binaryGap.solution(Integer.MAX_VALUE - 1));
		System.out.println(binaryGap.solution(Integer.MAX_VALUE - 3));
		System.out.println(binaryGap.solution(Integer.MAX_VALUE - 4));
		System.out.println(binaryGap.solution(Integer.MAX_VALUE - 5));
		System.out.println(binaryGap.solution(Integer.MAX_VALUE - 6));
		
	}

}
