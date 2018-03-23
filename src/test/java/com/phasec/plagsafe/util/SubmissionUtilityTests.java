package com.phasec.plagsafe.util;

import com.phasec.plagsafe.antlr.generated.Python3Parser.File_inputContext;
import org.junit.Test;
import util.SubmissionUtility;

import java.io.File;

import static org.junit.Assert.assertNull;

public class SubmissionUtilityTests {
    @Test
    public void testInvalidFIlesExceptions() {
        SubmissionUtility util = new SubmissionUtility();
        String expected = null;
        expected = util.readFile(new File("invalid File"));
        assertNull(expected);
    }

    @Test
    public void testInvalidFIlesExceptionsForAST() {
        SubmissionUtility util = new SubmissionUtility();
        File_inputContext expected = null;
        expected = util.generateAST(new File("invalid File"));
        assertNull(expected);
    }
}
