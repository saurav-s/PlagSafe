package com.phasec.plagsafe.models;

import java.io.File;
import java.util.List;

/**
 * Represents list of files
 * @author sanketsaurav
 *
 */
public class FileRecord {
	
	private List<File> files;

	/**
	 * default constructor
	 */
	public FileRecord() {
		super();
	}
	
	/**
	 * Parameterized constructor
	 * @param files the different parts in the files
	 */
	public FileRecord(List<File> files) {
		super();
		this.files = files;
	}

	/**
	 * get all the files
	 * @return list of files
	 */
	public List<File> getFiles() {
		return files;
	}

	/**
	 * set all the files
	 * @param files
	 */
	public void setFiles(List<File> files) {
		this.files = files;
	}

	public void addFile(File file) {
	    files.add(file);
    }
}
