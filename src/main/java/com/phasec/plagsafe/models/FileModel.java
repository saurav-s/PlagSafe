package com.phasec.plagsafe.models;

import java.io.File;

/**
 *all methods withe uploaded files
 */
public class FileModel {
    // name of this file
    private String fileName;
    // code in this file
    private File fileData;

    /**
     * get the file of the uploaded files
     * @return the name of the file
     */

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
