package com.phasec.plagsafe.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phasec.plagsafe.models.SystemUsageInfo;
import com.phasec.plagsafe.services.SystemStatisticsService;


@RestController
public class SystemUsageController {

	private static Logger logger = LoggerFactory.getLogger(SystemUsageController.class);

	private SystemUsageController() {
	}

	/**
	 * get controller method to receive the services stats request @return @throws
	 */
	@GetMapping("/api/system/usage")
	public static SystemUsageInfo getSystemUsageStatistics() {
		// log the request
		logger.info("System stats requested");

		// load the stats
		return SystemStatisticsService.loadSystemStats();
	}

}
