package util;

import com.phasec.plagsafe.detector.Submissible;
import com.phasec.plagsafe.detector.Submission;
import com.phasec.plagsafe.objects.FileMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SubmissionUtility {
    public Submissible initializeSubmission(FileMap file) {
        Submissible submission = new Submission();
        submission.setName(file.getFileName());
        submission.setCode(readFile(file.getFileData()));

        System.out.println(submission.getName());
        System.out.println(submission.getCode());

        //ToDo: implement and uncomment this part of the code
        //submission.setAST(generateAST(file.getFileData()));
        return submission;
    }

    public String readFile(File inputFile){
        Scanner scanner = null;
        String text= "";
        try {
            scanner = new Scanner(inputFile);
            text = scanner.useDelimiter("\\A").next();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally{
            if(scanner != null){
                scanner.close();
            }
        }
        return text;
    }
}
