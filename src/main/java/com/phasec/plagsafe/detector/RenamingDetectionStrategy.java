package com.phasec.plagsafe.detector;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.phasec.plagsafe.objects.Report;
import com.phasec.plagsafe.objects.SubmissibleRecord;

import util.LevenshteinDistanceGeneratorUtility;
import util.SubmissionUtility;

/**
 * Represents one of the implementations of the Plagiarism detection
 * strategies. This strategy takes the help of Levenshtein distance or minimum
 * edit distance algorithm. We calculate the minimum edit distance required to
 * convert code base of one submission file to another. Then we calculate the
 * percentage of similarity between the files on the basis of their edit
 * distance. This class implements the DetectionStrategy interface.
 * 
 * @author Tridiv
 *
 */
@Service
public class RenamingDetectionStrategy implements DetectionStrategy {

	// String constant representing the remark for the detection strategy.
	private static final String MATCH_REMARK = "Renaming Similarity Measure ";

	/**
	 * Represents the concrete method to compare two submissions. As each
	 * submission can have multiple files, this method iterates through all
	 * possible combinations of files and does a comparison between them to
	 * calculate the similarity measure.
	 * 
	 * @param submission1: a folder of submission with a list of submitted files
	 * @param submission2: another folder of submission with a list of submitted
	 *            files
	 * @return a list of reports that contains all results of one-to-one
	 *         comparisons
	 */
	@Override
	public List<Report> compare(SubmissibleRecord submission1, SubmissibleRecord submission2) {
		List<Report> reportList = new ArrayList<>();
		for (Submissible sub1 : submission1.getSubmissibles()) {
			for (Submissible sub2 : submission2.getSubmissibles()) {
				int matchPercentage = calculateRenamingMatch(sub1, sub2);
				Report newReport = new Report(sub1.getName(), sub2.getName(), matchPercentage, MATCH_REMARK);
				reportList.add(newReport);
			}
		}
		return reportList;
	}

	/**
	 * Represents the concrete method to compare between two individual file
	 * submissions.
	 * 
	 * @param sub1file: a file submission
	 * @param sub2file: another file submission
	 * @return Integer representing the similarity measure between the two input
	 *         file submissions
	 */
	@Override
	public int compare(Submissible sub1file, Submissible sub2file) {
		return calculateRenamingMatch(sub1file, sub2file);
	}

	/**
	 * Given two files calculates the match percentage based on the renaming of
	 * the files with the help of Levenshtein distance
	 * 
	 * @param sub1: a file submission
	 * @param sub2: another file submission
	 * @return Integer representing a match percentage between the files on the
	 *         basis of their Levensthein distance
	 */
	private int calculateRenamingMatch(Submissible sub1, Submissible sub2) {
		int minimumEditDistance = LevenshteinDistanceGeneratorUtility.getLevenshteinDistance(sub1.getCode(),
				sub2.getCode());
		int totalFileLength = SubmissionUtility.getTotalSubmissionFileLength(sub1.getCode(), sub2.getCode());
		return SubmissionUtility.getMatchPercentage(minimumEditDistance, totalFileLength);
	}
}
