package com.phasec.plagsafe;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.phasec.plagsafe.objects.Report;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlagsafeApplication.class)
public class ClassSubmissionServiceTests {

	@Autowired
	ClassSubmissionService submissionService;
	
	
   // public String initializeAndCompare(MultipartFile[] submissions, List<String> paths, String strategy)
	@Test
	public void testInitializeAndCompare() {
		List<String> paths = new ArrayList<>();
		paths.add( "test/team101/simple.py");
		paths.add( "test/team102/simple.py");
		
		
		MockMultipartFile firstFile = new MockMultipartFile("uploadfile1", "simple.py", "text/plain",
				"some xml".getBytes());
		MockMultipartFile secondFile = new MockMultipartFile("uploadfile2", "simple.py"," text/plain",
				"some other type".getBytes());
		MultipartFile[] submissions = new MultipartFile[2];
		submissions[0] = firstFile;
		submissions[1] = secondFile;
		String strategy = "ALL";
		String initializeAndCompare = submissionService.initializeAndCompare(submissions, paths, strategy );
	}
	
	@Test
	public void test_reformatFileNames(){
		List<Report> mockReportList = new ArrayList<>();
		Report report1 = new Report("Today is a good day", "Today is Firday", 92, "Similarities detected");
		mockReportList.add(report1);
		submissionService.reformatFilenames(mockReportList);
		
		
	}
	
//	@Test
//	public void test_makeRecordFiles(){
//		
//		List<String> mockSubmissionFile = new ArrayList<>();
//		
//		
//	}

}
