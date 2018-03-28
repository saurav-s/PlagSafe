package com.phasec.plagsafe;


import java.io.File;
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
import com.phasec.plagsafe.objects.FileRecord;
import com.phasec.plagsafe.objects.Report;

/**
 * this class is for Rest control
 */

@RestController
@RequestMapping("/api")
public class RestUploadController {
 
	@Autowired
	StorageService storageService;
	
	@Autowired
	ComparisonService comparisonService;
	
	private List<String> files = new ArrayList<>();
	private static Logger logger = LoggerFactory.getLogger(RestUploadController.class);
	private static final String ACCEPTABLE_FILE_TYPE = ".py";
 
    /**
     * the Api to upload file
     * @param fileList1 the first file uploaded
     * @param fileList2 the second file uploaded
     * @return if the file is uploaded successfully or not
		 * @throws Exception
     */
    @PostMapping("/uploadfile")
    public String uploadFileMulti(@RequestParam("uploadfile1") MultipartFile[] fileList1,
								  @RequestParam("uploadfile2") MultipartFile[] fileList2,
								  @RequestParam("strategy") String strategy)  {
    		try {

    			List<String> fileNames1 = storeFiles(fileList1);
    			List<String> fileNames2 = storeFiles(fileList2);

    			StrategyType comparisonStrategy = StrategyType.valueOf(strategy);
    			List<Report> runComparisionForFiles = runComparison(fileNames1, fileNames2, comparisonStrategy);
    			return getJsonString(runComparisionForFiles);
			} catch (Exception e) {
				logger.error("Error occurred while uploading the files"+e.getMessage());
				return getJsonString("Error occurred while uploading the files");
			
			}
    }

    /**
     * stores only stores the
     * @param receivedFiles
     * @return
     */
    private List<String> storeFiles(MultipartFile[] receivedFiles) {
        List<String> fileNamesList = new ArrayList<>();

        for(MultipartFile file : receivedFiles) {
            if(file.getOriginalFilename().endsWith(ACCEPTABLE_FILE_TYPE)) {
                storageService.store(file);
                files.add(file.getOriginalFilename());
                fileNamesList.add(file.getOriginalFilename());
            }
        }
        return fileNamesList;
    }

	/**
	 * deploy the comparison method
	 * @param fileNames1  the first file uploaded
	 * @param fileNames2  the second file uploaded
	 * @param comparisonStrategy 
	 * @return a list of reports
	 */
	private List<Report> runComparison(List<String> fileNames1, List<String> fileNames2, StrategyType comparisonStrategy) {
		List<FileRecord> filesList = new ArrayList<>();
		List<File> fileList1 = new ArrayList<>();
		List<File> fileList2 = new ArrayList<>();
		FileRecord files1 = new FileRecord();
		FileRecord files2 = new FileRecord();
		for(String fileName: fileNames1) {
			File file = storageService.getFile(fileName);
			fileList1.add(file);
		}
		files1.setFiles(fileList1);
		
		for(String fileName: fileNames2) {
			File file = storageService.getFile(fileName);
			fileList2.add(file);
		}
		files2.setFiles(fileList2);
		filesList.add(files1);
		filesList.add(files2);
		return comparisonService.runComparisionForFiles(filesList, comparisonStrategy);
	}

    
    /**
     * get all the files that have been uploaded
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
			logger.error("Error occured while getting the files: " + e.getMessage());
			throw e;
		}
		
		return lstFiles;
	}
	
	/**
	 * get the file in the form of Resource object
	 * @param filename the name of the submitted file
	 * @return Resource object
	 */
	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	/**
	 * get the Json format of files
	 * @param str
	 * @return Json as a string
	 */
	private String getJsonString(String str) {
		Gson gson = new Gson();
		return gson.toJson(str);
	}
	
	/**
	 * get the Json format of reports
	 * @param reports reports object
	 * @return Json as a string
	 */
	private String getJsonString(List<Report> reports) {
		Gson gson = new Gson();
		return gson.toJson(reports);
	}
}