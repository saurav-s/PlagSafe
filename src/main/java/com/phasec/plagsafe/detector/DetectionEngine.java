package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.objects.FileMap;
import util.SubmissionUtility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DetectionEngine implements Engine {
    /**
     *
     * @param submissions : list of submissions containing a list of submission files
     */
    @Override
    public void runDetection(List<List<FileMap>> submissions) {

        SubmissionUtility subUtil = new SubmissionUtility();

        List<List<Submissible>> submissionsMetadataList = new ArrayList<List<Submissible>>();

        for(List<FileMap> sub : submissions) {
            List<Submissible> submissionMetadata = new ArrayList<Submissible>();
            for(FileMap file : sub) {
                submissionMetadata.add(subUtil.initializeSubmission(file));
            }
            submissionsMetadataList.add(submissionMetadata);
            System.out.println(submissionsMetadataList.size());
        }
    }
}
