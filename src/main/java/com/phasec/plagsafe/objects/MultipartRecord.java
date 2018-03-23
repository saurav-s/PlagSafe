package com.phasec.plagsafe.objects;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * Represents list of MultiPart files
 * @author sanketsaurav
 *
 */
public class MultipartRecord {
	
	private List<MultipartFile> multiparts;

	/**
	 * default constructor
	 */
	public MultipartRecord() {
		super();
	}
	
	/**
	 * Parameterized constructor
	 * @param multiparts
	 */
	public MultipartRecord(List<MultipartFile> multiparts) {
		super();
		this.multiparts = multiparts;
	}

	/**
	 * 
	 * @return
	 */
	public List<MultipartFile> getMultiparts() {
		return multiparts;
	}

	/**
	 * 
	 * @param multiparts
	 */
	public void setMultiparts(List<MultipartFile> multiparts) {
		this.multiparts = multiparts;
	}
	
}
