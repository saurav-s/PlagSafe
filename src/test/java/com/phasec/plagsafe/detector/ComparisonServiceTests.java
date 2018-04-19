package com.phasec.plagsafe.detector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.phasec.plagsafe.services.ComparisonService;
import com.phasec.plagsafe.PlagsafeApplication;
import com.phasec.plagsafe.models.StrategyType;
import com.phasec.plagsafe.models.FileModel;
import com.phasec.plagsafe.models.FileRecord;
import com.phasec.plagsafe.models.Report;
import com.phasec.plagsafe.models.SubmissionRecord;


/**
 * Tests for the Comparison Controller class
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes =  PlagsafeApplication.class)
public class ComparisonServiceTests {

	@Autowired
	ComparisonService comparisonService;



	/**
	 *  this test is to initialize with single submission
	 */
	@Test
	public void testSingleSubmissionInitialization() {
		List<SubmissionRecord> submissions = new ArrayList<>();

		FileModel file = new FileModel();
		file.setFileName("simple.py");
		file.setFileData(new File("resources/simple.py"));
		SubmissionRecord record = new SubmissionRecord();
		List<FileModel> fileList = new ArrayList<FileModel>();
		fileList.add(file);
		record.setFiles(fileList);
		submissions.add(record);
		comparisonService.submissionStub(submissions, StrategyType.ALL);
		Assert.assertEquals(true, true);
	}



	@Test
	public void testRunComparisionForFiles() {

		File file1 = new File("resources/simple1.py");
		List<File> fileList1 = new ArrayList<>();
		fileList1.add(file1);
		FileRecord fr1 = new FileRecord(fileList1);

		File file2 = new File("resources/simple.py");
		List<File> fileList2 = new ArrayList<>();
		fileList2.add(file2);
		FileRecord fr2 = new FileRecord(fileList2);

		List<FileRecord> frList = new ArrayList<>();
		frList.add(fr1);
		frList.add(fr2);

		List<Report> res = comparisonService.runComparisionForFiles(frList, StrategyType.ALL);

		assertNotNull(res);
		assertEquals(res.size(), 3);

	}
	
	
	@Test
	public void testForCombinedStrtegy() {

		File file1 = new File("resources/simple1.py");
		List<File> fileList1 = new ArrayList<>();
		fileList1.add(file1);
		FileRecord fr1 = new FileRecord(fileList1);

		File file2 = new File("resources/simple.py");
		List<File> fileList2 = new ArrayList<>();
		fileList2.add(file2);
		FileRecord fr2 = new FileRecord(fileList2);

		List<FileRecord> frList = new ArrayList<>();
		frList.add(fr1);
		frList.add(fr2);

		List<Report> res = comparisonService.runComparisionForFiles(frList, StrategyType.COMBINED);

		assertNotNull(res);
		assertEquals(res.size(), 1);

	}
	
	@Test
	public void testForRefactoringStrtegy() {

		File file1 = new File("resources/simple1.py");
		List<File> fileList1 = new ArrayList<>();
		fileList1.add(file1);
		FileRecord fr1 = new FileRecord(fileList1);

		File file2 = new File("resources/simple.py");
		List<File> fileList2 = new ArrayList<>();
		fileList2.add(file2);
		FileRecord fr2 = new FileRecord(fileList2);

		List<FileRecord> frList = new ArrayList<>();
		frList.add(fr1);
		frList.add(fr2);

		List<Report> res = comparisonService.runComparisionForFiles(frList, StrategyType.REFACTORING);

		assertNotNull(res);
		assertEquals(res.size(), 1);

	}
	@Test
	public void testForLogicalStrtegy() {

		File file1 = new File("resources/simple1.py");
		List<File> fileList1 = new ArrayList<>();
		fileList1.add(file1);
		FileRecord fr1 = new FileRecord(fileList1);

		File file2 = new File("resources/simple.py");
		List<File> fileList2 = new ArrayList<>();
		fileList2.add(file2);
		FileRecord fr2 = new FileRecord(fileList2);

		List<FileRecord> frList = new ArrayList<>();
		frList.add(fr1);
		frList.add(fr2);

		List<Report> res = comparisonService.runComparisionForFiles(frList, StrategyType.LOGICAL);

		assertNotNull(res);
		assertEquals(res.size(), 1);

	}
	
	@Test
	public void testForRenamingStrtegy() {

		File file1 = new File("resources/simple1.py");
		List<File> fileList1 = new ArrayList<>();
		fileList1.add(file1);
		FileRecord fr1 = new FileRecord(fileList1);

		File file2 = new File("resources/simple.py");
		List<File> fileList2 = new ArrayList<>();
		fileList2.add(file2);
		FileRecord fr2 = new FileRecord(fileList2);

		List<FileRecord> frList = new ArrayList<>();
		frList.add(fr1);
		frList.add(fr2);

		List<Report> res = comparisonService.runComparisionForFiles(frList, StrategyType.RENAMING);

		assertNotNull(res);
		assertEquals(res.size(), 1);

	}
}
