package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.models.Report;
import com.phasec.plagsafe.models.SubmissibleRecord;
import com.phasec.plagsafe.services.SystemStatisticsService;

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

    /**
     * compares the two given files according to the strategy
     * @param sub1file submission 1 file
     * @param sub2file submission 2 file
     * @return comparison score
     */
    public int compare(Submissible sub1file, Submissible sub2file) {
        return strategy.compare(sub1file, sub2file);
    }

    /**
     * updates the request count of the strategy
     */
    public void updateRequestCount(SystemStatisticsService stats) { strategy.updateRequestCount(stats); }
}
