package com.phasec.plagsafe.util;

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
}
