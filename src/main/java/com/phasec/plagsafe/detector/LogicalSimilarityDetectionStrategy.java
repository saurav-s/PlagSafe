package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.antlr.ASTPrinter;
import com.phasec.plagsafe.objects.Report;
import util.SubmissionUtility;

import java.util.ArrayList;
import java.util.List;

public class LogicalSimilarityDetectionStrategy implements DetectionStrategy {
    private SubmissionUtility util;
    private static final String MATCHING_REMARK = "Logical similarities detected.";

    /**
     *
     * @param submission1
     * @param submission2
     * @return
     */
    @Override
    public List<Report> compare(List<Submissible> submission1, List<Submissible> submission2) {
        List<Report> reportList = new ArrayList<>();

        for(Submissible sub1file : submission1) {
            for(Submissible sub2file : submission2) {
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
        util = new SubmissionUtility();
        ASTPrinter astIterator = new ASTPrinter();

        StringBuilder sb = new StringBuilder();
        astIterator.ASTString(sub1file.getAst(), sb);
        String ast1String = sb.toString();

        sb = new StringBuilder();
        astIterator.ASTString(sub2file.getAst(), sb);
        String ast2String = sb.toString();

        int renameCount = util.editDistance(ast1String, ast2String);
        int averageFileLength = util.getAverageSubmissionFileLength(ast1String, ast2String);
        int matchPercentage = util.getMatchPercentage(renameCount, averageFileLength);

        Report report = new Report(sub1file.getName(), sub2file.getName(), matchPercentage, MATCHING_REMARK);
        return report;
    }
}
