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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + matchPercentage;
		result = prime * result + ((matchRemark == null) ? 0 : matchRemark.hashCode());
		result = prime * result + ((sourceFile == null) ? 0 : sourceFile.hashCode());
		result = prime * result + ((targetFile == null) ? 0 : targetFile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		if (matchPercentage != other.matchPercentage)
			return false;
		if (sourceFile == null) {
			if (other.sourceFile != null)
				return false;
		} else if (!sourceFile.equals(other.sourceFile))
			return false;
		if (targetFile == null) {
			if (other.targetFile != null)
				return false;
		} else if (!targetFile.equals(other.targetFile))
			return false;
		return true;
	}

	@Override
	public int compareTo(Report o) {
        int i = (this.matchPercentage == o.matchPercentage) ? 0 : 1;
		return (this.matchPercentage > o.matchPercentage) ? -1 : i;
	}
}
