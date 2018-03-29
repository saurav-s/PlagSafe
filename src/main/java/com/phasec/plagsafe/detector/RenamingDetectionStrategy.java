package com.phasec.plagsafe.detector;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.phasec.plagsafe.objects.Report;
import com.phasec.plagsafe.objects.SubmissibleRecord;

import util.LevenshteinDistanceGeneratorUtility;
import util.SubmissionUtility;

@Service
public class RenamingDetectionStrategy implements DetectionStrategy {
	
	private static final String MATCH_REMARK = "Renaming Similarity Measure ";


	/**
	 * the concrete method to show comparison result for two submissions
	 * @param submission1 a folder of submission with a list of submitted files
	 * @param submission2 another folder of submission with a list of submitted files
	 * @return a list of reports that contains all results of one-to-one comparison
	 */

	@Override
	public List<Report> compare(SubmissibleRecord submission1, SubmissibleRecord submission2) {
		List<Report> reportList = new ArrayList<>();
		for(Submissible sub1: submission1.getSubmissibles()){
			for(Submissible sub2: submission2.getSubmissibles()){
				int matchPercentage = calculateRenamingMatch(sub1, sub2);
				Report newReport = new Report(sub1.getName(), sub2.getName(), matchPercentage, MATCH_REMARK);
				reportList.add(newReport);				
			}
		}
		return reportList;
	}

	/**
	 *
	 * @param sub1file
	 * @param sub2file
	 * @return
	 */
	@Override
	public int compare(Submissible sub1file, Submissible sub2file) {
		return calculateRenamingMatch(sub1file, sub2file);
	}

	/**
	 * given two files calculates the match percentage based on the renaming of the files
	 * @param sub1
	 * @param sub2
	 * @return match percentage
	 */
	private int calculateRenamingMatch(Submissible sub1, Submissible sub2) {
		int minimumEditDistance = LevenshteinDistanceGeneratorUtility.getLevenshteinDistance(sub1.getCode(), sub2.getCode());
		int averageFileLength = SubmissionUtility.getAverageSubmissionFileLength(sub1.getCode(), sub2.getCode());
		int matchPercentage = SubmissionUtility.getMatchPercentage(minimumEditDistance, averageFileLength);
		return matchPercentage;
	}
}
