package com.phasec.plagsafe.models;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class ReportTests {
	
	private static final String SOURCE = "def sum(a, b):";
	private static final String TARGET = "def sum(a, b):";
	private static final String REMARK = "Similarities Detected!";
	
	
	Report report;
	
	@Before
	public void setUp(){
		report = new Report(SOURCE, TARGET, 100, REMARK);
	}
	
	@Test
	public void testToString(){
		String obtainedResult = report.toString();
		String expectedResult = "Report [sourceFile=def sum(a, b):, targetFile=def sum(a, b):, matchPercentage=100, matchRemark=Similarities Detected!]";
		assertEquals(expectedResult, obtainedResult);
	}
	
	@Test
	public void testSourceFileAttribute(){
		
		assertEquals(report.getSourceFile(), SOURCE);
		
		report.setSourceFile("");
		assertEquals(report.getSourceFile(), "");
	}
	
	@Test
	public void testTargetFileAttribute(){
		
		assertEquals(report.getTargetFile(), TARGET);
		
		report.setTargetFile("");
		assertEquals(report.getTargetFile(), "");
	}
	
	@Test
	public void testMatchPercentageAttribute(){
		
		assertEquals(report.getMatchPercentage(), 100);
		
		report.setMatchPercentage(50);
		assertEquals(report.getMatchPercentage(), 50);
	}
	
	@Test
	public void testMatchRemarkAttribute(){
		
		assertEquals(report.getMatchRemark(), REMARK);
		
		report.setMatchRemark("No Matches");
		assertEquals(report.getMatchRemark(), "No Matches");
	}
	
	@Test
	public void testCompareTo(){
		
		Report report1 =  new Report(SOURCE, TARGET, 100, "Similarities Detected!");
		Report report2 =  new Report(SOURCE, TARGET, 90, "Similarities Detected!");
		Report [] arr = {report, report2, report1};
		Report [] expectedArr = {report, report1, report2};
		Arrays.sort(arr);
		
		assertArrayEquals(expectedArr, arr);
		
		
	}
	
	
	
	
	

}
