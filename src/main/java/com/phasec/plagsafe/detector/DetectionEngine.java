package com.phasec.plagsafe.detector;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phasec.plagsafe.objects.FileModel;
import com.phasec.plagsafe.objects.Report;
import com.phasec.plagsafe.objects.SubmissibleRecord;
import com.phasec.plagsafe.objects.SubmissionRecord;

import util.SubmissionUtility;


public class DetectionEngine implements Engine {
	
	private static Logger logger = LoggerFactory.getLogger(DetectionEngine.class);
	
    /**
     *
     * @param submissions : list of submissions containing a list of submission files
     */
	private Logger log;
	public DetectionEngine(){
		log = LoggerFactory.getLogger(this.getClass().getName());
	}
    @Override
    public List<Report> runDetection(List<SubmissionRecord> submissions) {
        List<SubmissibleRecord> submissionsMetadataList = createSubmissionsList(submissions);

        int numberOfSubmissions = submissionsMetadataList.size();
        List<Report> reportList = new ArrayList<>();

        SubmissionComparable subCompare = new SubmissionCompare();

        for(int i=0;i<numberOfSubmissions;i++) {
            for(int j=i+1;j<numberOfSubmissions;j++) {
                List<Report> current = subCompare.compare(submissionsMetadataList.get(i), submissionsMetadataList.get(j));
                reportList.addAll(current);
            }
        }

        for(Report report : reportList) {
        		String reportString = report.toString();
            logger.info(reportString);
        }

        return reportList;
    }

    /**
     * This function initializes submission metadata and creates a list of the same
     * @param submissions list of list of submissions
     * @return list of submissions metadata
     *
     */

    private List<SubmissibleRecord> createSubmissionsList(List<SubmissionRecord> submissions) {
        List<SubmissibleRecord> submissionsMetadataList = new ArrayList<>();
        
        SubmissionUtility subUtil = new SubmissionUtility();
        for(SubmissionRecord sub : submissions) {
            SubmissibleRecord submissionMetadata = new SubmissibleRecord();
            for(FileModel file : sub.getFiles()) {
            		List<Submissible> submissibles = new ArrayList<>();
            		submissibles.add(subUtil.initializeSubmission(file));
                submissionMetadata.setSubmissibles(submissibles);
            }
            submissionsMetadataList.add(submissionMetadata);
        }
        return submissionsMetadataList;
    }
}
