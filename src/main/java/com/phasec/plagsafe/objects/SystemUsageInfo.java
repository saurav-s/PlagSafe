package com.phasec.plagsafe.objects;

/**
 * A model for system usage information
 */
public class SystemUsageInfo {
	
    private String systemStartDate;
    private String systemLastUsed ;
    private int totalRuns;
    private int logicalComparisonRequested;
    private int renamingComparisonRequested;
    private int refactoringComparisonRequested;
    private int weightedComparisonRequested;
    private int totalFilesCompared;
    private int maxSystemLoad;
    private int systemFailures;
	
    /**
     * getter setters
     */
	public String getSystemStartDate() {
		return systemStartDate;
	}
	
	public void setSystemStartDate(String systemStartDate) {
		this.systemStartDate = systemStartDate;
	}
	
	public String getSystemLastUsed() {
		return systemLastUsed;
	}
	
	public void setSystemLastUsed(String systemLastUsed) {
		this.systemLastUsed = systemLastUsed;
	}
	
	public int getTotalRuns() {
		return totalRuns;
	}
	
	public void setTotalRuns(int totalRuns) {
		this.totalRuns = totalRuns;
	}
	
	public int getLogicalComparisonRequested() {
		return logicalComparisonRequested;
	}
	
	public void setLogicalComparisonRequested(int logicalComparisonRequested) {
		this.logicalComparisonRequested = logicalComparisonRequested;
	}
	
	public int getRenamingComparisonRequested() {
		return renamingComparisonRequested;
	}
	
	public void setRenamingComparisonRequested(int renamingComparisonRequested) {
		this.renamingComparisonRequested = renamingComparisonRequested;
	}
	
	public int getRefactoringComparisonRequested() {
		return refactoringComparisonRequested;
	}
	
	public void setRefactoringComparisonRequested(int refactoringComparisonRequested) {
		this.refactoringComparisonRequested = refactoringComparisonRequested;
	}
	
	public int getWeightedComparisonRequested() {
		return weightedComparisonRequested;
	}
	
	public void setWeightedComparisonRequested(int weightedComparisonRequested) {
		this.weightedComparisonRequested = weightedComparisonRequested;
	}
	
	public int getTotalFilesCompared() {
		return totalFilesCompared;
	}
	
	public void setTotalFilesCompared(int totalFilesCompared) {
		this.totalFilesCompared = totalFilesCompared;
	}
	
	public int getMaxSystemLoad() {
		return maxSystemLoad;
	}
	
	public void setMaxSystemLoad(int maxSystemLoad) {
		this.maxSystemLoad = maxSystemLoad;
	}
	
	public int getSystemFailures() {
		return systemFailures;
	}
	
	public void setSystemFailures(int systemFailures) {
		this.systemFailures = systemFailures;
	}

	@Override
	public String toString() {
		return "SystemUsageInfo [systemStartDate=" + systemStartDate + ", systemLastUsed=" + systemLastUsed
				+ ", totalRuns=" + totalRuns + ", logicalComparisonRequested=" + logicalComparisonRequested
				+ ", renamingComparisonRequested=" + renamingComparisonRequested + ", refactoringComparisonRequested="
				+ refactoringComparisonRequested + ", weightedComparisonRequested=" + weightedComparisonRequested
				+ ", totalFilesCompared=" + totalFilesCompared + ", maxSystemLoad=" + maxSystemLoad
				+ ", systemFailures=" + systemFailures + "]";
	}
    
    

}
