package com.phasec.plagsafe.objects;


public class Ngram implements Comparable<Ngram> {
	private String ngramValue;
	private int startIndex;
	private int endIndex;
	
	public String getNgramValue() {
		return ngramValue;
	}
	
	public void setNgramValue(String ngramValue) {
		this.ngramValue = ngramValue;
	}
	
	public int getStartIndex() {
		return startIndex;
	}
	
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	public int getEndIndex() {
		return endIndex;
	}
	
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	@Override
	public int compareTo(Ngram o) {
		return  this.ngramValue.compareTo(o.ngramValue);
	}

	@Override
	public String toString() {
		return "Ngram [ngramValue=" + ngramValue + ", startIndex=" + startIndex + ", endIndex=" + endIndex + "]";
	}
	
	
	
}
