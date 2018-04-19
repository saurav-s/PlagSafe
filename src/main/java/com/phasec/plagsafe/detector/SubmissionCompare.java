package com.phasec.plagsafe.detector;

import java.util.List;

import com.phasec.plagsafe.models.StrategyType;
import com.phasec.plagsafe.models.Report;
import com.phasec.plagsafe.models.SubmissibleRecord;

import util.SubmissionUtility;

public class SubmissionCompare implements SubmissionComparable {

	/**
	 * performs comparison on the submissions given the strategy
	 * @param submission1 first submission
	 * @param submission2 second submission
	 * @param comparisonStrategy the comparison strategy requested by the user
	 * @return report list for each comparison on the file
	 */

    @Override
    public List<Report> compare(SubmissibleRecord submission1, SubmissibleRecord submission2, StrategyType comparisonStrategy) {
		ComparisonContext context = new ComparisonContext(SubmissionUtility.getDetectionStrategy(comparisonStrategy));
        return context.compare(submission1, submission2);
    }
}
