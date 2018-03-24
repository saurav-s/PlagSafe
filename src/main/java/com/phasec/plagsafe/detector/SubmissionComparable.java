package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.StrategyType;
import com.phasec.plagsafe.objects.Report;
import com.phasec.plagsafe.objects.SubmissibleRecord;

import java.util.List;

public interface SubmissionComparable {
	/**
	 * 
	 * @param submissibleRecord
	 * @param submissibleRecord2
	 * @param comparisonStrategy
	 * @return
	 */
    public List<Report> compare(SubmissibleRecord submissibleRecord, SubmissibleRecord submissibleRecord2, StrategyType comparisonStrategy);
}
