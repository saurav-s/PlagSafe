package com.phasec.plagsafe;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * this class is for testing all functions in the StorageServiceImpl
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlagsafeApplication.class)
public class StorageServiceTests {

	@Autowired
	StorageService service;


	/**
	 * test the init function in StorageServiceImpl
	 */
	@Test
	public void testInitMethod() {

		service.init();

	}

	/**
	 * test the deleteAll function in StorageServiceImpl.
	 * folder 'upload-dir' is successfully deleted under target
	 */

	@Test
	public void testDeleteAll() {
		MockMultipartFile firstFile = new MockMultipartFile("uploadfile0", "sample1.py", "text/plain",
				"some xml".getBytes());
		service.store(firstFile);
		service.deleteAll();
	}


	/**
	 * test the store files function in StorageServiceImpl.
	 * folder 'upload-dir' is successfully deleted under target with specific files created
	 */
	@Test
	public void testStore() {

		MockMultipartFile firstFile = new MockMultipartFile("uploadfile1", "sample1.py", "text/plain",
				"some xml".getBytes());
		service.store(firstFile);

		MockMultipartFile secondFile = new MockMultipartFile("uploadfile2", "randomFile.py", "text/plain",
				"some xml".getBytes());
		service.store(secondFile);

	}


	/**
	 * test the loadfiles function in StorageServiceImpl.
	 * if no such file, throw FileNotFoundException exception
	 * @throws FileNotFoundException
	 * @throws MalformedURLException
	 */
	@Test(expected = FileNotFoundException.class)
	public void testLoadFile() throws FileNotFoundException, MalformedURLException {
		Resource res = service.loadFile("wrong_fileName");

		MockMultipartFile firstFile = new MockMultipartFile("uploadfile0", "sample1.py", "text/plain",
				"some xml".getBytes());
		Resource res1 = service.loadFile("uploadfile0");
		assertEquals(true, res1.exists());

	}


	/**
	 * test the getFile function in StorageServiceImpl.
	 * if file is stored under 'upload-dir', return the file object , else,  return FileNotFoundException
	 * @throws FileNotFoundException
	 * @throws MalformedURLException
	 */
	@Test(expected = FileNotFoundException.class)
	public void testGetFile() throws FileNotFoundException, MalformedURLException {
		File obtainedFile = service.getFile("wrong_fileName");
		MockMultipartFile firstFile = new MockMultipartFile("uploadfile1", "sample1.py", "text/plain",
				"some xml".getBytes());
		service.store(firstFile);
		File obtainedFile1 = service.getFile("sample1.py");
		assertEquals("sample1.py",obtainedFile1.getName());
	}

}
