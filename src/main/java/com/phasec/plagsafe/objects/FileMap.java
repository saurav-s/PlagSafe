package com.phasec.plagsafe.objects;

import java.io.File;

/**
 *
 */
public class FileMap {
    // name of this file
    private String fileName;
    // code in this file
    private File fileData;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFileData() {
        return fileData;
    }

    public void setFileData(File fileData) {
        this.fileData = fileData;
    }
}
