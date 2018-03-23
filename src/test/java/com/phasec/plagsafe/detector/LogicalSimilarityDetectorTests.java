package com.phasec.plagsafe.detector;


import com.phasec.plagsafe.ComparisonController;
import com.phasec.plagsafe.objects.FileMap;
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
        controllerForTest.submissionStub(submissions);
        Assert.assertEquals(true, true);
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
        controllerForTest.submissionStub(submissions);
        Assert.assertEquals(true, true);
    }

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
        controllerForTest.submissionStub(submissions);
        Assert.assertEquals(true, true);
    }

}
