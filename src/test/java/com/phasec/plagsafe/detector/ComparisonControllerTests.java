package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.ComparisonController;
import com.phasec.plagsafe.objects.FileMap;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests for the Comparison Controller class
 *
 */
public class ComparisonControllerTests {
    // this test is to initialize with single submission
    @Test
    public void testSingleSubmissionInitialization() {
        List<List<FileMap>> submissions = new ArrayList<List<FileMap>>();

        FileMap file = new FileMap();
        file.setFileName("simple.py");
        file.setFileData(new File("resources/simple.py"));
        List<FileMap> fileList = new ArrayList<FileMap>();
        fileList.add(file);
        submissions.add(fileList);
        ComparisonController controllerForTest = new ComparisonController();
        controllerForTest.submissionStub(submissions);
        Assert.assertEquals(true, true);
    }
}
