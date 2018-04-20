package com.phasec.plagsafe.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


/**
 * Storage service interface provides a mechanism for file handling
 * 
 * @author sanketsaurav
 *
 */
public interface StorageService {

	/**
	 * store all the files to the local storage
	 * 
	 * @param file
	 */
	void store(MultipartFile file);

	void store(MultipartFile file, String path);

	/**
	 * get the file object from local file storage
	 * 
	 * @param filename filename the name of the loaded files
	 * @return the file object
	 * @throws FileNotFoundException
	 * @throws MalformedURLException
	 */
	File getFile(String filename) throws FileNotFoundException, MalformedURLException;



	/**
	 * load all the files to the service
	 * 
	 * @param filename filename the name of the loaded files
	 * @return the loaded files as resource object
	 * @throws FileNotFoundException
	 * @throws MalformedURLException
	 */
	Resource loadFile(String filename) throws FileNotFoundException, MalformedURLException;



	/**
	 * delete all the files in the store services
	 */
	void deleteAll();



	/**
	 * initialize the store service
	 */
	void init();
}