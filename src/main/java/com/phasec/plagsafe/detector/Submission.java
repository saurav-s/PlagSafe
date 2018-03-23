package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.antlr.generated.Python3Parser.File_inputContext;

import java.io.File;

public class Submission implements Submissible {
	
	public Submission(){
		
	}

    public Submission(String name, String code, File_inputContext ast) {
		super();
		this.name = name;
		this.code = code;
		this.ast = ast;
	}

	private String name;
    private String code;
    private File_inputContext ast;
    
    

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

    public File_inputContext getAst() {
        return ast;
    }

    public void setAst(File_inputContext ast) {
        this.ast = ast;
    }
}
