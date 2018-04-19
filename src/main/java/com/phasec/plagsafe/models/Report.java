package com.phasec.plagsafe.models;

/**
 * The class for creating the report
 */

public class Report implements Comparable<Report>{

    private String sourceFile;
    private String targetFile;
    private int matchPercentage;
    private String matchRemark; // reasons why the two file have such match results

    /**
     * constructor of the report
     * @param source the source file to be compared with
     * @param target the target file used to compare
     * @param matchPercentage integer as the match rate
     * @param matchRemark reasons why the two file have such match results
     */

    public Report(String source, String target, int matchPercentage, String matchRemark) {
        this.sourceFile = source;
        this.targetFile = target;
        this.matchPercentage = matchPercentage;
        this.matchRemark = matchRemark;
    }

    /**
     * present the report as a string
     * @return the result as a string
     */

    @Override
    public String toString() {
        return "Report [sourceFile=" + sourceFile + ", targetFile=" + targetFile + ", matchPercentage="
                + matchPercentage + ", matchRemark=" + matchRemark + "]";
    }

    // getters and setters for source files
    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    // getters and setters for target files

    public String getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(String targetFile) {
        this.targetFile = targetFile;
    }

    // getter and setters for the match percentage

    public int getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(int matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    //getters and setters for the match resons

    public String getMatchRemark() {
        return matchRemark;
    }

    public void setMatchRemark(String matchRemark) {
        this.matchRemark = matchRemark;
    }

	@Override
	public int compareTo(Report o) {
		return (this.matchPercentage > o.matchPercentage) ? -1 : (this.matchPercentage == o.matchPercentage) ? 0 : 1;
	}
}
