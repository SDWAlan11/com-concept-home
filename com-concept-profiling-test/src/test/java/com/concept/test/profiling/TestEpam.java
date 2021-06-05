package com.concept.test.profiling;

//this will compare two strings, if some element not match with other return false or something like that
public class TestEpam {

    public static boolean solution (String string1, String string2) {
        String[] array = string1.split(" ");
        for (int i = 0; i < array.length; i++){
            if(countWord(array[i], string1) <= countWord(array[i], string2))
                continue;
            else
                return false;
        }
        return true;
    }

    private static int countWord(String s, String string) {
        String[] array = string.split(" ");
        int count = 0;
        for (int i = 0; i < array.length; i++){
            if(s.compareTo(array[i]) == 0)
                count++;
        }
        return count;
    }

    public static void main(String[] args){
        String string1 = "Hello Hello Hi Konichiwa Konichiwa Konichiwa";
        String string2 = "Quiobo Hello Hello Hi Konichiwa Konichiwa";

        System.out.println(solution(string1, string2));


        String string2_1 = "Hello Hello Hi Konichiwa Konichiwa Konichiwa";
        String string2_2 = "Hello Hello Hi Hi Hi Konichiwa Konichiwa Konichiwa";

        System.out.println(solution(string2_1, string2_2));
    }
}
