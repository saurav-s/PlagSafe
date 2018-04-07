package com.phasec.plagsafe.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import util.LevenshteinDistanceGeneratorUtility;
import util.NGramGeneratorUtility;
import util.WeightPropertyReader;

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
	
	@Test
	public void test_WeightPropertyReader_setRenaming_weight(){
		WeightPropertyReader object = WeightPropertyReader.makeReaderObject();
		object.setRenaming_weight(13);
		
		int obtainedRenamingWeight = object.getRenaming_weight();
		assertEquals(obtainedRenamingWeight, 13);
		
	}
	
	@Test
	public void test_WeightPropertyReader_setLogical_weight(){
		WeightPropertyReader object = WeightPropertyReader.makeReaderObject();
		object.setLogical_weight(33);
		
		int obtainedLogicalWeight = object.getLogical_weight();
		assertEquals(obtainedLogicalWeight, 33);
		
	}
	
	@Test
	public void test_WeightPropertyReader_setRefactoring_weight(){
		WeightPropertyReader object = WeightPropertyReader.makeReaderObject();
		object.setRefactoring_weight(23);
		
		int obtainedLogicalWeight = object.getRefactoring_weight();
		assertEquals(obtainedLogicalWeight, 23);
		
	}
	

}
