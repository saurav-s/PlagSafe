package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.models.StrategyType;
import com.phasec.plagsafe.models.Report;
import com.phasec.plagsafe.models.SubmissibleRecord;

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
