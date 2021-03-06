package com.phasec.plagsafe.detector;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.phasec.plagsafe.PlagsafeApplication;
import com.phasec.plagsafe.models.Report;
import com.phasec.plagsafe.models.SubmissibleRecord;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlagsafeApplication.class)
public class RefactoringDetectionStrategyTests {

	@Autowired
	@Qualifier("refactoringDetectionStrategy")
	private DetectionStrategy refactoringDetectionStrategy;



	@Test
	public void testCompleteRedundantInput() {
		
		refactoringDetectionStrategy.updateRequestCount();

		Submission student1sub1 = new Submission("File1", "Today is a great day", null);
		Submission student1sub2 = new Submission("File2", "It has been snowing continously", null);

		Submission student2sub1 = new Submission("File1", "Today is a great day", null);
		Submission student2sub2 = new Submission("File2", "It has been snowing continously", null);

		List<Submissible> student1List = new ArrayList<>();
		student1List.add(student1sub1);
		student1List.add(student1sub2);
		SubmissibleRecord sr1 = new SubmissibleRecord();
		sr1.setSubmissibles(student1List);

		List<Submissible> student2List = new ArrayList<>();
		student2List.add(student2sub1);
		student2List.add(student2sub2);
		SubmissibleRecord record2 = new SubmissibleRecord();
		record2.setSubmissibles(student2List);
		List<Report> obtainedReportList = refactoringDetectionStrategy.compare(sr1, record2);

		assertEquals(100, obtainedReportList.get(0).getMatchPercentage());
		assertEquals(2, obtainedReportList.get(1).getMatchPercentage());
		assertEquals(2, obtainedReportList.get(2).getMatchPercentage());
		assertEquals(100, obtainedReportList.get(3).getMatchPercentage());

	}



	@Test
	public void testPartialRedundantInput() {

		Submission student1sub1 = new Submission("File1", "The weather is sunny today", null);
		Submission student1sub2 = new Submission("File2", "The forecast says snow showers later on", null);

		Submission student2sub1 = new Submission("File1", "The weather is windy today", null);
		Submission student2sub2 = new Submission("File2", "The forecast says thunderstorms later on", null);

		List<Submissible> student1List = new ArrayList<>();
		student1List.add(student1sub1);
		student1List.add(student1sub2);
		SubmissibleRecord record1 = new SubmissibleRecord();
		record1.setSubmissibles(student1List);

		List<Submissible> student2List = new ArrayList<>();
		student2List.add(student2sub1);
		student2List.add(student2sub2);
		SubmissibleRecord record2 = new SubmissibleRecord();
		record2.setSubmissibles(student2List);

		List<Report> obtainedReportList = refactoringDetectionStrategy.compare(record1, record2);

		for (Report r : obtainedReportList) {
			assertEquals(57, obtainedReportList.get(0).getMatchPercentage());
			assertEquals(1, obtainedReportList.get(1).getMatchPercentage());
			assertEquals(1, obtainedReportList.get(2).getMatchPercentage());
			assertEquals(47, obtainedReportList.get(3).getMatchPercentage());
		}

	}



	@Test
	public void testTotallyDifferentInput() {

		Submission student1sub1 = new Submission("File1", "I am experiencing a great journey", null);
		Submission student1sub2 = new Submission("File2", "The weather is amazing today", null);

		Submission student2sub1 = new Submission("File1", "Why are you late?", null);
		Submission student2sub2 = new Submission("File2", "You should have come half an hour early", null);

		List<Submissible> student1List = new ArrayList<>();
		student1List.add(student1sub1);
		student1List.add(student1sub2);
		SubmissibleRecord record1 = new SubmissibleRecord();
		record1.setSubmissibles(student1List);

		List<Submissible> student2List = new ArrayList<>();
		student2List.add(student2sub1);
		student2List.add(student2sub2);
		SubmissibleRecord record2 = new SubmissibleRecord();
		record2.setSubmissibles(student2List);

		List<Report> obtainedReportList = refactoringDetectionStrategy.compare(record1, record2);

		for (Report r : obtainedReportList) {
			assertEquals(0, obtainedReportList.get(0).getMatchPercentage());
			assertEquals(5, obtainedReportList.get(1).getMatchPercentage());
			assertEquals(0, obtainedReportList.get(2).getMatchPercentage());
			assertEquals(1, obtainedReportList.get(3).getMatchPercentage());
		}

	}

}
