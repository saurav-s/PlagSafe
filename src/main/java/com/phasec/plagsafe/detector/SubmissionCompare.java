package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.objects.Report;

import java.util.ArrayList;
import java.util.List;

public class SubmissionCompare implements SubmissionComparable {
    @Override
    public List<Report> compare(List<Submissible> submission1, List<Submissible> submission2) {
        ComparisonContext context = null;
        List<Report> matchReportList = new ArrayList<>();

        //compare for variable/function renaming using Levenshtein distance
        context = new ComparisonContext(new RenamingDetectionStrategy());
        matchReportList.addAll(context.compare(submission1, submission2));

        // compare for similarity in code logic using AST of each file in the submission
        context = new ComparisonContext(new LogicalSimilarityDetectionStrategy());
        matchReportList.addAll(context.compare(submission1, submission2));

        context = new ComparisonContext(new RefactoringDetectionStrategy());
        matchReportList.addAll(context.compare(submission1, submission2));

        return matchReportList;
    }
}
