package com.phasec.plagsafe.models;

import java.util.ArrayList;
import java.util.List;

public class MatchSnippet {
    private String fileNameOne;
    private String codeOne;
    private String fileNameTwo;
    private String codeTwo;
    private List<Integer> rangesForOne;
    private List<Integer> rangesForTwo;

    MatchSnippet(String fileOne, String codeOne, String fileTwo, String codeTwo) {
        this.fileNameOne = fileOne;
        this.fileNameTwo = fileTwo;
        this.codeOne = "<span style=\"background-color: #FFFF00\">" + codeOne + "</span>";
        this.codeOne = this.codeOne.replaceAll("&lt;", "<");
        this.codeOne = this.codeOne.replaceAll("&gt;", ">");

        this.codeTwo = codeTwo;
        this.rangesForOne = new ArrayList<>();
        this.rangesForTwo = new ArrayList<>();
    }
    public String getFileNameOne() {
        return fileNameOne;
    }

    public void setFileNameOne(String fileNameOne) {
        this.fileNameOne = fileNameOne;
    }

    public String getCodeOne() {
        return codeOne;
    }

    public void setCodeOne(String codeOne) {
        this.codeOne = codeOne;
    }

    public String getFileNameTwo() {
        return fileNameTwo;
    }

    public void setFileNameTwo(String fileNameTwo) {
        this.fileNameTwo = fileNameTwo;
    }

    public String getCodeTwo() {
        return codeTwo;
    }

    public void setCodeTwo(String codeTwo) {
        this.codeTwo = codeTwo;
    }

    public List<Integer> getRangesForOne() {
        return rangesForOne;
    }

    public void setRangesForOne(List<Integer> rangesForOne) {
        this.rangesForOne = rangesForOne;
    }

    public List<Integer> getRangesForTwo() {
        return rangesForTwo;
    }

    public void setRangesForTwo(List<Integer> rangesForTwo) {
        this.rangesForTwo = rangesForTwo;
    }

    public void addIndexForCodeOne(int index) {
        this.rangesForOne.add(index);
    }

    public void addIndexForCodeTwo(int index) {
        this.rangesForTwo.add(index);
    }
}
