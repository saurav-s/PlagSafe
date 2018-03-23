package com.phasec.plagsafe.detector;



import com.phasec.plagsafe.objects.Report;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.phasec.plagsafe.ComparisonService;
import com.phasec.plagsafe.objects.FileModel;
import com.phasec.plagsafe.objects.SubmissionRecord;

public class LogicalSimilarityDetectorTests {
    // test plagiarism with exact same files
    @Test
    public void testLogicalDetectionSameFiles() {
        List<SubmissionRecord> submissions = new ArrayList<>();

        FileModel file1 = new FileModel();
        file1.setFileName("simple.py");
        file1.setFileData(new File("resources/simple.py"));
        List<FileModel> file1List = new ArrayList<FileModel>();
        file1List.add(file1);
        SubmissionRecord record = new SubmissionRecord();
        record.setFiles(file1List);
        submissions.add(record);

        FileModel file2 = new FileModel();
        file2.setFileName("simple.py");
        file2.setFileData(new File("resources/simple.py"));
        List<FileModel> file2List = new ArrayList<FileModel>();
        file2List.add(file2);
        SubmissionRecord record2 = new SubmissionRecord();
        record2.setFiles(file2List);
        submissions.add(record2);

        ComparisonService controllerForTest = new ComparisonService();

        List<Report> reports = controllerForTest.submissionStub(submissions);
        StringBuilder actual = new StringBuilder();
        for(Report r : reports)
            actual.append(r.toString());
        String expected = "Report [sourceFile=simple.py, targetFile=simple.py, matchPercentage=100, matchRemark=Renaming Similarity Measure ]Report [sourceFile=simple.py, targetFile=simple.py, matchPercentage=100, matchRemark=Logical similarities detected.]Report [sourceFile=simple.py, targetFile=simple.py, matchPercentage=100, matchRemark=Refactoring Similarity Measure ]";

        Assert.assertEquals(expected, actual.toString());
    }

    //test plagiarism with different files
    @Test
    public void testLogicalDetectionDifferentFiles() {
        List<SubmissionRecord> submissions = new ArrayList<>();

        FileModel file1 = new FileModel();
        file1.setFileName("simple.py");
        file1.setFileData(new File("resources/simple.py"));
        List<FileModel> file1List = new ArrayList<FileModel>();
        file1List.add(file1);
        SubmissionRecord record = new SubmissionRecord();
        record.setFiles(file1List);
        submissions.add(record);

        FileModel file2 = new FileModel();
        file2.setFileName("simple1.py");
        file2.setFileData(new File("resources/simple1.py"));
        List<FileModel> file2List = new ArrayList<FileModel>();
        file2List.add(file2);
        SubmissionRecord record2 = new SubmissionRecord();
        record2.setFiles(file1List);
        submissions.add(record2);

        ComparisonService controllerForTest = new ComparisonService();
        List<Report> reports = controllerForTest.submissionStub(submissions);
        StringBuilder actual = new StringBuilder();
        for(Report r : reports)
            actual.append(r.toString());
        String expected = "Report [sourceFile=simple.py, targetFile=simple.py, matchPercentage=100, matchRemark=Renaming Similarity Measure ]Report [sourceFile=simple.py, targetFile=simple.py, matchPercentage=100, matchRemark=Logical similarities detected.]Report [sourceFile=simple.py, targetFile=simple.py, matchPercentage=100, matchRemark=Refactoring Similarity Measure ]";

        Assert.assertEquals(expected, actual.toString());
    }

    //test for no plagiarism on different files
    @Test
    public void testLogicalDetectionVeryDifferentFiles() {
        List<SubmissionRecord> submissions = new ArrayList<>();

        FileModel file1 = new FileModel();
        file1.setFileName("simple1.py");
        file1.setFileData(new File("resources/simple1.py"));
        List<FileModel> file1List = new ArrayList<FileModel>();
        file1List.add(file1);
        SubmissionRecord record = new SubmissionRecord();
        record.setFiles(file1List);
        submissions.add(record);

        FileModel file2 = new FileModel();
        file2.setFileName("single_variant.py");
        file2.setFileData(new File("resources/single_variant.py"));
        List<FileModel> file2List = new ArrayList<FileModel>();
        file2List.add(file2);
        SubmissionRecord record2 = new SubmissionRecord();
        record2.setFiles(file1List);
        submissions.add(record2);

        ComparisonService controllerForTest = new ComparisonService();
        List<Report> reports = controllerForTest.submissionStub(submissions);
        StringBuilder actual = new StringBuilder();
        for(Report r : reports)
            actual.append(r.toString());

        String expected = "Report [sourceFile=simple1.py, targetFile=simple1.py, matchPercentage=100, matchRemark=Renaming Similarity Measure ]Report [sourceFile=simple1.py, targetFile=simple1.py, matchPercentage=100, matchRemark=Logical similarities detected.]Report [sourceFile=simple1.py, targetFile=simple1.py, matchPercentage=100, matchRemark=Refactoring Similarity Measure ]";
        Assert.assertEquals(expected, actual.toString());
    }

}
