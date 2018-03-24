package com.phasec.plagsafe.util;

import com.phasec.plagsafe.objects.FileModel;
import com.phasec.plagsafe.objects.FileRecord;
import com.phasec.plagsafe.objects.SubmissibleRecord;
import com.phasec.plagsafe.objects.SubmissionRecord;
import org.junit.Assert;
import org.junit.Test;
import util.FileUtility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtilityTests {
    @Test
    public void testGetFileModel() {
        FileUtility util = new FileUtility();

        FileModel model = util.getFileModel(new File("resources/simple.py"));
        Assert.assertNotNull(model);
    }

    @Test
    public void testGetFileMapList() {
        FileUtility util = new FileUtility();
        List<File> files = new ArrayList<>();

        files.add(new File("resources/simple.py"));
        files.add(new File("resources/simple1.py"));
        files.add(new File("resources/single_variant.py"));

        FileRecord records = new FileRecord();
        records.setFiles(files);

        SubmissionRecord record = util.getFileMapList(records);
        Assert.assertNotNull(record);
    }
}
