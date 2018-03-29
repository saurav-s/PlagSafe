package com.phasec.plagsafe;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlagsafeApplication.class)
public class StorageServiceTests {

	@Autowired
	StorageService service;



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



	@Test(expected = FileNotFoundException.class)
	public void testLoadFile() throws FileNotFoundException, MalformedURLException {
		Resource res = service.loadFile("wrong_fileName");
	}



	@Test(expected = FileNotFoundException.class)
	public void testGetFile() throws FileNotFoundException, MalformedURLException {
		File obtainedFile = service.getFile("wrong_fileName");
	}

}
