package com.phasec.plagsafe.detector;

import java.util.List;

import com.phasec.plagsafe.objects.Report;
import com.phasec.plagsafe.objects.SubmissionRecord;

/**
 * This is the interface to abstract the plagiarism detection run for all the submissions
 * This interface is to be implemented by any class that needs to run a detection on list of submissions
 *
 * @author Rohit
 */
public interface Engine {
    /**
     * this method runs the detection on all the submissions with each other
     * @param submissions : list of submission records
     */
    public List<Report> runDetection(List<SubmissionRecord> submissions);
}
