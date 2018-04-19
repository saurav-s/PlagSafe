package com.phasec.plagsafe.controllers;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.phasec.plagsafe.services.ClassSubmissionService;
import com.phasec.plagsafe.services.ComparisonService;
import com.phasec.plagsafe.services.StorageService;
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

import util.DataFormatUtility;


/**
 * this class acts as controller for file uploads
 * 
 */

@RestController
@RequestMapping("/api")
public class FileUploadController {

	@Autowired
    StorageService storageService;

	@Autowired
    ComparisonService comparisonService;

	@Autowired
	ClassSubmissionService submissionService;

	private List<String> files = new ArrayList<>();
	private static Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Value("#{'${acceptable.file.types}'.split(',')}")
	private List<String> acceptableFiles;



	/**
	 * the Api to upload file,
	 * 
	 * @param fileList1 the first directory inside which all the files will be
	 *            uploaded
	 * @param fileList2 the second directory inside which all the files will be
	 *            uploaded
	 * @param strategy
	 * @return a JSON string containing List of reports models
	 */
	@PostMapping("/uploadfile")
	public String uploadFileMulti(@RequestParam("uploadFile1") MultipartFile[] fileList1,
                                  @RequestParam("submission1Paths") String[] path1List,
                                  @RequestParam("uploadFile2") MultipartFile[] fileList2,
                                  @RequestParam("submission2Paths") String[] path2List,
                                  @RequestParam("strategy") String strategy)
    {
		try {
		    return submissionService.makeClassSubmissionAndCompare(fileList1, path1List, fileList2, path2List, strategy);
		} catch (Exception e) {
			logger.error("Error occurred while uploading the files");
			submissionService.failureStatsUpdate();
            return DataFormatUtility.getJsonString("Error occurred while uploading the files");
		}
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
			lstFiles = files.stream().map(fileName -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                    "getFile", fileName).build().toString()).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error("Error occurred while getting the files: ");
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
			logger.error("Error fetching file");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}