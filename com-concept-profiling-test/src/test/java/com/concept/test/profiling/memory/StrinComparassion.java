/**
 * 
 */
package com.concept.test.profiling.memory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author daniel.arroyo
 *
 */
public abstract class StrinComparassion {
	
	static int [][] game;
	
	static List<String> ha = new ArrayList();

	public static void main(String... args) {
		int[] i = new int[-1];

		final String string1 = "String";
		  String string2 = new String("String");
	final String string3 = new String("String");
	String string4 = "String";
	game[3][3]=1;
		System.out.println(string1 == string2);
		
		System.out.println(string1.toUpperCase().equalsIgnoreCase(string3));
		
		System.out.println(System.identityHashCode(string1));
		System.out.println(System.identityHashCode(string2));
		System.out.println(System.identityHashCode(string3));
		System.out.println(System.identityHashCode(string4));
		
		System.out.println(string1.hashCode());
		System.out.println(string2.hashCode());
		System.out.println(string3.hashCode());
		System.out.println(string4.toUpperCase().hashCode());
		
		
	}
}
