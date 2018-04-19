package com.phasec.plagsafe.models;

import com.phasec.plagsafe.detector.Submissible;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SubmissibleRecordTests {
    @Test
    public void testSubmissibleRecordCreation() {
        SubmissibleRecord record = new SubmissibleRecord(new ArrayList<>());
        List<Submissible> expectedList = record.getSubmissibles();
        Assert.assertEquals(expectedList.size(), 0);
    }
}
