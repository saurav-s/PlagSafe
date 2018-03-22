package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.antlr.generated.Python3Parser.File_inputContext;

import java.io.File;

/**
 * This interface is to be implemented by any class that represents a submission file
 * @author rohit
 */
public interface Submissible {

    public String getName();

    public void setName(String name);

    public String getCode();

    public void setCode(String code);

    public File_inputContext getAst();

    public void setAst(File_inputContext ast);
}
