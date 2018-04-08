package com.phasec.plagsafe.system;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.phasec.plagsafe.objects.SystemUsageInfo;

/**
 * 
 * This class interacts with system usage information 
 *
 */
@Service
public class SystemStatisticsService implements Serializable {

	private static Logger logger = LoggerFactory.getLogger(SystemStatisticsService.class);
	// serialized file location
	private static final String FILE_PATH = "src/main/resources/stats.ser";

	// start date of the system
	private static String systemStartDate;

	// previous use of the system
	private static String systemLastUsed;



	private String getCurrentDateString() {
		// Create an instance of SimpleDateFormat used for formatting
		// the string representation of date (month/day/year)
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		// Get the date today using Calendar object.
		Date today = Calendar.getInstance().getTime();

		return df.format(today);
	}



	public void updateSystemLastUsed() {

		systemLastUsed = getCurrentDateString();
	}

	// total number of times the system has run since the start date
	private static int totalRuns;



	public void incrementTotalRunsBy(int i) {
		totalRuns += i;
	}

	// count of the total requests made by the user for each type of strategy
	private static int logicalComparisonRequested;
	private static int renamingComparisonRequested;
	private static int refactoringComparisonRequested;
	private static int weightedComparisonRequested;



	public void incrementLogicalComparisonRequestedBy(int i) {
		logicalComparisonRequested += i;
	}



	public void incrementRenamingComparisonRequestedRunsBy(int i) {
		renamingComparisonRequested += i;
	}



	public void incrementRefactoringComparisonRequested(int i) {
		refactoringComparisonRequested += i;
	}



	public void incrementWeightedComparisonRequestedRunsBy(int i) {
		weightedComparisonRequested += i;
	}

	// count of total number of files compared
	private static int totalFilesCompared;



	public void incrementTotalFilesComparedBy(int i) {
		totalFilesCompared += i;
	}

	// maximum number of files compared in a single run
	private static int maxSystemLoad;



	public void updateMaxLoad(int currentLoad) {
		maxSystemLoad = Math.max(maxSystemLoad, currentLoad);
	}

	// number of times the system has crashed
	private static int systemFailures;



	public void incrementSystemFailuresBy(int i) {
		systemFailures += i;
	}

	private static SystemStatisticsService statsInstance;



	/**
	 * de-serialize data usage data in this method
	 */
	private SystemStatisticsService() {
	}



	/**
	 * method to make sure only one instance of the object gets passed
	 * 
	 * @return instance of this class
	 */

	public static SystemStatisticsService initializeSystemStatistics() {
		if (statsInstance == null)
			statsInstance = new SystemStatisticsService();
		return statsInstance;
	}



	/**
	 * to be used if the system stats need to be reset
	 */
	public void resetSystemStats() {
		resetStats();
		serializeStats();
	}



	/**
	 * resets all data members to counts to 0, and dates to today's date for the
	 * data members
	 */
	private void resetStats() {
		systemStartDate = getCurrentDateString();
		systemLastUsed = getCurrentDateString();
		totalRuns = 0;
		logicalComparisonRequested = 0;
		renamingComparisonRequested = 0;
		refactoringComparisonRequested = 0;
		weightedComparisonRequested = 0;
		totalFilesCompared = 0;
		maxSystemLoad = 0;
		systemFailures = 0;
	}



	/**
	 * Serializes this object
	 */
	public void serializeStats() {
		try (FileOutputStream outputFile = new FileOutputStream(FILE_PATH);
				ObjectOutputStream out = new ObjectOutputStream(outputFile);) {
			out.writeObject(statsInstance);

		} catch (IOException e) {
			logger.error("Object serialization exception " + e);
		}
	}



	public SystemUsageInfo loadSystemStats() {
		ObjectInputStream input = null;
		try (FileInputStream inputFile = new FileInputStream(FILE_PATH)) {
			input = new ObjectInputStream(inputFile);
			statsInstance = (SystemStatisticsService) input.readObject();
			return getCurrentSystemUsageInfo();
		} catch (IOException | ClassNotFoundException e) {
			logger.error("Object deserialization exception " + e);
			return new SystemUsageInfo();
		} finally {
			//logger.info("closing files in finally after closing");
		}
	}
	
	private SystemUsageInfo getCurrentSystemUsageInfo() {
		SystemUsageInfo usageInfo = new SystemUsageInfo();
		usageInfo.setLogicalComparisonRequested(logicalComparisonRequested);
		usageInfo.setMaxSystemLoad(maxSystemLoad);
		usageInfo.setRefactoringComparisonRequested(refactoringComparisonRequested);
		usageInfo.setRenamingComparisonRequested(renamingComparisonRequested);
		usageInfo.setSystemFailures(systemFailures);
		usageInfo.setSystemFailures(systemFailures);
		usageInfo.setSystemLastUsed(systemLastUsed);
		usageInfo.setSystemStartDate(systemStartDate);
		usageInfo.setTotalFilesCompared(totalFilesCompared);
		usageInfo.setTotalRuns(totalRuns);
		usageInfo.setWeightedComparisonRequested(weightedComparisonRequested);
		return usageInfo;
	}



	@Override
	public String toString() {
		return "System started on:\t\t" + systemStartDate + "\n" + "System last run on:\t\t" + systemLastUsed + "\n"
				+ "Total system runs\t\t" + totalRuns + "\n" + "Logical comparison strategy requests:\t\t"
				+ logicalComparisonRequested + "\n" + "Renaming comparison strategy requests:\t\t"
				+ renamingComparisonRequested + "\n" + "Refactoring comparison strategy requests:\t\t"
				+ refactoringComparisonRequested + "\n" + "Weighted comparison strategy requests:\t\t"
				+ weightedComparisonRequested + "\n" + "Total number of files compared:\t\t" + totalFilesCompared + "\n"
				+ "Maximum load in seen in a run:\t\t" + maxSystemLoad + "\n" + "System Failures:\t\t" + systemFailures
				+ "\n";
	}



	public String getSystemStartDate() {
		return systemStartDate;
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



	public String getSystemLastUsed() {
		return systemLastUsed;
	}

}
