package com.concept.test.profiling.memory;


public class Adder {
       
	public long addIncremental(long l) {
              Long sum=0L;
               sum =sum+l;
               return sum;   
	}
	
	public static void main(String[] args) throws InterruptedException {
             Adder adder = new Adder();
              for(long i = 0;i<1000;i++) {
                     adder.addIncremental(i);
                     
              }
       }
}