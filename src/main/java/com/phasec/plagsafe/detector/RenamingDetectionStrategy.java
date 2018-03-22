package com.phasec.plagsafe.detector;

import java.util.Collections;
import java.util.List;

public class RenamingDetectionStrategy implements DetectionStrategy {
	@Override
	public void compare(List<Submissible> submission1, List<Submissible> submission2) {
		
		for(Submissible sub1: submission1){
			for(Submissible sub2: submission2){
				
			}
		}
		
	}
	
	public double compareTwoLists(List<String> list1, List<String> list2){
		
		Collections.sort(list1);
		Collections.sort(list2);
		
		int intersectionOfNGrams = 0;
		
		int index1 = 0;
		int index2 = 0;
		
		while(index1 < list1.size() && index2 < list2.size()){
			if(list1.get(index1).equals(list2.get(index2))){
				intersectionOfNGrams++;
				index1++;
				index2++;
			}
			else if(list1.get(index1).compareTo(list2.get(index2))<0){
				index1++;
			}
			else{
				index2++;
			}
		}
		
		int union = list1.size() + list2.size() - intersectionOfNGrams;
		return ((double)intersectionOfNGrams) / ((double)union);
		
		
		
		
	}

}
