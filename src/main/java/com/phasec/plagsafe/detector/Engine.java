package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.objects.FileMap;

import java.util.List;

/**
 * This is the interface to abstract the plagiarism detection run for all the submissions
 * This interface is to be implemented by any class that needs to run a detection on list of submissions
 *
 * @author Rohit
 */
public interface Engine {
    /**
     * this methos runs the detection on all the submissions with each other
     * @param submissions : list of submissions containing a list of submission files
     */
    public void runDetection(List<List<FileMap>> submissions);
}
