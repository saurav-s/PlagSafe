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
 
 
@RestController
@RequestMapping("/api")
public class RestUploadController {
 
	@Autowired
	StorageService storageService;
	
	List<String> files = new ArrayList<>();
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
 
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
    			for(MultipartFile file: fileList1) {
				storageService.store(file);
				files.add(file.getOriginalFilename());
			}
    			for(MultipartFile file: fileList2) {
    				storageService.store(file);
    				files.add(file.getOriginalFilename());
    				
    			}
			return getJsonString("You successfully uploaded all the files.");
		} catch (Exception e) {
			log.error("Error occured while uploading the files");
			return getJsonString("Error occured while uploading the files");
			
		}
    }
    
	@GetMapping("/getallfiles")
	public List<String> getListFiles() {
		List<String> lstFiles = new ArrayList<>();
		try{
			lstFiles = files.stream()
					.map(fileName -> MvcUriComponentsBuilder
							.fromMethodName(RestUploadController.class, "getFile", fileName).build().toString())
					.collect(Collectors.toList());	
		}catch(Exception e){
			log.error("Error occured while getting the files: "+e.getMessage());
			throw e;
		}
		
		return lstFiles;
	}
 
	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	private String getJsonString(String str) {
		Gson gson = new Gson();
		return gson.toJson(str);
	}
}