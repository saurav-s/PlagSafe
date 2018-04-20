package com.phasec.plagsafe.detector;

import org.junit.Before;
import org.junit.Test;

public class AllComparisonStrategiesTest {
	
	AllComparisonStrategies instance;
	
	@Before
	public void setUp(){
		
		instance = new AllComparisonStrategies();
	}
	
	@Test
	public void testUpdateRequestCount(){
		
		instance.updateRequestCount();
		
	}
	
	@Test
	public void testCompare(){
		
		Submissible sub1 = new Submission();
		Submissible sub2 = new Submission();
		
		instance.compare(sub1, sub2);
	}

}
