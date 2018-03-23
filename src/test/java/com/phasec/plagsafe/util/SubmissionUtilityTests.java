package com.phasec.plagsafe.util;

import org.junit.Test;
import util.SubmissionUtility;

import java.io.File;

public class SubmissionUtilityTests {
    @Test
    public void testInvalidFIlesExceptions() {
        SubmissionUtility util = new SubmissionUtility();
        util.readFile(new File("invalideFile"));
        //expects exception to be thrown
    }
}
