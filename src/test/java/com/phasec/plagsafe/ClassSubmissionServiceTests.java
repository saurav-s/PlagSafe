package com.phasec.plagsafe;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.phasec.plagsafe.objects.FileRecord;
import com.phasec.plagsafe.objects.Report;


/**
 * @author sanketsaurav
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlagsafeApplication.class)
public class ClassSubmissionServiceTests {

	@Autowired
	ClassSubmissionService submissionService;

	@MockBean
	ComparisonService comparisonService;



	/**
	 * Test for initialize and compare
	 */
	@Test
	public void testInitializeAndCompare() {
		Report report = new Report("test/team101/simple.py", "test/team102/simple.py", 100, "test_Remarks");
		List<Report> reports = new ArrayList<>();
		reports.add(report);
		when(comparisonService.runComparisionForFiles(anyListOf(FileRecord.class), eq(StrategyType.ALL)))
				.thenReturn(reports);
		List<String> paths = new ArrayList<>();
		paths.add("test/team101/simple.py");
		paths.add("test/team102/simple.py");

		MockMultipartFile firstFile = new MockMultipartFile("uploadfile1", "simple.py", "text/plain",
				"some xml".getBytes());
		MockMultipartFile secondFile = new MockMultipartFile("uploadfile2", "simple.py", " text/plain",
				"some other type".getBytes());
		MultipartFile[] submissions = new MultipartFile[2];
		submissions[0] = firstFile;
		submissions[1] = secondFile;
		String strategy = "ALL";
		String responseMessage = submissionService.initializeAndCompare(submissions, paths, strategy);
		assertEquals(
				"[{\"sourceFile\":\"test/team101/simple.py\",\"targetFile\":\"test/team102/simple.py\",\"matchPercentage\":100,\"matchRemark\":\"test_Remarks\"}]",
				responseMessage);
	}



	/**
	 * Test for initialize and compare fail
	 */
	@Test
	public void testInitializeAndCompareFail() {

		when(comparisonService.runComparisionForFiles(anyListOf(FileRecord.class), eq(StrategyType.ALL)))
				.thenThrow(new RuntimeException());
		List<String> paths = new ArrayList<>();
		paths.add("test/team101/simple.py");
		paths.add("test/team102/simple.py");

		MockMultipartFile firstFile = new MockMultipartFile("uploadfile1", "simple.py", "text/plain",
				"some xml".getBytes());
		MockMultipartFile secondFile = new MockMultipartFile("uploadfile2", "simple.py", " text/plain",
				"some other type".getBytes());
		MultipartFile[] submissions = new MultipartFile[2];
		submissions[0] = firstFile;
		submissions[1] = secondFile;
		String strategy = "ALL";
		String responseMessage = submissionService.initializeAndCompare(submissions, paths, strategy);
		assertEquals("\"Error occurred while uploading the file \"", responseMessage);
	}



	/**
	 * Test for reformat File names
	 */
	@Test
	public void test_reformatFileNames() {
		List<Report> mockReportList = new ArrayList<>();
		Report report1 = new Report("Today is a good day", "Today is Firday", 92, "Similarities detected");
		mockReportList.add(report1);
		submissionService.reformatFilenames(mockReportList);

	}

}
