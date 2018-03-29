package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.antlr.generated.Python3Parser.File_inputContext;

/**
 * Represents an interface that has to be implemented by any class that represents a submission file
 * This interface contains the behavior specifications that any submission should have.
 * @author Rohit
 */
public interface Submissible {

	/**
	 * Represents the getter method for the name of the submission file
	 * @return String representation of the name of the submission file
	 */
    public String getName();

    /**
     * Represents the setter method for the name of the submission file
     * @param name: the name of the submission file that needs to be updated
     */
    public void setName(String name);

    /**
     * Represents the getter method for the code of the submission file
     * @return String representing the code of the submission file
     */
    public String getCode();

    
    /**
     * Represents the setter method for the code of the submission file
     * @param code: the code the submission file that needs to be updated
     */
    public void setCode(String code);

    /**
     * Represents the getter method of the ast of the submission file
     * @return File_inputContext representing the ast of the submission file
     */
    public File_inputContext getAst();

    /**
     * Represents the setter method for the ast of the submission file
     * @param ast: the ast of the submission file that needs to be updated
     */
    public void setAst(File_inputContext ast);
}
