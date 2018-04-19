package com.phasec.plagsafe.detector;

import org.junit.Before;
import org.junit.Test;

public class WeightedComparisonStrategyTests {
	
	public WeightedComparisonStrategy instance;
	
	@Before
	public void setUp(){
		instance = new WeightedComparisonStrategy();
	}
	
	@Test
	public void testUpdateRequestCount(){
		
		Submissible sub1 = new Submission();
		Submissible sub2 = new Submission();
		instance.updateRequestCount();
		instance.compare(sub1, sub2);
	}

}
