package com.phasec.plagsafe;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;

public class StorageServiceTests {
	
	StorageService service;
	
	@Before
	public void setUp(){
//		mockService = mock(StorageService.class);
		service = new StorageService();
	}
	
	@Test
	public void testInitMethod(){ 
		
		service.init();
		
		
	}
	
	@Test
	public void testDeleteAll(){
		service.deleteAll();
	}
	
	@Test
	public void testStore(){
		
		MockMultipartFile firstFile = new MockMultipartFile("uploadfile1", "sample1.py", "text/plain",
				"some xml".getBytes());
		service.store(firstFile);
	}
	
	@Test
	public void testLoadFile(){
		
		Resource res = service.loadFile("wrong_fileName");
		assertNull(res);
	}
	
	@Test
	public void testGetFile(){
		
		File obtainedFile = service.getFile("wrong_fileName");
		assertNull(obtainedFile);
	}

}
