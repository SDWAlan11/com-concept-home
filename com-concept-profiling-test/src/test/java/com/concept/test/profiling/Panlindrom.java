package com.concept.test.profiling;

public class Panlindrom {

    String solution(int N, int K){
        char[] chars = new char[N];
        int innitialChar = 97;
        for (int i = 0; i <= N / 2 ; i++){
            chars[i] = (char)(innitialChar +  i%K);
            chars[N - (i + 1)] = (char)(innitialChar + i%K);
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args){
        System.out.println(new Panlindrom().solution(7,2));
        System.out.println(new Panlindrom().solution(8,3));
        System.out.println(new Panlindrom().solution(151,3));
    }
}
