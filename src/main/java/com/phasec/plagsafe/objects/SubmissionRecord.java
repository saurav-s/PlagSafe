package com.phasec.plagsafe.objects;

import java.util.List;

/**
 * Represents list of files which are part of a single submission
 * @author sanketsaurav
 *
 */
public class SubmissionRecord {
	
	private List<FileModel> files;
	
	/**
	 * default constructor
	 */
	public SubmissionRecord() {
		super();
	}

	/**
	 * Parameterized constructor
	 * @param files
	 */
	public SubmissionRecord(List<FileModel> files) {
		super();
		this.files = files;
	}

	/**
	 * 
	 * @return list of file models which are part of submission records
	 */
	public List<FileModel> getFiles() {
		return files;
	}

	/**
	 * 
	 * @param files
	 */
	public void setFiles(List<FileModel> files) {
		this.files = files;
	}
	
}
