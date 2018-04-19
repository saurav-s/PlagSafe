package com.phasec.plagsafe.detector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.phasec.plagsafe.services.SystemStatisticsService;
import org.springframework.stereotype.Service;

import com.phasec.plagsafe.models.Report;
import com.phasec.plagsafe.models.SubmissibleRecord;

import util.NGramGeneratorUtility;

/**
 * Represents one of the implementations of the Plagiarism detection strategies.
 * This strategy takes the help of n-grams and w-shingling to detect the
 * similarity between two files. We calculate the n-gram for each of the file.
 * Then we calculate their similarity based on the concept of w-shingling. This
 * class implements the DetectionStrategy interface Reference:
 * https://en.wikipedia.org/wiki/W-shingling
 * 
 * @author Tridiv
 *
 */
@Service
public class RefactoringDetectionStrategy implements DetectionStrategy {

	// Constant declarations
	private static final String MATCH_REMARK = "Refactoring Similarity Measure ";
	private static final int NGRAM_SIZE = 3;

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
				int similarityMeasure = getNGramSimilarityMeasure(sub1, sub2);
				Report newReport = new Report(sub1.getName(), sub2.getName(), similarityMeasure, MATCH_REMARK);
				reportList.add(newReport);
			}
		}
		return reportList;

	}

	/**
	 * Represents the concrete method to compare between two individual file
	 * submissions.
	 * 
	 * @param sub1: a file submission
	 * @param sub2: another file submission
	 * @return Integer representing the similarity measure between the two input
	 *         file submissions
	 */
	@Override
	public int compare(Submissible sub1, Submissible sub2) {
		return getNGramSimilarityMeasure(sub1, sub2);
	}

	/**
	 * updates the refactoring comparison request count
	 *
	 */

	@Override
	public void updateRequestCount(SystemStatisticsService stats) {
		stats.incrementRefactoringComparisonRequested(1);
	}

	/**
	 * Calculates N-grams and compares the results to find the similarity
	 * measure
	 * 
	 * @param sub1: a file submission
	 * @param sub2: another file submission
	 * @return Integer representing the similarity measure between the two input
	 *         file submissions on the basis of their generated N-grams
	 */
	private int getNGramSimilarityMeasure(Submissible sub1, Submissible sub2) {
		List<String> list1 = NGramGeneratorUtility.getNGramList(sub1.getCode(), NGRAM_SIZE);
		List<String> list2 = NGramGeneratorUtility.getNGramList(sub2.getCode(), NGRAM_SIZE);
		double similarityMeasure = compareTwoLists(list1, list2);
		return (int) similarityMeasure;
	}

	/**
	 * Compares two lists of N-Grams returns the similarity quotient between
	 * them with the help of w-shingling formulae
	 * 
	 * @param list1: List of N-grams
	 * @param list2: Another list of N-grams
	 * @return Double value representing the similarity between the two input
	 *         list of N-grams.
	 */
	private double compareTwoLists(List<String> list1, List<String> list2) {

		Collections.sort(list1);
		Collections.sort(list2);

		int intersectionOfNGrams = getIntersectionCount(list1, list2);
		int union = list1.size() + list2.size() - intersectionOfNGrams;

		return ((double) intersectionOfNGrams * 100) / ((double) union);

	}

	/**
	 * 
	 * @param list1: a list of Strings
	 * @param list2: another list of Strings
	 * @return Integer representing the intersection between the two input
	 *         strings.
	 */
	private int getIntersectionCount(List<String> list1, List<String> list2) {

		int intersectionOfNGrams = 0;
		int index1 = 0;
		int index2 = 0;

		while (index1 < list1.size() && index2 < list2.size()) {
			if (list1.get(index1).equals(list2.get(index2))) {
				intersectionOfNGrams++;
				index1++;
				index2++;
			} else if (list1.get(index1).compareTo(list2.get(index2)) < 0) {
				index1++;
			} else {
				index2++;
			}
		}
		return intersectionOfNGrams;

	}

}
