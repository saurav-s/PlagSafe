package com.phasec.plagsafe.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phasec.plagsafe.objects.SystemUsageInfo;
import com.phasec.plagsafe.system.SystemStatisticsService;


@RestController
public class SystemUsageController {

	private static Logger logger = LoggerFactory.getLogger(SystemUsageController.class);



	/**
	 * get controller method to receive the system stats request @return @throws
	 */
	@GetMapping("/api/system/usage")
	public SystemUsageInfo getSystemUsageStatistics() {
		// log the request
		logger.info("System stats requested");

		// get an instance
		SystemStatisticsService service = SystemStatisticsService.initializeSystemStatistics();

		// load the stats
		SystemUsageInfo systemStats = service.loadSystemStats();

		logger.info(systemStats.toString());

		// returns system statistics
		return systemStats;
	}

}
