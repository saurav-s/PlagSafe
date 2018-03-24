package com.phasec.plagsafe.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import util.LevenshteinDistanceGeneratorUtility;
import util.NGramGeneratorUtility;

public class UtilTest {
	

	
	@Test
	public void nGramGeneratorUtilityTest(){
		
		NGramGeneratorUtility object = new NGramGeneratorUtility();
		List<String> res = object.getNGramList("Northeastern University", 3);
		assertNotNull(res);
		
	}
	
	@Test
	public void levenshteinDistanceGeneratorUtilityTest(){
		
		LevenshteinDistanceGeneratorUtility object = new LevenshteinDistanceGeneratorUtility();
		int lDistance = object.getLevenshteinDistance("pale", "bale");
		assertEquals(1, lDistance);
		
	}
	

}
