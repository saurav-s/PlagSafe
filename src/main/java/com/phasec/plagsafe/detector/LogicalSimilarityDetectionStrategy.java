package com.phasec.plagsafe.detector;

import java.util.ArrayList;
import java.util.List;

import com.phasec.plagsafe.antlr.ASTPrinter;
import com.phasec.plagsafe.objects.Report;
import com.phasec.plagsafe.objects.SubmissibleRecord;

import util.SubmissionUtility;

public class LogicalSimilarityDetectionStrategy implements DetectionStrategy {
    private static final String MATCHING_REMARK = "Logical similarities detected.";

    /**
     *
     * @param submission1
     * @param submission2
     * @return
     */
    @Override
    public List<Report> compare(SubmissibleRecord submission1, SubmissibleRecord submission2) {
        List<Report> reportList = new ArrayList<>();

        for(Submissible sub1file : submission1.getSubmissibles()) {
            for(Submissible sub2file : submission2.getSubmissibles()) {
                Report current = fileASTCompare(sub1file, sub2file);
                reportList.add(current);
            }
        }

        return reportList;
    }

    /**
     *
     * @param sub1file
     * @param sub2file
     * @return
     */
    private Report fileASTCompare(Submissible sub1file, Submissible sub2file) {
        ASTPrinter astIterator = new ASTPrinter();

        StringBuilder sb = new StringBuilder();
        astIterator.ASTString(sub1file.getAst(), sb);
        String ast1String = sb.toString();

        sb = new StringBuilder();
        astIterator.ASTString(sub2file.getAst(), sb);
        String ast2String = sb.toString();
        int renameCount = SubmissionUtility.editDistance(ast1String, ast2String);
        int averageFileLength = SubmissionUtility.getAverageSubmissionFileLength(ast1String, ast2String);
        int matchPercentage = SubmissionUtility.getMatchPercentage(renameCount, averageFileLength);

        return new Report(sub1file.getName(), sub2file.getName(), matchPercentage, MATCHING_REMARK);
    }
}
