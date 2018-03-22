package com.phasec.plagsafe;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class ComparisonController {

    @RequestMapping(value="/comparison", method= RequestMethod.POST)
    public void process(ModelMap model, @RequestParam("file") MultipartFile[] submissions) throws Exception {
        System.out.println(submissions);
    }
}
