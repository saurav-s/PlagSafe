package com.phasec.plagsafe.models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MatchSnippetTests {
	
	MatchSnippet instance;
	
	@Before
	public void setUp(){
		instance = new MatchSnippet("file1", "Code1", "file2", "Code2");
		
	}
	
	@Test
	public void testFileNameOne(){
		instance.setFileNameOne("NewFile1");
		String obtainedResult = instance.getFileNameOne();
		assertEquals("NewFile1", obtainedResult);
	}
	
	@Test
	public void testCodeOne(){
		instance.setCodeOne("NewCode1");
		String obtainedResult = instance.getCodeOne();
		assertEquals("NewCode1", obtainedResult);
	}
	
	@Test
	public void testFileNameTwo(){
		instance.setFileNameTwo("NewFile2");
		String obtainedResult = instance.getFileNameTwo();
		assertEquals("NewFile2", obtainedResult);
	}
	
	@Test
	public void testCodeTwo(){
		instance.setCodeTwo("NewCode2");
		String obtainedResult = instance.getCodeTwo();
		assertEquals("NewCode2", obtainedResult);
	}
	
	@Test
	public void testRangesForOne(){
		List<Integer> rangesForOne = new ArrayList<>();
		instance.setRangesForOne(rangesForOne);
		assertNotNull(instance.getRangesForOne());
	}
	
	@Test
	public void testRangesForTwo(){
		List<Integer> rangesFortwo = new ArrayList<>();
		instance.setRangesForTwo(rangesFortwo);
		assertNotNull(instance.getRangesForTwo());
	}
	
	@Test
	public void testIndexForCode(){
		instance.addIndexForCodeOne(3);
		instance.addIndexForCodeTwo(3);
	}
	

}
