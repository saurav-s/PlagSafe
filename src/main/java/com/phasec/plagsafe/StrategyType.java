package com.phasec.plagsafe;

/**
 * 
 * @author sanketsaurav
 *
 */
public enum StrategyType {
	
	LOGICAL("logical"),
	REFACTORING("refactoring"),
	RENAMING("renaming"),
	ALL("all");

	private String value;

	private StrategyType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
	
}
