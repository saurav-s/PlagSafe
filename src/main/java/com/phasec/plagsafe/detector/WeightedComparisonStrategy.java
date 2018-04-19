package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.models.Report;
import com.phasec.plagsafe.models.SubmissibleRecord;
import com.phasec.plagsafe.services.SystemStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.SubmissionUtility;
import util.WeightPropertyReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.phasec.plagsafe.models.StrategyType.*;

public class WeightedComparisonStrategy implements DetectionStrategy {
    private static Logger logger = LoggerFactory.getLogger(WeightedComparisonStrategy.class);

    private static final String MATCHING_REMARK = "Weighted comparison result of all comparison";

    @Override
    public List<Report> compare(SubmissibleRecord submission1, SubmissibleRecord submission2) {
        List<Report> reportList = new ArrayList<>();

        for(Submissible sub1file : submission1.getSubmissibles()) {
            for(Submissible sub2file : submission2.getSubmissibles()) {
                // logical match
                ComparisonContext context = new ComparisonContext(SubmissionUtility.STRATEGY_MAP.get(LOGICAL));
                int logicalMatch = context.compare(sub1file, sub2file);

                // renaming match
                context = new ComparisonContext(SubmissionUtility.STRATEGY_MAP.get(RENAMING));
                int renamingMatch = context.compare(sub1file, sub2file);

                // refactoring match
                context = new ComparisonContext(SubmissionUtility.STRATEGY_MAP.get(REFACTORING));
                int refactoringMatch = context.compare(sub1file, sub2file);

                WeightPropertyReader prop = WeightPropertyReader.makeReaderObject();
                try {
                    prop.loadComparisonProperties();
                } catch(IOException e) {
                    logger.error("Weight properties could not be read properly");
                }

                int weightSum = (prop.getLogical_weight() + prop.getRefactoring_weight() + prop.getRenaming_weight());
                logger.info("Normalizing the polynomial result " + weightSum);

                int sum =   (prop.getLogical_weight() * logicalMatch) +
                        (prop.getRefactoring_weight() * refactoringMatch) +
                        (prop.getRenaming_weight() * renamingMatch);
                int normalizedSum = sum/(weightSum == 0?1:weightSum);

                reportList.add(new Report(sub1file.getName(), sub2file.getName(), normalizedSum, MATCHING_REMARK));
            }
        }

        return reportList;
    }

    /**
     * Comparison percentage is calculated individually for each strategy
     *
     * @param sub1file
     * @param sub2file
     * @return
     */
    @Override
    public int compare(Submissible sub1file, Submissible sub2file) {
        return 0;
    }

    /**
     * updates the services stat for weighted comparison request
     *
     * @param stats services stats that needs updating
     */
    @Override
    public void updateRequestCount(SystemStatisticsService stats) {
        stats.incrementWeightedComparisonRequestedRunsBy(1);
    }
}
