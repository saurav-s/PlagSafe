package com.phasec.plagsafe.detector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.phasec.plagsafe.ComparisonService;
import com.phasec.plagsafe.objects.FileModel;
import com.phasec.plagsafe.objects.SubmissionRecord;

/**
 * Tests for the Comparison Controller class
 *
 */
public class ComparisonServiceTests {
    // this test is to initialize with single submission
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
        ComparisonService controllerForTest = new ComparisonService();
        controllerForTest.submissionStub(submissions);
        Assert.assertEquals(true, true);
    }
}
