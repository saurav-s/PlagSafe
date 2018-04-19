package com.phasec.plagsafe.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SystemUsageInfoTests {
	
	SystemUsageInfo instance;
	
	@Before
	public void setUp(){
		instance = new SystemUsageInfo();
	}
	
	@Test
	public void testSystemStartDate(){
		
		instance.setSystemStartDate("04/19/2018 16:07:33");
		String obtainedResult = instance.getSystemStartDate();
		assertEquals("04/19/2018 16:07:33", obtainedResult);
		
	}
	
	@Test
	public void testSystemLastUsed(){
		
		instance.setSystemLastUsed("04/19/2018 16:07:33");
		String obtainedResult = instance.getSystemLastUsed();
		assertEquals("04/19/2018 16:07:33", obtainedResult);
		
	}
	
	@Test
	public void testTotalRuns(){
		
		instance.setTotalRuns(33);
		int obtainedResult = instance.getTotalRuns();
		assertEquals(33, obtainedResult);
		
	}
	
	
	@Test
	public void testLogicalComparisonRequested(){
		
		instance.setLogicalComparisonRequested(33);
		int obtainedResult = instance.getLogicalComparisonRequested();
		assertEquals(33, obtainedResult);
		
	}
	
	@Test
	public void testRenamingComparisonRequested(){
		
		instance.setRenamingComparisonRequested(33);
		int obtainedResult = instance.getRenamingComparisonRequested();
		assertEquals(33, obtainedResult);
		
	}
	
	@Test
	public void testRefactoringComparisonRequested(){
		
		instance.setRefactoringComparisonRequested(33);
		int obtainedResult = instance.getRefactoringComparisonRequested();
		assertEquals(33, obtainedResult);
		
	}
	
	@Test
	public void testWeightedComparisonRequested(){
		
		instance.setWeightedComparisonRequested(33);
		int obtainedResult = instance.getWeightedComparisonRequested();
		assertEquals(33, obtainedResult);
		
	}
	
	@Test
	public void testTotalFilesCompared(){
		
		instance.setTotalFilesCompared(33);
		int obtainedResult = instance.getTotalFilesCompared();
		assertEquals(33, obtainedResult);
		
	}
	
	@Test
	public void testMaxSystemLoad(){
		
		instance.setMaxSystemLoad(33);
		int obtainedResult = instance.getMaxSystemLoad();
		assertEquals(33, obtainedResult);
		
	}
	
	@Test
	public void testSystemFailures(){
		
		instance.setSystemFailures(33);
		int obtainedResult = instance.getSystemFailures();
		assertEquals(33, obtainedResult);
		
	}
	
	@Test
	public void testToString(){
		
		String obtainedResult = instance.toString();
		assertNotNull(obtainedResult);
	}
	
	
	
	

}
