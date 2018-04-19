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
		List<String> res = NGramGeneratorUtility.getNGramList("Northeastern University", 3);
		assertNotNull(res);
		
	}
	
	@Test
	public void levenshteinDistanceGeneratorUtilityTest(){
		int lDistance = LevenshteinDistanceGeneratorUtility.getLevenshteinDistance("pale", "bale");
		assertEquals(1, lDistance);
		
	}
	
	@Test
	public void test_WeightPropertyReader_setRenaming_weight(){
		WeightPropertyReader object = WeightPropertyReader.makeReaderObject();
		object.setRenamingWeight(13);
		
		int obtainedRenamingWeight = object.getRenamingWeight();
		assertEquals(obtainedRenamingWeight, 13);
		
	}
	
	@Test
	public void test_WeightPropertyReader_setLogical_weight(){
		WeightPropertyReader object = WeightPropertyReader.makeReaderObject();
		object.setLogicalWeight(33);
		
		int obtainedLogicalWeight = object.getLogicalWeight();
		assertEquals(obtainedLogicalWeight, 33);
		
	}
	
	@Test
	public void test_WeightPropertyReader_setRefactoring_weight(){
		WeightPropertyReader object = WeightPropertyReader.makeReaderObject();
		object.setRefactoringWeight(23);
		
		int obtainedLogicalWeight = object.getRefactoringWeight();
		assertEquals(obtainedLogicalWeight, 23);
		
	}
	

}
