package com.phasec.plagsafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import util.DataFormatUtility;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassSubmissionService {
    private static Logger logger = LoggerFactory.getLogger(ClassSubmissionService.class);

    @Autowired
    StorageService storageService;

    @Value("#{'${acceptable.file.types}'.split(',')}")
    private List<String> acceptableFiles;



    public void initializeAndCompare(MultipartFile[] submissions, List<String> paths, String strategy) {
        try {
            List<String> files = storeFiles(submissions, paths);

        } catch (Exception e) {
            logger.error("Error occurred while uploading the file" + e.getMessage());
            DataFormatUtility.getJsonString("Error occurred while uploading the file");
        }
    }

    private List<String> storeFiles(MultipartFile[] submissions, List<String> paths) {
        for(int i=0;i<submissions.length;i++) {
            if(submissions[i].getOriginalFilename().endsWith(acceptableFiles.get(0))){
                storageService.store(submissions[i], paths.get(i));
            }
        }

        return new ArrayList<String>();
    }
}
