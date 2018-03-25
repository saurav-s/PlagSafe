package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.StrategyType;
import com.phasec.plagsafe.objects.Report;
import com.phasec.plagsafe.objects.SubmissibleRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubmissionCompare implements SubmissionComparable {
	

    @Override
    public List<Report> compare(SubmissibleRecord submission1, SubmissibleRecord submission2, StrategyType comparisonStrategy) {
        List<Report> matchReportList = new ArrayList<>();
        switch(comparisonStrategy) {
        		case RENAMING:  		matchReportList = compareRenaming(submission1, submission2);
        					   		break;
        		case LOGICAL: 		matchReportList = compareLogic(submission1, submission2);
				   				break;
        		case REFACTORING:	matchReportList = compareRefactoring(submission1, submission2);
				   				break;
        		case ALL:			matchReportList = compareAll(submission1, submission2);
				   				break;
        		case COMBINED:		matchReportList = compareCombined(submission1, submission2);
   								break;
        }
        return matchReportList;
    }

 
	/**
	 * compare using combination of all the strategies dynamically
	 * @param submission1
	 * @param submission2
	 * @return
	 */
	private List<Report> compareCombined(SubmissibleRecord submission1, SubmissibleRecord submission2) {
		//Do some stuff
		return Collections.EMPTY_LIST;
	}
    
    
	/**
	 * compare using all the strategies
	 * @param submission1
	 * @param submission2
	 * @return
	 */
	private List<Report> compareAll(SubmissibleRecord submission1, SubmissibleRecord submission2) {
		List<Report> matchReportList = new ArrayList<>();
		matchReportList.addAll(compareRenaming(submission1, submission2));
		matchReportList.addAll(compareLogic(submission1, submission2));
		matchReportList.addAll(compareRefactoring(submission1, submission2));
		return matchReportList;
	}
	
	
	/**
	 * comparing for code refactoring
	 * @param submission1
	 * @param submission2
	 * @return
	 */
	private List<Report> compareRefactoring(SubmissibleRecord submission1, SubmissibleRecord submission2) {
		ComparisonContext context = new ComparisonContext(new RefactoringDetectionStrategy());
        return context.compare(submission1, submission2);
	}

	/**
	 * compare for similarity in code logic using AST of each file in the submission
	 * @param submission1
	 * @param submission2
	 * @return
	 */
	private List<Report> compareLogic(SubmissibleRecord submission1, SubmissibleRecord submission2) {
		ComparisonContext context = new ComparisonContext(new LogicalSimilarityDetectionStrategy());
        return context.compare(submission1, submission2);
	}

	/**
	 * compare for variable/function renaming using Levenshtein distance
	 * @param submission1
	 * @param submission2
	 * @return
	 */
	private List<Report> compareRenaming(SubmissibleRecord submission1, SubmissibleRecord submission2) {
		ComparisonContext context = new ComparisonContext(new RenamingDetectionStrategy());
        return context.compare(submission1, submission2);
	}
	
}
