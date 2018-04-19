package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.models.Report;
import com.phasec.plagsafe.models.SubmissibleRecord;
import com.phasec.plagsafe.services.SystemStatisticsService;
import util.SubmissionUtility;

import java.util.ArrayList;
import java.util.List;

import static com.phasec.plagsafe.models.StrategyType.*;

/**
 * This class implements the detection strategy for all the different strategies to be run together
 * @author Rohit
 */

public class AllComparisonStrategies implements DetectionStrategy{
    /**
     * runs comparison for all the strategies separately at once
     * @param submission1 first submission to be compared
     * @param submission2 second submission to be compared
     * @return list of comparison report
     */
    @Override
    public List<Report> compare(SubmissibleRecord submission1, SubmissibleRecord submission2) {
        List<Report> matchReportList = new ArrayList<>();

        ComparisonContext context = new ComparisonContext(SubmissionUtility.STRATEGY_MAP.get(LOGICAL));
        matchReportList.addAll(context.compare(submission1, submission2));

        context = new ComparisonContext(SubmissionUtility.STRATEGY_MAP.get(REFACTORING));
        matchReportList.addAll(context.compare(submission1, submission2));


        context = new ComparisonContext(SubmissionUtility.STRATEGY_MAP.get(RENAMING));
        matchReportList.addAll(context.compare(submission1, submission2));

        return matchReportList;
    }

    /**
     * Return the comparison result percentage for all
     * @param sub1file
     * @param sub2file
     * @return
     */
    public int compare(Submissible sub1file, Submissible sub2file) {
        return 0;
    }

    /**
     * update the stats for each of the strategies
     *
     * @param stats stats to be updated
     */
    @Override
    public void updateRequestCount(SystemStatisticsService stats) {
        stats.incrementRenamingComparisonRequestedRunsBy(1);
        stats.incrementRefactoringComparisonRequested(1);
        stats.incrementLogicalComparisonRequestedBy(1);
    }
}
