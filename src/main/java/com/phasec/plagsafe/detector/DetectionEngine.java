package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.objects.FileMap;
import com.phasec.plagsafe.objects.Report;
import util.SubmissionUtility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DetectionEngine implements Engine {
    /**
     *
     * @param submissions : list of submissions containing a list of submission files
     */
	private Logger log;
	public DetectionEngine(){
		log = LoggerFactory.getLogger(this.getClass().getName());
	}
    @Override
    public List<Report> runDetection(List<List<FileMap>> submissions) {
    	log.info("Running detection engine...");
        List<List<Submissible>> submissionsMetadataList = createSubmissionsList(submissions);
        int numberOfSubmissions = submissionsMetadataList.size();
        List<Report> reportList = new ArrayList<>();

        SubmissionComparable subCompare = new SubmissionCompare();

        for(int i=0;i<numberOfSubmissions;i++) {
            for(int j=i+1;j<numberOfSubmissions;j++) {
                List<Report> current = subCompare.compare(submissionsMetadataList.get(i), submissionsMetadataList.get(j));
                reportList.addAll(current);
            }
        }

        for(Report report : reportList)
            System.out.println(report.toString());

        return reportList;
    }

    /**
     * This function initializes submission metadata and creates a list of the same
     * @param submissions list of list of submissions
     * @return list of submissions metadata
     *
     */
    private List<List<Submissible>> createSubmissionsList(List<List<FileMap>> submissions) {
    	log.info("Creating list of submissions...");
        List<List<Submissible>> submissionsMetadataList = new ArrayList<List<Submissible>>();
        SubmissionUtility subUtil = new SubmissionUtility();
        for(List<FileMap> sub : submissions) {
            List<Submissible> submissionMetadata = new ArrayList<Submissible>();
            for(FileMap file : sub) {
                submissionMetadata.add(subUtil.initializeSubmission(file));
            }
            submissionsMetadataList.add(submissionMetadata);
            //System.out.println(submissionsMetadataList.size());
        }

        return submissionsMetadataList;
    }
}
