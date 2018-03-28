package com.phasec.plagsafe;

import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;


public class StorageServiceTests {

	StorageService service;



	@Before
	public void setUp() {
		// mockService = mock(StorageService.class);
		service = new StorageService();
	}



	@Test
	public void testInitMethod() {

		service.init();

	}



	@Test
	public void testDeleteAll() {
		service.deleteAll();
	}



	@Test
	public void testStore() {

		MockMultipartFile firstFile = new MockMultipartFile("uploadfile1", "sample1.py", "text/plain",
				"some xml".getBytes());
		service.store(firstFile);
	}



	@Test
	public void testLoadFile() throws FileNotFoundException, MalformedURLException {

		Resource res = service.loadFile("wrong_fileName");
		assertNull(res);
	}



	@Test
	public void testGetFile() throws FileNotFoundException, MalformedURLException {

		File obtainedFile = service.getFile("wrong_fileName");
		assertNull(obtainedFile);
	}

}
