package com.phasec.plagsafe;

import java.io.File;
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

@Service
public class StorageService {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	private final Path rootLocation = Paths.get("upload-dir");

	/**
	 * 
	 * @param file
	 */
	public void store(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			log.error("StorageService.store() -> error while storing file. " + e.getMessage());
		}
	}

	/**
	 * 
	 * @param filename
	 * @return
	 */
	public Resource loadFile(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				log.error("StorageService.store() -> error while loading file. ");
				return null;
			}
		} catch (MalformedURLException e) {
			log.error("StorageService.store() -> MalformedURL error while storing file. " + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @param filename
	 * @return
	 */
	public File getFile(String filename) {
		try {
			Path path = rootLocation.resolve(filename);
			Resource resource = new UrlResource(path.toUri());
			if (resource.exists() || resource.isReadable()) {
				return path.toFile();
			} else {
				log.error("StorageService.store() -> error while loading file. ");
				return null;
			}
		} catch (MalformedURLException e) {
			log.error("StorageService.store() -> MalformedURL error while storing file. " + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 */
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	/**
	 * 
	 */
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			log.error("StorageService.store() -> IOException error while init." + e.getMessage());
		}
	}
}