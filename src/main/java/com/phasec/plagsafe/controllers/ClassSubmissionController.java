package com.phasec.plagsafe.controllers;

import com.phasec.plagsafe.services.ClassSubmissionService;
import com.phasec.plagsafe.services.SystemStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import util.DataFormatUtility;

import java.util.List;

@RestController
@RequestMapping("/api/class")
public class ClassSubmissionController {

    private static Logger logger = LoggerFactory.getLogger(ClassSubmissionController.class);

    @Autowired
    ClassSubmissionService submissionService;

    /**
     * to upload entire class submissions preserving the directory structure
     * @param submissions list of class submission files
     * @param paths their corresponding relative paths
     * @param strategy comparison strategy demanded
     * @return a JSON string containing the list of report models
     */
    @PostMapping("submissions")
    public String uploadClassSubmissions(@RequestParam("submissionFiles") MultipartFile[] submissions,
                                         @RequestParam("relativePaths") List<String> paths,
                                         @RequestParam("strategy") String strategy)
    {
        try{
            //update services stats
            submissionService.updateSystemStats(submissions, strategy);

            // pass the received info to a service for further processing and computations
            // return the comparison result string
            return submissionService.initializeAndCompare(submissions, paths, strategy);
        } catch (Exception e) {
            logger.error("Error occurred while uploading the files" + e.getMessage());
            submissionService.failureStatsUpdate();
            return DataFormatUtility.getJsonString("Error occurred while uploading the files");
        }

    }

}
