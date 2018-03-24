package com.phasec.plagsafe.detector;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.phasec.plagsafe.ComparisonService;
import com.phasec.plagsafe.StrategyType;
import com.phasec.plagsafe.objects.FileModel;
import com.phasec.plagsafe.objects.FileRecord;
import com.phasec.plagsafe.objects.Report;
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
        controllerForTest.submissionStub(submissions,StrategyType.ALL);
        Assert.assertEquals(true, true);
    }
    
    @Test
    public void testRunComparisionForFiles(){
    	
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
    	
    	ComparisonService cs = new ComparisonService();
    	List<Report> res = cs.runComparisionForFiles(frList,StrategyType.ALL);
    	
    	assertNotNull(res);
    	assertEquals(res.size(), 3);
    	
    	
    }
}
