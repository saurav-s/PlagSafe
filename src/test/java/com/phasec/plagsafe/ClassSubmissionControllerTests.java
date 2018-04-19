package com.phasec.plagsafe;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.phasec.plagsafe.controllers.ClassSubmissionController;
import com.phasec.plagsafe.services.ClassSubmissionService;
import com.phasec.plagsafe.services.StorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import com.phasec.plagsafe.models.Report;


/**
 * @author sanketsaurav
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ClassSubmissionController.class)
public class ClassSubmissionControllerTests {

	@MockBean
	ClassSubmissionService submissionService;
	
	@MockBean
    private StorageService storageService;
	
	@Autowired
	private MockMvc mvc;

	/**
	 * Test for initialize and compare
	 * @throws Exception 
	 */
	@Test
	public void testUploadClassSubmissions() throws Exception {
		Report report = new Report("test/team101/simple.py", "test/team102/simple.py", 100, "test_Remarks");
		List<Report> reports = new ArrayList<>();
		reports.add(report);

		String[] paths = new String[2];
		paths[0] = "test/team101/simple.py";
		paths[1] = "test/team102/simple.py";

		MockMultipartFile firstFile = new MockMultipartFile("uploadfile1", "simple.py", "text/plain",
				"some xml".getBytes());
		MockMultipartFile secondFile = new MockMultipartFile("uploadfile2", "simple.py", " text/plain",
				"some other type".getBytes());
		MultipartFile[] submissions = new MultipartFile[2];
		submissions[0] = firstFile;
		submissions[1] = secondFile;
		String strategy = "ALL";
		
		
		
		List<String> pathList = new ArrayList<>();
		pathList.add("test/team101/simple.py");
		pathList.add("test/team102/simple.py");
		
		when(submissionService.initializeAndCompare(submissions, pathList, strategy)).thenReturn("[{\"sourceFile\":\"test/team101/simple.py\",\"targetFile\":\"test/team102/simple.py\",\"matchPercentage\":100,\"matchRemark\":\"test_Remarks\"}]");
		mvc.perform(MockMvcRequestBuilders
				.fileUpload("/api/class/submissions")
				.file(firstFile).file(secondFile)
				.param("relativePaths", paths)
				.param("strategy", "ALL"))
			.andDo(print())
			.andExpect(status().isOk());
			//.andExpect(content().string("[{\"sourceFile\":\"test/team101/simple.py\",\"targetFile\":\"test/team102/simple.py\",\"matchPercentage\":100,\"matchRemark\":\"test_Remarks\"}]"));
		

	}

}
