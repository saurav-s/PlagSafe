package com.phasec.plagsafe.util;

import com.phasec.plagsafe.models.FileModel;
import com.phasec.plagsafe.models.FileRecord;
import com.phasec.plagsafe.models.SubmissionRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.FileUtility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtilityTests {
	
	FileUtility instance;
	
	@Before
	public void setUp(){
		instance = FileUtility.createInstance();
	}
	
    @Test
    public void testGetFileModel() {

        FileModel model = FileUtility.getFileModel(new File("resources/simple.py"));
        Assert.assertNotNull(model);
    }

    @Test
    public void testGetFileMapList() {
        List<File> files = new ArrayList<>();

        files.add(new File("resources/simple.py"));
        files.add(new File("resources/simple1.py"));
        files.add(new File("resources/single_variant.py"));

        FileRecord records = new FileRecord();
        records.setFiles(files);

        SubmissionRecord record = FileUtility.getFileMapList(records);
        Assert.assertNotNull(record);
    }
}
