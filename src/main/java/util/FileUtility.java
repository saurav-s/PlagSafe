package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phasec.plagsafe.objects.FileModel;
import com.phasec.plagsafe.objects.FileRecord;
import com.phasec.plagsafe.objects.SubmissionRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

public class FileUtility {
	
	private static Logger logger = LoggerFactory.getLogger(FileUtility.class);

	/**
	 * 
	 * @param file
	 * @return
	 */
	public static FileModel getFileModel(File file) {
		FileModel fileMap = new FileModel(); 
		fileMap.setFileName(file.getName());
		fileMap.setFileData(file);
		return fileMap;
	}
	

	/**
	 * 
	 * @param files
	 * @return SubmissionRecord
	 */
	public static SubmissionRecord getFileMapList(FileRecord files) {
		SubmissionRecord record = new SubmissionRecord();
		List<FileModel> fileModelList = new ArrayList<>();
		for(File file : files.getFiles()) {
			fileModelList.add(getFileModel(file));
		}
		record.setFiles(fileModelList);
		logger.info("Created submission records from files");
		return record;
	}

	public static boolean validFileType(MultipartFile file, List<String> validTypes) {
		for (String validType : validTypes) {
			if (file.getOriginalFilename().endsWith(validType))
				return true;
		}
		return false;
	}



}
