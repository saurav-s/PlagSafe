package com.phasec.plagsafe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


/**
 * A Service class used to store and retrieve files
 */

@Service
public class StorageServiceImpl implements StorageService {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	// root location where files will be stored on the system
	private final Path rootLocation = Paths.get("upload-dir");



	/**
	 * store all the files to the local storage
	 * 
	 * @param file
	 */
	@Override
	public void store(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			log.error("StorageService.store() -> error while storing file. " + e.getMessage());
		}
	}

	@Override
	public void store(MultipartFile file, String path) {
		try {
		    path = path.replaceAll("/","-");
		    Files.copy(file.getInputStream(), this.rootLocation.resolve(path));
        } catch(Exception e) {
		    log.error("StorageService.store() -> error while storing file. " + e.getMessage());
        }
	}


	/**
	 * get the file object from local file storage
	 * 
	 * @param filename filename the name of the loaded files
	 * @return the file object
	 * @throws FileNotFoundException
	 * @throws MalformedURLException
	 */
	@Override
	public File getFile(String filename) throws FileNotFoundException, MalformedURLException {
		try {
			Path path = rootLocation.resolve(filename);
			Resource resource = new UrlResource(path.toUri());
			validateResource(resource);
			return path.toFile();
		} catch (MalformedURLException e) {
			log.error("StorageService.getFile() -> MalformedURL error while getting file. " + e.getMessage());
			throw e;
		}
	}



	/**
	 * validate the existence of the resource
	 * 
	 * @param resource
	 * @throws FileNotFoundException
	 */
	private void validateResource(Resource resource) throws FileNotFoundException {
		if (!resource.exists() && !resource.isReadable()) {
			log.error("StorageService.validateResource() -> error while validating file resource. ");
			throw new FileNotFoundException(
					"Error validating file resource, File does not exists or it is not readable");
		}
	}



	/**
	 * load all the files to the service
	 * 
	 * @param filename filename the name of the loaded files
	 * @return the loaded files as resource object
	 * @throws FileNotFoundException
	 * @throws MalformedURLException
	 */
	@Override
	public Resource loadFile(String filename) throws FileNotFoundException, MalformedURLException {
		log.info("Loading file...");
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			validateResource(resource);
			return resource;
		} catch (MalformedURLException e) {
			log.error("StorageService.loadfile() -> MalformedURL error while storing file. " + e.getMessage());
			throw e;
		}
	}



	/**
	 * delete all the files in the store system
	 */
	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}



	/**
	 * initialize the store service
	 */
	@Override
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			log.error("StorageService.store() -> IOException error while init." + e.getMessage());
		}
	}
}