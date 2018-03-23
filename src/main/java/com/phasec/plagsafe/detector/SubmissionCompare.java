package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.objects.Report;
import com.phasec.plagsafe.objects.SubmissibleRecord;

import java.util.ArrayList;
import java.util.List;

public class SubmissionCompare implements SubmissionComparable {
	
    @Override
    public List<Report> compare(SubmissibleRecord submission1, SubmissibleRecord submission2) {
        ComparisonContext context = null;
        List<Report> matchReportList = new ArrayList<>();

        //compare for variable/function renaming using Levenshtein distance
        context = new ComparisonContext(new RenamingDetectionStrategy());

        //matchReportList.addAll(context.compare(submission1, submission2));

        // compare for similarity in code logic using AST of each file in the submission
        context = new ComparisonContext(new LogicalSimilarityDetectionStrategy());
        context.compare(submission1, submission2);
        matchReportList.addAll(context.compare(submission1, submission2));

        return matchReportList;
    }
}
