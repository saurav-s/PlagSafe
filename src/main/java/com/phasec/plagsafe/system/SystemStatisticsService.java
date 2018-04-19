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
/**
 * @author sanketsaurav
 *
 */
/**
 * @author sanketsaurav
 *
 */
@Service
public class SystemStatisticsService implements Serializable {

	private static Logger logger = LoggerFactory.getLogger(SystemStatisticsService.class);

	private static SystemStatisticsService statsInstance;

	// serialized file location
	private static final String FILE_PATH = "src/main/resources/stats.ser";

	// start date of the system
	private String systemStartDate;

	// previous use of the system
	private String systemLastUsed;
	
	// total number of times the system has run since the start date
	private int totalRuns;
	
	// count of total number of files compared
	private int totalFilesCompared;
	
	// maximum number of files compared in a single run
	private int maxSystemLoad;
	
	// number of times the system has crashed
	private int systemFailures;





	// count of the total requests made by the user for each type of strategy
	private int logicalComparisonRequested;
	private int renamingComparisonRequested;
	private int refactoringComparisonRequested;
	private int weightedComparisonRequested;


	private String getCurrentDateString() {
		// Create an instance of SimpleDateFormat used for formatting
		// the string representation of date (month/day/year)
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		// Get the date today using Calendar object.
		Date today = Calendar.getInstance().getTime();

		return df.format(today);
	}


	/**
	 * update last system update date
	 */
	public void updateSystemLastUsed() {
		systemLastUsed = getCurrentDateString();
	}


	/**
	 * increment total runs by i
	 * @param i : number of runs
	 */
	public void incrementTotalRunsBy(int i) {
		totalRuns += i;
	}

	/**
	 * increment logical comparison runs by i
	 * @param i
	 */
	public void incrementLogicalComparisonRequestedBy(int i) {
		logicalComparisonRequested += i;
	}

	/**
	 * 
	 * @param i
	 */
	public void incrementRenamingComparisonRequestedRunsBy(int i) {
		renamingComparisonRequested += i;
	}

	/**
	 * 
	 * @param i
	 */
	public void incrementRefactoringComparisonRequested(int i) {
		refactoringComparisonRequested += i;
	}


	/**
	 * 
	 * @param i
	 */
	public void incrementWeightedComparisonRequestedRunsBy(int i) {
		weightedComparisonRequested += i;
	}

	
	/**
	 * 
	 * @param i
	 */
	public void incrementTotalFilesComparedBy(int i) {
		totalFilesCompared += i;
	}

	public void updateMaxLoad(int currentLoad) {
		maxSystemLoad = Math.max(maxSystemLoad, currentLoad);
	}

	public void incrementSystemFailuresBy(int i) {
		systemFailures += i;
	}


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

	public static SystemStatisticsService getSystemStatInstance() {
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


	/**
	 * load all system stats
	 * @return system usage info
	 */
	public SystemUsageInfo loadSystemStats() {
		ObjectInputStream input = null;
		try (FileInputStream inputFile = new FileInputStream(FILE_PATH)) {
			input = new ObjectInputStream(inputFile);
			statsInstance = (SystemStatisticsService) input.readObject();
			return getCurrentSystemUsageInfo();
		} catch (IOException | ClassNotFoundException e) {
			logger.error("Object deserialization exception " + e);
			return new SystemUsageInfo();
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



	public void setTotalRuns(int runs) {
		totalRuns = runs;
	}



	public int getLogicalComparisonRequested() {
		return logicalComparisonRequested;
	}



	public void setLogicalComparisonRequested(int logicalComparisons) {
		logicalComparisonRequested = logicalComparisons;
	}



	public int getRenamingComparisonRequested() {
		return renamingComparisonRequested;
	}



	public void setRenamingComparisonRequested(int renamingComparisons) {
		renamingComparisonRequested = renamingComparisons;
	}



	public int getRefactoringComparisonRequested() {
		return refactoringComparisonRequested;
	}



	public void setRefactoringComparisonRequested(int refactoringComparisons) {
		refactoringComparisonRequested = refactoringComparisons;
	}



	public int getWeightedComparisonRequested() {
		return weightedComparisonRequested;
	}



	public void setWeightedComparisonRequested(int weightedComparisons) {
		weightedComparisonRequested = weightedComparisons;
	}



	public int getTotalFilesCompared() {
		return totalFilesCompared;
	}



	public void setTotalFilesCompared(int totalFiles) {
		totalFilesCompared = totalFiles;
	}



	public int getMaxSystemLoad() {
		return maxSystemLoad;
	}



	public void setMaxSystemLoad(int maxLoad) {
		maxSystemLoad = maxLoad;
	}



	public int getSystemFailures() {
		return systemFailures;
	}



	public void setSystemFailures(int systemFailuresCount) {
		systemFailures = systemFailuresCount;
	}



	public String getSystemLastUsed() {
		return systemLastUsed;
	}

}
