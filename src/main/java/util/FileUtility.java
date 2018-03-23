package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.phasec.plagsafe.objects.FileModel;
import com.phasec.plagsafe.objects.MultipartRecord;
import com.phasec.plagsafe.objects.SubmissionRecord;

public class FileUtility {
	
	private static Logger logger = LoggerFactory.getLogger(FileUtility.class);

	public static File multipartToFile(MultipartFile multipart) throws IOException {
		File convFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}
	
	public static FileModel getFileModel(File file) {
		FileModel fileMap = new FileModel(); 
		fileMap.setFileName(file.getName());
		fileMap.setFileData(file);
		return fileMap;
	}
	
	public static SubmissionRecord getFileMapList(MultipartRecord multiparts) {
		SubmissionRecord record = new SubmissionRecord();
		for(MultipartFile multiPartFile : multiparts.getMultiparts()) {
			File file;
			try {
				file = multipartToFile(multiPartFile);
				List<FileModel> files = new ArrayList<>();
				files.add(getFileModel(file));
				record.setFiles(files);
			} catch (IOException e) {
				logger.warn("Exception occured while converting multipart to file: "+e.getMessage());
			}
		}
		return record;
	}

}
