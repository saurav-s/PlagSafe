package com.phasec.plagsafe.objects;

import java.util.List;

import com.phasec.plagsafe.detector.Submissible;

/**
 * Represents a list of submissibles
 * @author sanketsaurav
 *
 */
public class SubmissibleRecord {
	
	private List<Submissible> submissibles;
	
	public SubmissibleRecord() {
		super();
	}

	public SubmissibleRecord(List<Submissible> submissibles) {
		super();
		this.submissibles = submissibles;
	}

	/**
	 * 
	 * @return list of submissible
	 */
	public List<Submissible> getSubmissibles() {
		return submissibles;
	}

	/**
	 * 
	 * @param submissibles
	 */
	public void setSubmissibles(List<Submissible> submissibles) {
		this.submissibles = submissibles;
	}
	
	
}
