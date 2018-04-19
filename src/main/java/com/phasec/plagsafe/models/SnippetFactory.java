package com.phasec.plagsafe.models;

public class SnippetFactory {
    public MatchSnippet getMatchSnippet(String fileOne, String codeOne, String fileTwo, String codeTwo){
        return new MatchSnippet(fileOne, codeOne, fileTwo, codeTwo);
    }
}

