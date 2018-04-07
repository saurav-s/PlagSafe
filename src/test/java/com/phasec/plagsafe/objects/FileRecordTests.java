package com.phasec.plagsafe.objects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FileRecordTests {
	
	@Test
	public void test_FileRecord(){
		
		File file = new File("resources/simple.py");
		List<File> fileList = new ArrayList<>();
		fileList.add(file);
		FileRecord record = new FileRecord(fileList);
		record.addFile(file);
	}

}
