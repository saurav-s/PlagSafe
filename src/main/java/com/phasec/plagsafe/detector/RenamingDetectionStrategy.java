package com.phasec.plagsafe.detector;

import java.util.ArrayList;

import java.util.List;

import com.phasec.plagsafe.objects.Report;

import util.LevenshteinDistanceGeneratorUtility;
import util.NGramGeneratorUtility;
import util.SubmissionUtility;

public class RenamingDetectionStrategy implements DetectionStrategy {
	
	private static final String MATCH_REMARK = "Renaming Similarity Measure ";
	
	@Override
	public List<Report> compare(List<Submissible> submission1, List<Submissible> submission2) {
		
				
		List<Report> reportList = new ArrayList<>();
		for(Submissible sub1: submission1){
			for(Submissible sub2: submission2){
				int minimumEditDistance = LevenshteinDistanceGeneratorUtility.getLevenshteinDistance(sub1.getCode(), sub2.getCode());
				int averageFileLength = SubmissionUtility.getAverageSubmissionFileLength(sub1.getCode(), sub2.getCode());
				int matchPercentage = SubmissionUtility.getMatchPercentage(minimumEditDistance, averageFileLength);
				Report newReport = new Report(sub1.getName(), sub2.getName(), matchPercentage, MATCH_REMARK);
				reportList.add(newReport);				
			}
		}
		
		return reportList;
		
	}
}
