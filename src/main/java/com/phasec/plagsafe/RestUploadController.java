package com.phasec.plagsafe;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
import com.phasec.plagsafe.objects.MultipartRecord;
import com.phasec.plagsafe.objects.Report;

 
@RestController
@RequestMapping("/api")
public class RestUploadController {
 
	@Autowired
	StorageService storageService;
	
	@Autowired
	ComparisonService comparisonService;
	
	private List<String> files = new ArrayList<>();
	private static Logger logger = LoggerFactory.getLogger(RestUploadController.class);
 
    /**
     * 
     * @param fileList1
     * @param fileList2
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadfile")
    public String uploadFileMulti(@RequestParam("uploadfile1") MultipartFile[] fileList1,@RequestParam("uploadfile2") MultipartFile[] fileList2)  {
    		try {
    			List<MultipartFile> multipartFiles1 = new ArrayList<>();
    			for(MultipartFile file: fileList1) {
				storageService.store(file);
				files.add(file.getOriginalFilename());
				multipartFiles1.add(file);
			}
    			
    			List<MultipartFile> multipartFiles2 = new ArrayList<>();
    			for(MultipartFile file: fileList2) {
    				storageService.store(file);
    				files.add(file.getOriginalFilename());
    				multipartFiles2.add(file);
    			}
    			
    			List<Report> reports = runComparison(multipartFiles1, multipartFiles2);
			return getJsonString(reports);
		} catch (Exception e) {
			logger.error("Error occured while uploading the files"+e.getMessage());
			return getJsonString("Error occured while uploading the files");
			
		}
    }

	/**
	 * @param multipartFiles1
	 * @param multipartFiles2
	 */
	private List<Report> runComparison(List<MultipartFile> multipartFiles1, List<MultipartFile> multipartFiles2) {
		MultipartRecord record1 = new MultipartRecord();
		MultipartRecord record2 = new MultipartRecord();
		record1.setMultiparts(multipartFiles1);
		record2.setMultiparts(multipartFiles2);
		List<MultipartRecord> multipartRecords = new ArrayList<>();
		multipartRecords.add(record1);
		multipartRecords.add(record2);
		return comparisonService.runComparision(multipartRecords);
	}
    
    /**
     * 
     * @return all files uri
     */
	@GetMapping("/getallfiles")
	public List<String> getListFiles() {
		List<String> lstFiles = new ArrayList<>();
		try{
			lstFiles = files.stream()
					.map(fileName -> MvcUriComponentsBuilder
							.fromMethodName(RestUploadController.class, "getFile", fileName).build().toString())
					.collect(Collectors.toList());	
		}catch(Exception e){
			logger.error("Error occured while getting the files: "+e.getMessage());
			throw e;
		}
		
		return lstFiles;
	}
	
	/**
	 * 
	 * @param filename
	 * @return
	 */
	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	private String getJsonString(String str) {
		Gson gson = new Gson();
		return gson.toJson(str);
	}
	
	/**
	 * 
	 * @param reports
	 * @return
	 */
	private String getJsonString(List<Report> reports) {
		Gson gson = new Gson();
		return gson.toJson(reports);
	}
}