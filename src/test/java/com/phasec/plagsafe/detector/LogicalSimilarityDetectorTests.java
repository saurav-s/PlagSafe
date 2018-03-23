package com.phasec.plagsafe.detector;


import com.phasec.plagsafe.ComparisonController;
import com.phasec.plagsafe.objects.FileMap;
import com.phasec.plagsafe.objects.Report;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LogicalSimilarityDetectorTests {
    @Test
    public void testLogicalDetectionSameFiles() {
        List<List<FileMap>> submissions = new ArrayList<List<FileMap>>();

        FileMap file1 = new FileMap();
        file1.setFileName("simple.py");
        file1.setFileData(new File("resources/simple.py"));
        List<FileMap> file1List = new ArrayList<FileMap>();
        file1List.add(file1);
        submissions.add(file1List);

        FileMap file2 = new FileMap();
        file2.setFileName("simple.py");
        file2.setFileData(new File("resources/simple.py"));
        List<FileMap> file2List = new ArrayList<FileMap>();
        file2List.add(file2);
        submissions.add(file2List);

        ComparisonController controllerForTest = new ComparisonController();

        List<Report> reports = controllerForTest.submissionStub(submissions);
        StringBuilder actual = new StringBuilder();
        for(Report r : reports)
            actual.append(r.toString());
        String expected = "Report [sourceFile=simple.py, targetFile=simple.py, matchPercentage=100, matchRemark=Renaming Similarity Measure ]Report [sourceFile=simple.py, targetFile=simple.py, matchPercentage=100, matchRemark=Logical similarities detected.]Report [sourceFile=simple.py, targetFile=simple.py, matchPercentage=100, matchRemark=Refactoring Similarity Measure ]";

        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void testLogicalDetectionDifferentFiles() {
        List<List<FileMap>> submissions = new ArrayList<List<FileMap>>();

        FileMap file1 = new FileMap();
        file1.setFileName("simple.py");
        file1.setFileData(new File("resources/simple.py"));
        List<FileMap> file1List = new ArrayList<FileMap>();
        file1List.add(file1);
        submissions.add(file1List);

        FileMap file2 = new FileMap();
        file2.setFileName("simple1.py");
        file2.setFileData(new File("resources/simple1.py"));
        List<FileMap> file2List = new ArrayList<FileMap>();
        file2List.add(file2);
        submissions.add(file2List);

        ComparisonController controllerForTest = new ComparisonController();
        List<Report> reports = controllerForTest.submissionStub(submissions);
        StringBuilder actual = new StringBuilder();
        for(Report r : reports)
            actual.append(r.toString());
        String expected = "Report [sourceFile=simple.py, targetFile=simple1.py, matchPercentage=77, matchRemark=Renaming Similarity Measure ]Report [sourceFile=simple.py, targetFile=simple1.py, matchPercentage=84, matchRemark=Logical similarities detected.]Report [sourceFile=simple.py, targetFile=simple1.py, matchPercentage=32, matchRemark=Refactoring Similarity Measure ]";

        Assert.assertEquals(expected, actual.toString());
    }

    //test for no plagiarism on different files
    @Test
    public void testLogicalDetectionVeryDifferentFiles() {
        List<List<FileMap>> submissions = new ArrayList<List<FileMap>>();

        FileMap file1 = new FileMap();
        file1.setFileName("simple1.py");
        file1.setFileData(new File("resources/simple1.py"));
        List<FileMap> file1List = new ArrayList<FileMap>();
        file1List.add(file1);
        submissions.add(file1List);

        FileMap file2 = new FileMap();
        file2.setFileName("single_variant.py");
        file2.setFileData(new File("resources/single_variant.py"));
        List<FileMap> file2List = new ArrayList<FileMap>();
        file2List.add(file2);
        submissions.add(file2List);

        ComparisonController controllerForTest = new ComparisonController();
        List<Report> reports = controllerForTest.submissionStub(submissions);
        StringBuilder actual = new StringBuilder();
        for(Report r : reports)
            actual.append(r.toString());

        String expected = "Report [sourceFile=simple1.py, targetFile=single_variant.py, matchPercentage=17, matchRemark=Renaming Similarity Measure ]Report [sourceFile=simple1.py, targetFile=single_variant.py, matchPercentage=28, matchRemark=Logical similarities detected.]Report [sourceFile=simple1.py, targetFile=single_variant.py, matchPercentage=2, matchRemark=Refactoring Similarity Measure ]";
        Assert.assertEquals(expected, actual.toString());
    }

}
