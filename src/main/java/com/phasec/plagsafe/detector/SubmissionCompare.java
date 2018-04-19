package com.phasec.plagsafe.detector;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phasec.plagsafe.models.StrategyType;
import com.phasec.plagsafe.models.Report;
import com.phasec.plagsafe.models.SubmissibleRecord;

import util.SubmissionUtility;

public class SubmissionCompare implements SubmissionComparable {
	private static final String MATCHING_REMARK = "Weighted comparison result of all comparison";
    private static Logger logger = LoggerFactory.getLogger(SubmissionCompare.class);

	/**
	 * performs comparison on the submissions given the strategy
	 * @param submission1 first submission
	 * @param submission2 second submission
	 * @param comparisonStrategy the comparison strategy requested by the user
	 * @return report list for each comparison on the file
	 */

    @Override
    public List<Report> compare(SubmissibleRecord submission1, SubmissibleRecord submission2, StrategyType comparisonStrategy) {
        List<Report> matchReportList = new ArrayList<>();
		ComparisonContext context = new ComparisonContext(SubmissionUtility.STRATEGY_MAP.get(comparisonStrategy));
        return context.compare(submission1, submission2);
    }
}
