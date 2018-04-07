package com.phasec.plagsafe.controllers;

import com.phasec.plagsafe.ClassSubmissionController;
import com.phasec.plagsafe.objects.SystemStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemUsageController {

    private static Logger logger = LoggerFactory.getLogger(SystemUsageController.class);

    /**
     * get controller method to receive the system stats request
     * @return
     */
    @GetMapping("/api/system/usage")
    public SystemStatistics getSystemUsageStatistics() {
        // log the request
        logger.info("System stats requested");

        // get an instance
        SystemStatistics stats = SystemStatistics.initializeSystemStatistics();

        // load the stats
        stats.loadSystemStats();

        // log current stats
        logger.info(stats.toString());

        // returns system statistics
        return stats;
    }

}
