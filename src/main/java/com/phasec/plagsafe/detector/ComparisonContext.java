package com.phasec.plagsafe.detector;

import java.util.List;

/**
 *
 */
public class ComparisonContext {
    //
    private DetectionStrategy strategy;

    /**
     *
     * @param strategy
     */
    public ComparisonContext(DetectionStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     *
     * @param submission1
     * @param submission2
     */
    public void compare(List<Submissible> submission1, List<Submissible> submission2) {
        strategy.compare(submission1, submission2);
    }
}
