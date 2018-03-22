package com.phasec.plagsafe.detector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.phasec.plagsafe.objects.Report;

import util.NGramGeneratorUtility;

public class RefactoringDetectionStrategy implements DetectionStrategy {
	
	private static final String MATCH_REMARK = "Refactoring Similarity Measure ";

	@Override
	public List<Report> compare(List<Submissible> submission1, List<Submissible> submission2) {

		List<Report> reportList = new ArrayList<>();
		for (Submissible sub1 : submission1) {
			for (Submissible sub2 : submission2) {
				List<String> list1 = NGramGeneratorUtility.getNGramList(sub1.getCode(), 3);
				List<String> list2 = NGramGeneratorUtility.getNGramList(sub2.getCode(), 3);
				double similarityMeasure = compareTwoLists(list1, list2);
				Report newReport = new Report(sub1.getName(), sub2.getName(), (int) similarityMeasure*100, MATCH_REMARK);
				reportList.add(newReport);
			}
		}
		
		return reportList;

	}

	public double compareTwoLists(List<String> list1, List<String> list2) {

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
		return ((double) intersectionOfNGrams) / ((double) union);

	}

}
