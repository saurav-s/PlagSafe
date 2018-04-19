package com.phasec.plagsafe.controllers;

import com.phasec.plagsafe.services.SnippetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.DataFormatUtility;

import java.io.File;

/**
 * This class listens as a controller for generating matching snippet requests
 * @author Rohit
 */

@RestController
public class MatchSnippetController {

    private Logger logger = LoggerFactory.getLogger(MatchSnippetController.class);

    @Autowired
    SnippetService snippetService;

    /**
     * method to receive get requests
     * @param firstFile
     * @param secondFile
     * @return matching snippet for the two files
     */
    @GetMapping(value="/match/snippet")
    public String generateMatchSnippet(@RequestParam String firstFile, @RequestParam String secondFile) {
        try {
            return snippetService.processSnippet(firstFile, secondFile);
        } catch(Exception e) {
            logger.error("Unable to read files");
            return DataFormatUtility.getJsonString("Couldn't read input files");
        }
    }
}
