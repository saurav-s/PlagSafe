package com.phasec.plagsafe;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.google.gson.Gson;
import com.phasec.plagsafe.objects.FileRecord;
import com.phasec.plagsafe.objects.Report;


/**
 * this class acts as controller for file uploads
 */

@RestController
@RequestMapping("/api")
public class FileUploadController {

	@Autowired
	StorageService storageService;

	@Autowired
	ComparisonService comparisonService;

	private List<String> files = new ArrayList<>();
	private static Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Value("#{'${list.of.acceptable.file.type}'.split(',')}")
	private List<String> acceptableFiles;



	/**
	 * the Api to upload file,
	 * 
	 * @param fileList1 the first directory inside which all the files will be
	 *            uploaded
	 * @param fileList2 the second directory inside which all the files will be
	 *            uploaded
	 * @param strategy
	 * @return a JSON string containing List of reports objects
	 */
	@PostMapping("/uploadfile")
	public String uploadFileMulti(@RequestParam("uploadfile1") MultipartFile[] fileList1,
			@RequestParam("uploadfile2") MultipartFile[] fileList2, @RequestParam("strategy") String strategy) {
		try {

			List<String> fileNames1 = storeFiles(fileList1);
			List<String> fileNames2 = storeFiles(fileList2);

			StrategyType comparisonStrategy = StrategyType.valueOf(strategy);
			List<Report> runComparisionForFiles = runComparison(fileNames1, fileNames2, comparisonStrategy);
			return getJsonString(runComparisionForFiles);
		} catch (Exception e) {
			logger.error("Error occurred while uploading the files" + e.getMessage());
			return getJsonString("Error occurred while uploading the files");
		}
	}



	/**
	 * stores the acceptable multipart files and returns list of File names which
	 * were stored.
	 * 
	 * @param receivedFiles
	 * @return list of File names which were stored
	 */
	private List<String> storeFiles(MultipartFile[] receivedFiles) {
		List<String> fileNamesList = new ArrayList<>();

		for (MultipartFile file : receivedFiles) {
			if (isAccpetableFile(file)) {
				storageService.store(file);
				addToFileUploadList(file);
				fileNamesList.add(file.getOriginalFilename());
			}
		}
		return fileNamesList;
	}



	/**
	 * check whether this file type belongs to acceptable list or not
	 * 
	 * @param file
	 * @return
	 */
	private boolean isAccpetableFile(MultipartFile file) {
		for (String acceptableFileType : acceptableFiles) {
			if (file.getOriginalFilename().endsWith(acceptableFileType))
				return true;
		}
		return false;
	}



	/**
	 * Add the file to files uploaded list
	 * 
	 * @param file
	 */
	private void addToFileUploadList(MultipartFile file) {
		if (!files.contains(file.getOriginalFilename())) {
			files.add(file.getOriginalFilename());
		}
	}



	/**
	 * create a list of FileRecords and deploy the comparison method
	 * 
	 * @param fileNames1 the first file uploaded
	 * @param fileNames2 the second file uploaded
	 * @param comparisonStrategy
	 * @return a list of reports
	 * @throws MalformedURLException
	 * @throws FileNotFoundException
	 */
	private List<Report> runComparison(List<String> fileNames1, List<String> fileNames2,
			StrategyType comparisonStrategy) throws FileNotFoundException, MalformedURLException {
		List<FileRecord> filesList = createFileRecordList(fileNames1, fileNames2);
		return comparisonService.runComparisionForFiles(filesList, comparisonStrategy);
	}



	/**
	 * Create a List of FileRecord object from the two list of filenames
	 * 
	 * @param fileNames1
	 * @param fileNames2
	 * @return List of FileRecord object
	 * @throws MalformedURLException
	 * @throws FileNotFoundException
	 */
	private List<FileRecord> createFileRecordList(List<String> fileNames1, List<String> fileNames2)
			throws FileNotFoundException, MalformedURLException {
		List<FileRecord> filesList = new ArrayList<>();
		List<File> fileList1 = new ArrayList<>();
		List<File> fileList2 = new ArrayList<>();
		FileRecord files1 = createFileRecord(fileNames1, fileList1);
		FileRecord files2 = createFileRecord(fileNames2, fileList2);
		filesList.add(files1);
		filesList.add(files2);
		return filesList;
	}



	/**
	 * Create a FileRecord object from the two list of filenames
	 * 
	 * @param fileNames1
	 * @param fileList1
	 * @return FileRecord object
	 * @throws FileNotFoundException
	 * @throws MalformedURLException
	 */
	private FileRecord createFileRecord(List<String> fileNames1, List<File> fileList1)
			throws FileNotFoundException, MalformedURLException {
		FileRecord files1 = new FileRecord();

		for (String fileName : fileNames1) {
			File file = storageService.getFile(fileName);
			fileList1.add(file);
		}
		files1.setFiles(fileList1);
		return files1;
	}



	/**
	 * get all the files that have been uploaded
	 * 
	 * @return all files uri
	 */
	@GetMapping("/getallfiles")
	public List<String> getListFiles() {
		List<String> lstFiles = new ArrayList<>();
		try {
			lstFiles = files.stream()
					.map(fileName -> MvcUriComponentsBuilder
							.fromMethodName(FileUploadController.class, "getFile", fileName).build().toString())
					.collect(Collectors.toList());
		} catch (Exception e) {
			logger.error("Error occured while getting the files: " + e.getMessage());
			throw e;
		}

		return lstFiles;
	}



	/**
	 * get the file in the form of Resource object. User can download files through
	 * this URI
	 * 
	 * @param filename the name of the submitted file
	 * @return Resource URI of the file
	 */
	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		try {
			Resource file = storageService.loadFile(filename);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.body(file);
		} catch (FileNotFoundException | MalformedURLException e) {
			logger.error("Error fecthing file");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}



	/**
	 * get the JSON format of string
	 * 
	 * @param str string to be converted
	 * @return JSON string value
	 */
	private String getJsonString(String str) {
		Gson gson = new Gson();
		return gson.toJson(str);
	}



	/**
	 * get the JSON format of reports
	 * 
	 * @param reports
	 * @return JSON string representing list of Reports
	 */
	private String getJsonString(List<Report> reports) {
		Gson gson = new Gson();
		return gson.toJson(reports);
	}
}