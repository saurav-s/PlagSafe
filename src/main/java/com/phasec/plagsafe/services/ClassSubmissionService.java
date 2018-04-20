package com.phasec.plagsafe.services;

import com.phasec.plagsafe.models.StrategyType;
import com.phasec.plagsafe.detector.*;
import com.phasec.plagsafe.models.FileRecord;
import com.phasec.plagsafe.models.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import util.DataFormatUtility;
import util.FileUtility;
import util.SubmissionUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.Map.*;

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

            // comparison strategy demanded by the user
            StrategyType comparisonStrategy = StrategyType.valueOf(strategy);

            //get the report from comparison of files based on the comparison strategy
            List<Report> comparisonReport = triggerComparison(submissionFiles, comparisonStrategy);
            reformatFilenames(comparisonReport);

            //convert to JSON string and return comparison results
            return DataFormatUtility.getJsonString(comparisonReport);

        } catch (Exception e) {
            // log the error
            logger.error(FILE_UPLOAD_ERROR, e.getMessage());

            // return upload error message
            return DataFormatUtility.getJsonString(FILE_UPLOAD_ERROR);
        }
    }

    /**
     * updates the filenames in the reports with the right delimiter, replacing the internally used delimiter
     * @param reports a list of reports which needs the modification
     */
    void reformatFilenames(List<Report> reports) {
        for(Report report : reports) {
            report.setSourceFile(report.getSourceFile().replaceAll(FILE_NAME_DELIMITER, PATH_DELIMITER));
            report.setTargetFile(report.getTargetFile().replaceAll(FILE_NAME_DELIMITER, PATH_DELIMITER));
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
    private List<Report> triggerComparison(List<String> submissionFiles, StrategyType comparisonStrategy)
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

        for(Entry<String, List<String>> submission : submissionMap.entrySet()) {
            // make a record of files for this submission

            FileRecord record = makeRecordFiles(submission.getValue());

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

    /**
     * updates services stats
     * @param submissions multipart file submissions received
     *
     */
    public void updateSystemStats(MultipartFile[] submissions, String strategy) {

        SystemStatisticsService.loadSystemStats();

        SystemStatisticsService.updateSystemLastUsed();
        SystemStatisticsService.incrementTotalRunsBy(1);

        // increment services load based on python file count in the submission
        int validCount = 0;
        for(MultipartFile submission : submissions) {
            if (FileUtility.validFileType(submission, validTypes)) {
                validCount++;
            }
        }

        // update services load stats
        SystemStatisticsService.incrementTotalFilesComparedBy(validCount);
        SystemStatisticsService.updateMaxLoad(validCount);

        // update strategy request load for the given strategy
        ComparisonContext context = new ComparisonContext(SubmissionUtility.getDetectionStrategy(StrategyType.valueOf(strategy)));
        context.updateRequestCount();

        SystemStatisticsService.serializeStats();
    }

    /**
     * transforms two submissions in a class submission
     * @param fileList1 list of files in submission 1
     * @param path1List list of paths of files in submission 1
     * @param fileList2 list of files in submission 2
     * @param path2List list of paths of files in submission 2
     * @param strategy comparison strategy requested by the user
     * @return returns the comparison result as a string
     */
    public String makeClassSubmissionAndCompare(MultipartFile[] fileList1, String[] path1List,
                                                MultipartFile[] fileList2, String[] path2List,
                                                String strategy) {
        MultipartFile[] submissions = new MultipartFile[fileList1.length + fileList2.length];
        List<String> paths = new ArrayList<>();

        // adds the files to the submissions and paths from file list for submission 1
        int i = 0;
        for(MultipartFile file : fileList1) {
            submissions[i] = file;
            paths.add(i, path1List[i]);
            i++;
        }

        //adds the files to the submissions and paths from file list for submission 2
        int j = 0;
        for(MultipartFile file : fileList2) {
            submissions[i] = file;
            paths.add(i, path2List[j]);
            j++; i++;
        }

        // update system stats
        updateSystemStats(submissions, strategy);

        // return comparison report
        return initializeAndCompare(submissions, paths, strategy);
    }

    /**
     * updates system stats on failures
     * @return returns failure message
     */
    public void failureStatsUpdate() {
        SystemStatisticsService.loadSystemStats();
        SystemStatisticsService.incrementSystemFailuresBy(1);

        //save updated value
        SystemStatisticsService.serializeStats();
    }



}
