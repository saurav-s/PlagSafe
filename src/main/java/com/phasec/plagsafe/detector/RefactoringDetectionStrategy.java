package com.phasec.plagsafe.detector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.phasec.plagsafe.objects.Report;
import com.phasec.plagsafe.objects.SubmissibleRecord;

import util.NGramGeneratorUtility;

@Service
public class RefactoringDetectionStrategy implements DetectionStrategy {
	
	private static Logger logger = LoggerFactory.getLogger(RefactoringDetectionStrategy.class);
	private static final String MATCH_REMARK = "Refactoring Similarity Measure ";
	private static final int NGRAM_SIZE = 3;

	/**
	 * the concrete method to show comparison result for two submissions
	 * @param submission1 a folder of submission with submitted rocord
	 * @param submission2 another folder of submission with submitted rocord
	 * @return a list of reports that contains all results of one-to-one comparison
	 */

	@Override
	public List<Report> compare(SubmissibleRecord submission1, SubmissibleRecord submission2) {

		List<Report> reportList = new ArrayList<>();
		for (Submissible sub1 : submission1.getSubmissibles()) {
			for (Submissible sub2 : submission2.getSubmissibles()) {
				int similarityMeasure = getNGramSimilarityMeasure(sub1, sub2);
				logger.info("similarityMeasure = " + similarityMeasure);
				Report newReport = new Report(sub1.getName(), sub2.getName(), similarityMeasure, MATCH_REMARK);
				reportList.add(newReport);
			}
		}
		
		return reportList;

	}

    /**
     * compares for refactoring similarities in two files
     * @param sub1
     * @param sub2
     * @return similarity measure of the two files
     */
	@Override
	public int compare(Submissible sub1, Submissible sub2) {
	    return getNGramSimilarityMeasure(sub1, sub2);
	}

    /**
     * Calculates N-grams and compares the results
     * @param sub1
     * @param sub2
     * @return return comparison results
     */
	private int getNGramSimilarityMeasure(Submissible sub1, Submissible sub2) {
        List<String> list1 = NGramGeneratorUtility.getNGramList(sub1.getCode(), NGRAM_SIZE);
        List<String> list2 = NGramGeneratorUtility.getNGramList(sub2.getCode(), NGRAM_SIZE);
        double similarityMeasure = compareTwoLists(list1, list2);
        return (int) similarityMeasure;
    }

	/**
	 * compare two submitted files
	 * @param list1 submission1 a folder of submission with a list of submitted files
	 * @param list2 another folder of submission with a list of submitted files
	 * @return the report of comparison of two single files
	 */

	private double compareTwoLists(List<String> list1, List<String> list2) {

		Collections.sort(list1);
		Collections.sort(list2);

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

		int union = list1.size() + list2.size() - intersectionOfNGrams;
		return ((double) intersectionOfNGrams * 100) / ((double) union);

	}

}
