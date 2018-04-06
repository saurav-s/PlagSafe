package com.phasec.plagsafe.controllers;

import com.phasec.plagsafe.ClassSubmissionController;
import com.phasec.plagsafe.objects.SystemStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/system")
public class SystemUsageController {

    private static Logger logger = LoggerFactory.getLogger(SystemUsageController.class);

    @GetMapping("usage")
    public SystemStatistics getSystemUsageStatistics() {


        return null;
    }

}
