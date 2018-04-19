package com.phasec.plagsafe.models;

/**
 * 
 * @author sanketsaurav
 *
 */
public enum StrategyType {
	
	LOGICAL("logical"),
	REFACTORING("refactoring"),
	RENAMING("renaming"),
	ALL("all"),
	COMBINED("combined");

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
