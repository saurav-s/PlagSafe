package com.phasec.plagsafe.objects;

import java.io.File;
import java.util.List;

/**
 * Represents list of files
 * @author sanketsaurav
 *
 */
public class FileRecord {
	
	private List<File> files;
	
	public FileRecord(List<File> files){
		super();
		this.files = files;
	}

	/**
	 * default constructor
	 */
	public FileRecord() {
		super();
	}

	/**
	 * 
	 * @return list of files
	 */
	public List<File> getFiles() {
		return files;
	}

	/**
	 * 
	 * @param files
	 */
	public void setFiles(List<File> files) {
		this.files = files;
	}

	
}
