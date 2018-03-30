package com.phasec.plagsafe;

import com.phasec.plagsafe.objects.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import util.DataFormatUtility;
import util.FileUtility;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassSubmissionService {
    private static Logger logger = LoggerFactory.getLogger(ClassSubmissionService.class);
    private static final String PATH_DELIMITER = "/";
    private static final String FILE_NAME_DELIMITER = "-";

    private static final String FILE_UPLOAD_ERROR = "Error occurred while uploading the file";

    @Autowired
    StorageService storageService;

    @Value("#{'${acceptable.file.types}'.split(',')}")
    private List<String> validTypes;

    public void initializeAndCompare(MultipartFile[] submissions, List<String> paths, String strategy) {
        try {
            // save the all the files
            List<String> submissionFiles = storeFiles(submissions, paths);

            // comparison strategy
            StrategyType comparisonStrategy = StrategyType.valueOf(strategy);

            //get the report from comparison of files
            List<Report> comparisonReport = initializeComparison(submissionFiles, comparisonStrategy);
            //return DataFormatUtility.getJsonString(comparisonReport);
        } catch (Exception e) {
            logger.error(FILE_UPLOAD_ERROR + e.getMessage());
            DataFormatUtility.getJsonString(FILE_UPLOAD_ERROR);
        }
    }

    private List<Report> initializeComparison(List<String> submissionFiles, StrategyType comparisonStrategy)
            throws FileNotFoundException, MalformedURLException {


        return new ArrayList<>();
    }

    private List<String> storeFiles(MultipartFile[] submissions, List<String> paths) {
        List<String> absoluteFileName = new ArrayList<>();

        // save all the received files
        for(int i=0;i<submissions.length;i++) {
            if(FileUtility.validFileType(submissions[i], validTypes)) {
                String pathPrefix = paths.get(i).replaceAll(PATH_DELIMITER,FILE_NAME_DELIMITER);
                storageService.store(submissions[i], pathPrefix);
                absoluteFileName.add(pathPrefix);
            }
        }

        return absoluteFileName;
    }
}
