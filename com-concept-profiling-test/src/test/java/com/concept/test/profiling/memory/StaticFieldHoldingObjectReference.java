package com.concept.test.profiling.memory;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

public class StaticFieldHoldingObjectReference {
	private Random random = new Random();
	public static final ArrayList<Double> list = new ArrayList<Double>(1000000);

	@Test
	public void givenStaticField_whenLotsOfOperations_thenMemoryLeak() throws InterruptedException {
	    for (int i = 0; i < 1000000; i++) {
	        list.add(random.nextDouble());
	    }
	    
	    System.gc();
	    Thread.sleep(10000); // to allow GC do its job
	}
	
	@Test
	public void givenNormalField_whenLotsOfOperations_thenGCWorksFine() throws InterruptedException {
	    addElementsToTheList();
	    System.gc();
	    Thread.sleep(10000); // to allow GC do its job
	}
	    
	private void addElementsToTheList(){
	    ArrayList<Double> list = new ArrayList<Double>(1000000);
	    for (int i = 0; i < 1000000; i++) {
	        list.add(random.nextDouble());
	    }
	}
}
