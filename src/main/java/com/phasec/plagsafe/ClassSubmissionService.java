package com.phasec.plagsafe;

import com.phasec.plagsafe.objects.FileRecord;
import com.phasec.plagsafe.objects.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import util.DataFormatUtility;
import util.FileUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.*;

@Service
public class ClassSubmissionService {
    private static Logger logger = LoggerFactory.getLogger(ClassSubmissionService.class);
    private static final String PATH_DELIMITER = "/";
    private static final String FILE_NAME_DELIMITER = "-";

    private static final String FILE_UPLOAD_ERROR = "Error occurred while uploading the file ";

    @Autowired
    StorageService storageService;

    @Autowired
    ComparisonService comparisonService;

    @Value("#{'${acceptable.file.types}'.split(',')}")
    private List<String> validTypes;

    /**
     * this method receives the submission files and saves them locally, and perform comparison on them
     * @param submissions   submission files for comparison
     * @param paths         relative paths of corresponding submission files
     * @param strategy      strategy demanded by the user to compare submissions
     */
    public String initializeAndCompare(MultipartFile[] submissions, List<String> paths, String strategy) {
        try {
            // save the all the files
            List<String> submissionFiles = storeFiles(submissions, paths);

            // comparison strategy
            StrategyType comparisonStrategy = StrategyType.valueOf(strategy);

            //get the report from comparison of files
            List<Report> comparisonReport = initializeComparison(submissionFiles, comparisonStrategy);

            //convert to JSON string and return comparison results
            return DataFormatUtility.getJsonString(comparisonReport);

        } catch (Exception e) {
            // log the error
            logger.error(FILE_UPLOAD_ERROR + e.getMessage());

            // return upload error message
            return DataFormatUtility.getJsonString(FILE_UPLOAD_ERROR);
        }
    }

    /**
     * This method initializes initializes comparison, by creating data to perform start comparing submissions
     *
     * @param submissionFiles       submission files to be compared
     * @param comparisonStrategy    comparison strategy to be employed
     * @return                      a report of comparisons performed from the given strategy
     * @throws FileNotFoundException    if any of the file that is to be read is not found
     * @throws MalformedURLException
     */
    private List<Report> initializeComparison(List<String> submissionFiles, StrategyType comparisonStrategy)
            throws FileNotFoundException, MalformedURLException {

        // create list of comparable file records
        List<FileRecord> records = getFileRecordList(submissionFiles);
        return comparisonService.runComparisionForFiles(records, comparisonStrategy);
    }

    /**
     * This method is to locally store the files
     *
     * @param submissions files to be saved
     * @param paths relative path of files where corresponding files where picked from
     * @return  a list of filenames with which the received files have been saved locally
     *
     */
    private List<String> storeFiles(MultipartFile[] submissions, List<String> paths) {

        List<String> absoluteFileName = new ArrayList<>();

        // save all the received files
        for(int i=0;i<submissions.length;i++) {

            //only save valid files
            if(FileUtility.validFileType(submissions[i], validTypes)) {

                //add file path to filename to avoid maintain distinction in files from different submissions
                String pathPrefix = paths.get(i).replaceAll(PATH_DELIMITER,FILE_NAME_DELIMITER);
                storageService.store(submissions[i], pathPrefix);

                //add the file name to the list of saved file names
                absoluteFileName.add(pathPrefix);
            }
        }

        return absoluteFileName;
    }


    /**
     * get a list of file records from the submission files
     * @param submissionFiles files to be converted to file record
     * @return a list of file records, which will be used by comparison technique
     */
    private List<FileRecord> getFileRecordList(List<String> submissionFiles) throws FileNotFoundException, MalformedURLException {
        // list of resultant record
        List<FileRecord> recordList = new ArrayList<>();

        // divide list of files according to submissions
        Map<String, List<String>>  submissionMap = reorganizeSubmissionFiles(submissionFiles);

        for(String submission : submissionMap.keySet()) {
            // make a record of files for this submission
            FileRecord record = makeRecordFiles(submissionMap.get(submission));

            // add the record to the list of records
            recordList.add(record);
        }

        return recordList;
    }

    /**
     * method to add the submission files to the record
     * @param submissionFiles submission files to be added to record
     * @return the record of the files together
     * @throws FileNotFoundException
     * @throws MalformedURLException
     */
    private FileRecord makeRecordFiles(List<String> submissionFiles) throws FileNotFoundException, MalformedURLException {
        FileRecord record = new FileRecord(new ArrayList<>());

        // each file in the submission to the record
        for(String submission : submissionFiles) {
            // get the file store locally
            File file = storageService.getFile(submission);
            System.out.println(file.getName());
            // add the file to the record
            record.addFile(file);
        }

        return record;
    }


    /**
     * Method creates map of list for each submission
     * @param submissionFiles submission files to be mapped to each submission
     * @return map of files for each submission
     */
    private Map<String, List<String>>  reorganizeSubmissionFiles(List<String> submissionFiles) {
        Map<String, List<String>> submissionMap = new HashMap<>();


        // add all the submission names to the list of submissions without the file name
        for(String file : submissionFiles) {
            // text after the last '-' is the file name, before that is the submission name
            String subName = file.substring(0, file.lastIndexOf(FILE_NAME_DELIMITER));

            // if this submission name exists in the map, add the file to the list of files related to this
            // submission, else create new entry in the map
            if(submissionMap.containsKey(subName)) {
                submissionMap.get(subName).add(file);
            } else {
                List<String> newFileList = new ArrayList<>();
                newFileList.add(file);
                submissionMap.put(subName, newFileList);
            }

        }

        return submissionMap;
    }


}
