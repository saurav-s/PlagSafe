package com.phasec.plagsafe;
/**
 *
 */

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.phasec.plagsafe.detector.DetectionEngine;
import com.phasec.plagsafe.detector.Engine;
import com.phasec.plagsafe.objects.MultipartRecord;
import com.phasec.plagsafe.objects.Report;
import com.phasec.plagsafe.objects.SubmissionRecord;

import util.FileUtility;

/**
 * Service for starting comparison of files
 * @author sanketsaurav
 *
 */
@Service
public class ComparisonService {

    /**
     *
     * @param submissions
     */
    public List<Report> submissionStub(List<SubmissionRecord> submissions) {
        Engine detectionEngine = new DetectionEngine();
        return detectionEngine.runDetection(submissions);
    }
    
    /**
     * 
     * @param submissions
     * @return report list
     */
    public List<Report> runComparision(List<MultipartRecord> multiPartRecords){
    		List<SubmissionRecord>  submissionRecords = new ArrayList<>();
    		for(MultipartRecord multiPartRecord : multiPartRecords) {
    			SubmissionRecord record = FileUtility.getFileMapList(multiPartRecord);
    			submissionRecords.add(record);
    		}
    		return submissionStub(submissionRecords);
    }
}
