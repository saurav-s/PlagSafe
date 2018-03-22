package com.phasec.plagsafe;
/**
 *
 */

import com.phasec.plagsafe.detector.DetectionEngine;
import com.phasec.plagsafe.detector.Engine;
import com.phasec.plagsafe.objects.FileMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class ComparisonController {

    /**
     *
     *
     * @param submissions
     * @throws IOException
     */
    @RequestMapping(value = "/comparison", method = RequestMethod.POST)
    public void process(@RequestParam("file") List<MultipartFile> submissions) throws IOException {
        //System.out.println(submissions);
        //System.out.println(submissions.isEmpty());
        submissionStub(null);
    }

    /**
     *
     * @param submissions
     */
    public void submissionStub(List<List<FileMap>> submissions) {
        Engine detectionEngine = new DetectionEngine();
        detectionEngine.runDetection(submissions);

    }
}
