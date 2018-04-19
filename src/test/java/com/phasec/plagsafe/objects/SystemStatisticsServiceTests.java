package com.phasec.plagsafe.objects;

import org.junit.Before;
import org.junit.Test;

import com.phasec.plagsafe.system.SystemStatisticsService;


public class SystemStatisticsServiceTests {
	
	private SystemStatisticsService instance;
	
	@Before
	public void setUp(){
		instance = SystemStatisticsService.getSystemStatInstance();
	}
	
	@Test
	public void testGetters(){
		
		instance.updateSystemLastUsed();
		instance.incrementTotalRunsBy(1);
		instance.incrementTotalFilesComparedBy(3);
		instance.updateMaxLoad(3);
		instance.incrementSystemFailuresBy(1);
		instance.resetSystemStats();
		instance.toString();
		
		String startDate = instance.getSystemStartDate();
		int totalRuns = instance.getTotalRuns();
		int logicalComaparisons = instance.getLogicalComparisonRequested();
		instance.setLogicalComparisonRequested(3);
		instance.setRenamingComparisonRequested(3);
		int refactoringComparisons = instance.getRefactoringComparisonRequested();
		instance.setRefactoringComparisonRequested(3);
		int weightedComparisons = instance.getWeightedComparisonRequested();
		instance.setWeightedComparisonRequested(3);
		int totalFilesCompared = instance.getTotalFilesCompared();
		instance.setTotalFilesCompared(3);
		int maxSystemLoad = instance.getMaxSystemLoad();
		instance.setMaxSystemLoad(3);
		int totalFailures = instance.getSystemFailures();
		instance.setSystemFailures(3);
		String lastUsed = instance.getSystemLastUsed();
		
		
	}

}
