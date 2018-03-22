package com.phasec.plagsafe.detector;

import java.util.List;

public class SubmissionCompare implements SubmissionComparable {
    @Override
    public void compare(List<Submissible> submission1, List<Submissible> submission2) {
        ComparisonContext context = null;

        //compare for variable/function renaming using Levenshtein distance
        context = new ComparisonContext(new RenamingDetectionStrategy());
        context.compare(submission1, submission2);

        // compare for similarity in code logic using AST of each file in the submission
        context = new ComparisonContext(new LogicalSimilarityDetectionStrategy());
        context.compare(submission1, submission2);
    }
}
