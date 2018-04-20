package com.phasec.plagsafe.services;

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

import com.phasec.plagsafe.models.SystemUsageInfo;

/**
 * 
 * This class interacts with services usage information
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
	// serialized file location
	private static final String FILE_PATH = "upload-dir/stats.ser";

	// start date of the services
	private static String systemStartDate;

	// previous use of the services
	private static String systemLastUsed;



	private static String getCurrentDateString() {
		// Create an instance of SimpleDateFormat used for formatting
		// the string representation of date (month/day/year)
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		// Get the date today using Calendar object.
		Date today = Calendar.getInstance().getTime();

		return df.format(today);
	}



	public static void updateSystemLastUsed() {
		systemLastUsed = getCurrentDateString();
	}

	// total number of times the services has run since the start date
	private static int totalRuns;

	public static void incrementTotalRunsBy(int i) {
		totalRuns += i;
	}

	// count of the total requests made by the user for each type of strategy
	private static int logicalComparisonRequested;
	private static int renamingComparisonRequested;
	private static int refactoringComparisonRequested;
	private static int weightedComparisonRequested;



	public static void incrementLogicalComparisonRequestedBy(int i) {
		logicalComparisonRequested += i;
	}

	public static void incrementRenamingComparisonRequestedRunsBy(int i) {
		renamingComparisonRequested += i;
	}

	public static void incrementRefactoringComparisonRequested(int i) {
		refactoringComparisonRequested += i;
	}

	public static void incrementWeightedComparisonRequestedRunsBy(int i) {
		weightedComparisonRequested += i;
	}

	// count of total number of files compared
	private static int totalFilesCompared;

	public static void incrementTotalFilesComparedBy(int i) {
		totalFilesCompared += i;
	}

	// maximum number of files compared in a single run
	private static int maxSystemLoad;

	public static void updateMaxLoad(int currentLoad) {
		maxSystemLoad = Math.max(maxSystemLoad, currentLoad);
	}

	// number of times the services has crashed
	private static int systemFailures;

	public static void incrementSystemFailuresBy(int i) {
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
		if (statsInstance == null) {
			logger.info("Creating a new stats object");
			statsInstance = new SystemStatisticsService();
		}
		return statsInstance;
	}



	/**
	 * to be used if the services stats need to be reset
	 */
	public void resetSystemStats() {
		resetStats();
		serializeStats();
	}



	/**
	 * resets all data members to counts to 0, and dates to today's date for the
	 * data members
	 */
	private static void resetStats() {
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
	public static void serializeStats() {
		try (FileOutputStream outputFile = new FileOutputStream(FILE_PATH);
			 ObjectOutputStream out = new ObjectOutputStream(outputFile);) {
			out.writeObject(statsInstance);

		} catch (IOException e) {
			logger.error("Object serialization exception ", e);
		}
	}


	/*
	 * @return return system stats after loading
	 */
	public static SystemUsageInfo loadSystemStats() {
		ObjectInputStream input = null;
		try (FileInputStream inputFile = new FileInputStream(FILE_PATH)) {
			input = new ObjectInputStream(inputFile);
			statsInstance = (SystemStatisticsService) input.readObject();
			return getCurrentSystemUsageInfo();
		} catch (IOException | ClassNotFoundException e) {
			logger.error("Object deserialization exception ", e);
			return new SystemUsageInfo();
		}
	}

	private static SystemUsageInfo getCurrentSystemUsageInfo() {
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


	/**
	 * converts stats to string
	 * @return
	 */
	@Override
	public String toString() {
		return "System started on:\t\t" + systemStartDate + "\n" + "System last run on:\t\t" + systemLastUsed + "\n"
				+ "Total services runs\t\t" + totalRuns + "\n" + "Logical comparison strategy requests:\t\t"
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



	public static void setTotalRuns(int tr) {
		totalRuns = tr;
	}



	public int getLogicalComparisonRequested() {
		return logicalComparisonRequested;
	}



	public static void setLogicalComparisonRequested(int lcr) {
		logicalComparisonRequested = lcr;
	}



	public int getRenamingComparisonRequested() {
		return renamingComparisonRequested;
	}



	public static void setRenamingComparisonRequested(int rcr) {
		renamingComparisonRequested = rcr;
	}



	public int getRefactoringComparisonRequested() {
		return refactoringComparisonRequested;
	}



	public static void setRefactoringComparisonRequested(int rcr) {
		refactoringComparisonRequested = rcr;
	}



	public int getWeightedComparisonRequested() {
		return weightedComparisonRequested;
	}



	public static void setWeightedComparisonRequested(int wcr) {
		weightedComparisonRequested = wcr;
	}



	public int getTotalFilesCompared() {
		return totalFilesCompared;
	}



	public static void setTotalFilesCompared(int tfc) {
		totalFilesCompared = tfc;
	}



	public int getMaxSystemLoad() {
		return maxSystemLoad;
	}



	public static void setMaxSystemLoad(int msl) {
		maxSystemLoad = msl;
	}



	public int getSystemFailures() {
		return systemFailures;
	}



	public static void setSystemFailures(int sf) {
		systemFailures = sf;
	}



	public String getSystemLastUsed() {
		return systemLastUsed;
	}

}
