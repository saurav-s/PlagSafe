package com.phasec.plagsafe.objects;

public class Report {
    private String sourceFile;
    private String targetFile;
    private int matchPercentage;
    private String matchRemark;

    public Report(String source, String target, int matchPercentage, String matchRemark) {
        this.sourceFile = source;
        this.targetFile = target;
        this.matchPercentage = matchPercentage;
        this.matchRemark = matchRemark;
    }

    @Override
    public String toString() {
        return "Report [sourceFile=" + sourceFile + ", targetFile=" + targetFile + ", matchPercentage="
                + matchPercentage + ", matchRemark=" + matchRemark + "]";
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(String targetFile) {
        this.targetFile = targetFile;
    }

    public int getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(int matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    public String getMatchRemark() {
        return matchRemark;
    }

    public void setMatchRemark(String matchRemark) {
        this.matchRemark = matchRemark;
    }
}
