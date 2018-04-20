package com.phasec.plagsafe.util;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import util.SnippetUtility;

public class SnippetUtilityTests {
	
	private SnippetUtility instance;
	
	@Before
	public void setUp(){
		instance = SnippetUtility.createInstance();
	}
	
	@Test
	public void testFindSnippetRanges(){
		
		List<Integer> obtainedResult = SnippetUtility.findSnippetRanges("Code1", "Code2");
		assertNotNull(obtainedResult);
		
	}

}
