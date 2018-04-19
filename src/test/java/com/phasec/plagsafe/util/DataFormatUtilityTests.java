package com.phasec.plagsafe.util;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.phasec.plagsafe.models.Report;

import util.DataFormatUtility;

public class DataFormatUtilityTests {
	
	DataFormatUtility instance;
	
	@Before
	public void setUp(){
		instance = DataFormatUtility.createInstance();
	}
	
	
	
	@Test
	public void testDataFormatUtility(){
		
		
		String str = "Report1";
		String str2 = "Report2";
		String str3 = "Report3";
		
		Report report1 = new Report("Report1", "Report2", 80, "Similarities detected");
		Report report2 = new Report("Report1", "Report2", 80, "Similarities detected");
		
		List<Report> reportList = new ArrayList<>();
		reportList.add(report1);
		reportList.add(report2);
		
		
		String obtainedResult = DataFormatUtility.getJsonString(reportList);
		assertNotNull(obtainedResult);
		
	}

}
