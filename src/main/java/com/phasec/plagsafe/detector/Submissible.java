package com.phasec.plagsafe.detector;

import java.io.File;

/**
 * This interface is to be implemented by any class that represents a submission file
 * @author rohit
 */
public interface Submissible {
    /**
     * @param codeFile  Code file for which the AST is to be generated
     */
    public void generateAST(File codeFile);

    public String getName();

    public void setName(String name);

    public String getCode();

    public void setCode(String code);
}
