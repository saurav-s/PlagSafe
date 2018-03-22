package com.phasec.plagsafe.detector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.phasec.plagsafe.objects.Report;

import util.NGramGeneratorUtility;

public class RenamingDetectionStrategy implements DetectionStrategy {
	@Override
	public List<Report> compare(List<Submissible> submission1, List<Submissible> submission2) {
		
		List<Report> reportList = new ArrayList<>();
		for(Submissible sub1: submission1){
			for(Submissible sub2: submission2){
				
					
			}
		}
		
		return reportList;
		
	}
	
	

}
