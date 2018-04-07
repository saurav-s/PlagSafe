package com.phasec.plagsafe;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StrategyTypeEnumTests {
	
	@Test
	public void testMethodsOfEnum(){
		StrategyType type = StrategyType.ALL;
		String obtainedResult = type.getValue();
		assertEquals(obtainedResult, "all");
		
		String toString = type.toString();
		assertEquals(toString, "all");
	}

}
