package com.phasec.plagsafe.services;

import com.phasec.plagsafe.models.MatchSnippet;
import com.phasec.plagsafe.models.SnippetFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.DataFormatUtility;
import util.SnippetUtility;
import util.SubmissionUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Service class for snippet generation
 * @author sanketsaurav
 *
 */
@Service
public class SnippetService {
    private static Logger logger = LoggerFactory.getLogger(SnippetService.class);

    private static final String PATH_DELIMITER = "/";
    private static final String FILE_NAME_DELIMITER = "-";

    @Autowired
    StorageService storageService;

    /**
     * process snippet generation
     * @param fileOne
     * @param fileTwo
     * @return
     */
    public MatchSnippet processSnippet(String fileOne, String fileTwo) {
        String savedFileOne = changeToSavedFileFormat(fileOne);
        String savedFileTwo = changeToSavedFileFormat(fileTwo);

        String codeOne = readFromFile(savedFileOne);
        String codeTwo = readFromFile(savedFileTwo);

        SnippetFactory snipFactory = new SnippetFactory();
        MatchSnippet snip = snipFactory.getMatchSnippet(fileOne, codeOne, fileTwo, codeTwo);

        List<Integer> ranges = SnippetUtility.findSnippetRanges(codeOne, codeTwo);
        updateSnippetRange(ranges, snip);
        processHighlight(codeOne, codeTwo, snip);
        return snip;
    }

    /**
     * Processes highlights
     * @param codeOne
     * @param codeTwo
     * @param snip
     */
	private void processHighlight(String codeOne, String codeTwo, MatchSnippet snip) {
		String highlightedCodeOne = getHighlightedCode(snip.getRangesForOne(), codeOne);
        highlightedCodeOne = parseTags(highlightedCodeOne);
        snip.setCodeOne(highlightedCodeOne);
        
        String highlightedCodeTwo = getHighlightedCode(snip.getRangesForTwo(), codeTwo);
        highlightedCodeTwo = parseTags(highlightedCodeTwo);
        snip.setCodeTwo(highlightedCodeTwo);
	}

	/**
	 * parse tags
	 * @param highlightedCodeOne
	 * @return
	 */
	private String parseTags(String highlightedCodeOne) {
		String startTagReplaced = highlightedCodeOne.replaceAll("&lt;", "<");
        return startTagReplaced.replaceAll("&gt;", ">");
	}
    
    /**
     * 
     * @param charIndexList
     * @param code
     * @return
     */
    private String getHighlightedCode(List<Integer> charIndexList,String code) {
    		boolean tagStarted = false;
    		StringBuilder sb = new StringBuilder();
    		if(charIndexList!=null) {
    			for(int index = 0; index < code.length();index++) {
    				if(charIndexList.contains(index)) {
    					tagStarted = processMatchingChar(code, tagStarted, sb, index);
    				}else {
    					tagStarted = processNonMatchingChar(code, tagStarted, sb, index);
    				} 
    			}
    			//in case all of the char matches
    			if(tagStarted) {
    				sb.append("</span>");
    			}
    		}
			
    		return sb.toString();
    }

    /**
     * 
     * @param code
     * @param tagStarted
     * @param sb
     * @param index
     * @return
     */
	private boolean processNonMatchingChar(String code, boolean tagStarted, StringBuilder sb, int index) {
		boolean tagAlreadyStarted = tagStarted;
		if(tagStarted) {
			sb.append("</span>");
			tagAlreadyStarted = false;
		}
		sb.append(code.charAt(index));
		return tagAlreadyStarted;
	}

	/**
	 * 
	 * @param code
	 * @param tagStarted
	 * @param sb
	 * @param index
	 * @return
	 */
	private boolean processMatchingChar(String code, boolean tagStarted, StringBuilder sb, int index) {
		boolean tagAlreadyStarted = tagStarted;
		if(!tagStarted) {
			sb.append("<span style=\"background-color: #40b4f7;\">");
			sb.append(code.charAt(index));
			tagAlreadyStarted = true;
		}else {
			sb.append(code.charAt(index));
		}
		return tagAlreadyStarted;
	}

    /**
     * updates the ranges of the snippet where the similarities have been detected
     * @param ranges list of ranges
     * @param snip snippet that needs updating
     */
    private void updateSnippetRange(List<Integer> ranges, MatchSnippet snip) {
        int n = ranges.size();
        // add indices to the first list
        int i=0;

        for(;i<n/2;i++)
            snip.addIndexForCodeOne(ranges.get(i));
        for(;i<n;i++)

            snip.addIndexForCodeTwo(ranges.get(i));
    }


    /**
     * updates the given filenames path to
     * @param fileName full name of the file that should be converted
     * @return return updated filename
     */
    private String changeToSavedFileFormat(String fileName) {
        return fileName.replaceAll(PATH_DELIMITER, FILE_NAME_DELIMITER);
    }

    private String readFromFile(String fileName) {
        try {
            SubmissionUtility util = new SubmissionUtility();
            File file = storageService.getFile(fileName);
            return util.readFile(file);

        } catch (FileNotFoundException | MalformedURLException e) {
            logger.error("File not present in the system ", fileName);
            return DataFormatUtility.getJsonString("File not present in the system");
        }

    }
}
