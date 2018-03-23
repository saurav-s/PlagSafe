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
    /**
     * this method calculates the edit distance from a source string to a target String
     *
     * @param source
     * @param target
     * @return count of changes to be made on the source string to get the target string
     */
    public int editDistance(String source, String target) {
        int [][]dp = new int [source.length()+1][target.length()+1];

        dp[0][0]=0;
        for(int i=1; i<dp.length; i++){
            dp[i][0]=i;
        }

        for(int j=1; j<dp[0].length; j++){
            dp[0][j]=j;
        }

        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                if(source.charAt(i-1)==target.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    /**
     *
     * @param source
     * @param target
     * @return
     */
    public static int getAverageSubmissionFileLength(String source, String target) {
        return (source.length() + target.length());
    }

    /**
     *
     * @param diff
     * @param total
     * @return
     */
    public static int getMatchPercentage(int diff, int total) {
        return 100 - ((diff*100)/total);
    }

}
