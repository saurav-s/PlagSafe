package com.phasec.plagsafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
     * @return a JSON string containing the list of report objects
     */
    @PostMapping("submissions")
    public String uploadClassSubmissions(@RequestParam("submissionFiles") MultipartFile[] submissions,
                                         @RequestParam("relativePaths") List<String> paths,
                                         @RequestParam("strategy") String strategy)
    {

        // pass the received info to a service for further processing and computations
        submissionService.initializeAndCompare(submissions, paths, strategy);
        return new String();
    }

}
