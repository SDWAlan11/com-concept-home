package com.concept.test.profiling.memory;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Problem: You are given a zero-indexed array A consisting of n > 0 integers; you must return
 * the number of unique values in array A.
 * Solution O(n log n): First, sort array A; similar values will then be next to each other.
 * Finally, just count the number of distinct pairs in adjacent cells.
 */
public class ZeroIndex {

    public int solution(int[] arrayOfNums) {
        int len = arrayOfNums.length;
        Arrays.sort(arrayOfNums);
        int result = 1;
        for (int k=1; k < len; k++){
            if (arrayOfNums[k] != arrayOfNums[k-1])
                result += 1;
        }
        return result;
    }

    public static void main(String[] args) {
        ZeroIndex permMissingElem = new ZeroIndex();
        System.out.println(permMissingElem.solution(new int[]{2, 3, 1, 3}));

    }
}