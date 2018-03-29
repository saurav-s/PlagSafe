package com.phasec.plagsafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.phasec.plagsafe.detector.DetectionEngine;
import com.phasec.plagsafe.detector.Engine;
import com.phasec.plagsafe.objects.FileRecord;
import com.phasec.plagsafe.objects.Report;
import com.phasec.plagsafe.objects.SubmissionRecord;

import util.FileUtility;

/**
 * Implementation for ComparisonService to compare the files
 * @author sanketsaurav
 *
 */
@Service
public class ComparisonServiceImpl implements ComparisonService{

    /**
     * Runs comparison for the SubmissionRecord list and generates a List of report containing match results
     * @param submissions
     * @param comparisonStrategy 
     * @return List of report containing match results
     */
	@Override
    public List<Report> submissionStub(List<SubmissionRecord> submissions, StrategyType comparisonStrategy) {
        Engine detectionEngine = new DetectionEngine();
        return detectionEngine.runDetection(submissions, comparisonStrategy);
    }
    
    /**
     * Runs comparison for the file record list and generates a List of report containing match results
     * @param filesList
     * @param comparisonStrategy 
     * @return List of report containing match results
     */
	@Override
    public List<Report> runComparisionForFiles(List<FileRecord> filesList, StrategyType comparisonStrategy){
		List<SubmissionRecord> submissionRecords = createSubmissionRecords(filesList);
		List<Report> reports = submissionStub(submissionRecords, comparisonStrategy);
		Collections.sort(reports);
		return reports;
		
    }

    /**
     * creates a list of submission records for the given list of files
     * @param filesList
     * @return list of SubmissionRecord
     */
	private List<SubmissionRecord> createSubmissionRecords(List<FileRecord> filesList) {
		List<SubmissionRecord>  submissionRecords = new ArrayList<>();
		for(FileRecord files : filesList) {
			SubmissionRecord record = FileUtility.getFileMapList(files);
			submissionRecords.add(record);
		}
		return submissionRecords;
	}
}