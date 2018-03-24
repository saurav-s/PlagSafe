package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.objects.Report;
import com.phasec.plagsafe.objects.SubmissibleRecord;

import java.util.List;

/**
 *the comparison context class for the user
 */

public class ComparisonContext {
    //
    private DetectionStrategy strategy;

    /**
     * constructor fot the comparisonCintext class
     * @param strategy choosen strategy
     */

    public ComparisonContext(DetectionStrategy strategy) {
        this.strategy = strategy;
    }



    /**
     * compare two submissions
     * @param submission1 the first submitted folder
     * @param submission2 the second submitted folder
     */

    public List<Report> compare(SubmissibleRecord submission1, SubmissibleRecord submission2) {
        return strategy.compare(submission1, submission2);
    }
}
