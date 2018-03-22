package com.phasec.plagsafe.detector;

import java.io.File;

public class Submission implements Submissible {

    private String name;
    private String code;
    //add AST filed: ToDo
    /**
     * @param codeFile :  Code file for which the AST is to be generated
     */
    @Override
    public void generateAST(File codeFile) {
        //ToDo: implement AST generation
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
