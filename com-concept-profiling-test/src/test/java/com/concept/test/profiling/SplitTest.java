package com.concept.test.profiling;

public class SplitTest {
    int solution(String s){
        String delimiters = "\\.|\\?|\\!";

        String[] tokensVal = s.split(delimiters);
        System.out.println("Count of tokens = " + tokensVal.length);
        int result = 0;
        for (int i = 0 ; i < tokensVal.length; i++){
            result = Math.max(result, tokensVal[i].trim().split("\\s+").length);
        }
        return result;
    }

    public static void main(String[] args){
        System.out.println(new SplitTest().solution("We are testers. Give us a try?"));
        System.out.println(new SplitTest().solution("Forget  CVs..Save time . x x"));
        System.out.println(new SplitTest().solution(""));
    }
}
