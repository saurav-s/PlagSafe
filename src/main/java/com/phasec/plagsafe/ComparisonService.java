package com.phasec.plagsafe;

import java.util.List;

import com.phasec.plagsafe.objects.FileRecord;
import com.phasec.plagsafe.objects.Report;
import com.phasec.plagsafe.objects.SubmissionRecord;

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
