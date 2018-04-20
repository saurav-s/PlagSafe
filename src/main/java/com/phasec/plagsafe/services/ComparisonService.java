package com.phasec.plagsafe.services;

import java.util.List;

import com.phasec.plagsafe.models.StrategyType;
import com.phasec.plagsafe.models.FileRecord;
import com.phasec.plagsafe.models.Report;
import com.phasec.plagsafe.models.SubmissionRecord;

/**
 * Interface for comparing files
 * @author sanketsaurav
 *
 */
public interface ComparisonService {

    /**
     * Runs comparison for the SubmissionRecord list and generates a List of report containing match results
     * @param submissions
     * @param comparisonStrategy 
     * @return List of report containing match results
     */
    List<Report> submissionStub(List<SubmissionRecord> submissions, StrategyType comparisonStrategy);
    
    /**
     * Runs comparison for the file record list and generates a List of report containing match results
     * @param filesList
     * @param comparisonStrategy 
     * @return List of report containing match results
     */
    List<Report> runComparisionForFiles(List<FileRecord> filesList, StrategyType comparisonStrategy);
}
