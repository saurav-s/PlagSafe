package util;

import com.phasec.plagsafe.antlr.AntlrDriver;
import com.phasec.plagsafe.antlr.generated.Python3Parser.File_inputContext;
import com.phasec.plagsafe.detector.Submissible;
import com.phasec.plagsafe.detector.Submission;
import com.phasec.plagsafe.objects.FileMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements the general utilities that are required with every submission
 * @author rohit
 *
 */
public class SubmissionUtility {

    /**
     *
     * @param file
     * @return
     */
    public Submissible initializeSubmission(FileMap file) {
        Submissible submission = new Submission();
        submission.setName(file.getFileName());
        submission.setCode(readFile(file.getFileData()));
        submission.setAst(generateAST(file.getFileData()));

        return submission;
    }

    /**
     * This method reads the code file and returns the text as a string
     * @param inputFile
     * @return : contents of the file as a String
     */
    public String readFile(File inputFile){
        Scanner scanner = null;
        String text= "";
        try {
            scanner = new Scanner(inputFile);
            text = scanner.useDelimiter("\\A").next();
        }
        catch (FileNotFoundException e) {

        }
        finally{
            if(scanner != null){
                scanner.close();
            }
        }
        return text;
    }

    /**
     * This method generated the AST for the give code python code file
     * @param submissionFile
     * @return
     */
    public File_inputContext generateAST(File submissionFile) {
        AntlrDriver antlrDriver = new AntlrDriver();
        File_inputContext ast = null;

        try {
            ast = antlrDriver.parseFile(submissionFile);
        }
        catch (IOException e) {

        }

        return ast;
    }
}
