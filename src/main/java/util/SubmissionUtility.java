package util;

import com.phasec.plagsafe.models.StrategyType;
import com.phasec.plagsafe.antlr.AntlrDriver;
import com.phasec.plagsafe.antlr.generated.Python3Parser.File_inputContext;
import com.phasec.plagsafe.detector.*;
import com.phasec.plagsafe.models.FileModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.phasec.plagsafe.models.StrategyType.*;
import static com.phasec.plagsafe.models.StrategyType.COMBINED;

/**
 * This class implements the general utilities that are required with every submission
 * @author rohit
 *
 */
public class SubmissionUtility {

	private static Logger logger = LoggerFactory.getLogger(SubmissionUtility.class);
	
	public static SubmissionUtility createInstance(){
		return new SubmissionUtility();
	}

    // Static strategy map to switch go to different strategies
    private static Map<StrategyType, DetectionStrategy> strategyMap = new HashMap<>();

    //adding known strategies to the map to be used by the application
    static {
        strategyMap.put(LOGICAL, new LogicalSimilarityDetectionStrategy());
        strategyMap.put(RENAMING, new RenamingDetectionStrategy());
        strategyMap.put(REFACTORING, new RefactoringDetectionStrategy());
        strategyMap.put(ALL, new AllComparisonStrategies());
        strategyMap.put(COMBINED, new WeightedComparisonStrategy());
    }

    public static DetectionStrategy getDetectionStrategy(StrategyType key) {
        return strategyMap.get(key);
    }


    /**
     *
     * @param file
     * @return
     */
    public Submissible initializeSubmission(FileModel file) {
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
        String text= "";
        try(Scanner scanner = new Scanner(inputFile)) {
            text = scanner.useDelimiter("\\A").next();
        }catch (FileNotFoundException e){
            logger.error("Error occurred while generating AST: ");
            return null;
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
            logger.error("Error occured while generating AST");
            return null;
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
    public static int editDistance(String source, String target) {
        return LevenshteinDistanceGeneratorUtility.getLevenshteinDistance(source, target);
    }

    /**
     *
     * @param source
     * @param target
     * @return
     */
    public static int getTotalSubmissionFileLength(String source, String target) {
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
