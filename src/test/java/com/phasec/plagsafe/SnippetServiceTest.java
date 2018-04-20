package com.phasec.plagsafe;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import org.apache.derby.tools.sysinfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.phasec.plagsafe.models.MatchSnippet;
import com.phasec.plagsafe.services.SnippetService;
import com.phasec.plagsafe.services.StorageService;

/**
 * Test class for snippet generation
 * @author sanketsaurav
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlagsafeApplication.class)
public class SnippetServiceTest {

	@Autowired
	SnippetService service;
	
    @Autowired
    StorageService storageService;

    
    /**
     * 
     * @throws Exception
     */
	@Test
	public void testProcessSnippetSuccess() throws Exception {
		MockMultipartFile firstFile = new MockMultipartFile("uploadfile1", "sample1.py", "text/plain",
				"some xml".getBytes());
		storageService.store(firstFile);
		MockMultipartFile secondFile = new MockMultipartFile("uploadfile2", "sample2.py", "text/plain",
				"some xml".getBytes());
		storageService.store(firstFile);
		storageService.store(secondFile);
		//File obtainedFile1 = storageService.getFile("sample1.py");
		MatchSnippet processSnippet = service.processSnippet("sample1.py", "sample2.py");
		assertEquals("<span style=\"background-color: #40b4f7;\">some xml</span>", processSnippet.getCodeOne());
		assertEquals("<span style=\"background-color: #40b4f7;\">some xml</span>", processSnippet.getCodeTwo());
	}
	
	
    /**
     * 
     * @throws Exception
     */
	@Test
	public void testProcessSnippet() throws Exception {
		MockMultipartFile firstFile = new MockMultipartFile("uploadfile1", "sample11.py", "text/plain",
				"def a=some xml; y=10".getBytes());
		storageService.store(firstFile);
		MockMultipartFile secondFile = new MockMultipartFile("uploadfile2", "sample22.py", "text/plain",
				"def b=xml; x=10".getBytes());
		storageService.store(firstFile);
		storageService.store(secondFile);
		//File obtainedFile1 = storageService.getFile("sample1.py");
		MatchSnippet processSnippet = service.processSnippet("sample11.py", "sample22.py");
		System.out.println(processSnippet.getCodeOne());
		System.out.println(processSnippet.getCodeTwo());
		assertEquals("<span style=\"background-color: #40b4f7;\">def </span>a<span style=\"background-color: #40b4f7;\">=</span>some <span style=\"background-color: #40b4f7;\">xml; </span>y<span style=\"background-color: #40b4f7;\">=10</span>", processSnippet.getCodeOne());
		assertEquals("<span style=\"background-color: #40b4f7;\">def </span>b<span style=\"background-color: #40b4f7;\">=xml; </span>x<span style=\"background-color: #40b4f7;\">=10</span>", processSnippet.getCodeTwo());
	}

}
