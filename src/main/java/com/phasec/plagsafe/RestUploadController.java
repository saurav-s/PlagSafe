package com.phasec.plagsafe;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	List<String> files = new ArrayList<String>();
 
    // Multiple file upload
    @PostMapping("/uploadfile")
    public String uploadFileMulti(@RequestParam("uploadfile") MultipartFile file) throws Exception {
    	try {
    			System.out.println("Inside RestUploadController.uploadFileMulti()");
			storageService.store(file);
			files.add(file.getOriginalFilename());
			String response = "You successfully uploaded - " + file.getOriginalFilename();
			Gson gson = new Gson();
			String jsonResponse = gson.toJson(response);
			return jsonResponse;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("FAIL! Maybe You had uploaded the file before or the file's size > 500KB");
		}
    }
    
	@GetMapping("/getallfiles")
	public List<String> getListFiles() {List<String> lstFiles = new ArrayList<String>();
		try{
			lstFiles = files.stream()
					.map(fileName -> MvcUriComponentsBuilder
							.fromMethodName(RestUploadController.class, "getFile", fileName).build().toString())
					.collect(Collectors.toList());	
		}catch(Exception e){
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
}