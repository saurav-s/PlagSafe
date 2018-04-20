package com.phasec.plagsafe.detector;

import com.phasec.plagsafe.antlr.generated.Python3Parser.File_inputContext;

/**
 * Represents a concrete implementation of the Submissible interface. Any python
 * file that has been submitted as a part of the plagiarism detection will be
 * represented as a Submission
 * 
 * @author Rohit and Tridiv
 *
 */
public class Submission implements Submissible {

	/**
	 * Member variables of the Submission class
	 */
	private String name;
	private String code;
	private File_inputContext ast;

	/**
	 * Default constructor
	 */
	public Submission() {

	}

	/**
	 * Represents the constructor of a Submission class
	 * 
	 * @param name: name of the corresponding python file
	 * @param code: the entire code content of the corresponding python file in
	 *            the form of a string
	 * @param ast: the generated ast of the corresponding python file
	 * 
	 */
	public Submission(String name, String code, File_inputContext ast) {
		super();
		this.name = name;
		this.code = code;
		this.ast = ast;
	}

	/**
	 * Getter method of the name attribute
	 * 
	 * @return String representing the name of the Submission
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter method of the name attribute
	 * 
	 * @param name: the name of the Submission
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter method of the code attribute
	 * 
	 * @return String representing the code contents of the Submission
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Setter method of the code attribute
	 * 
	 * @param code: the code of the Submission
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Getter method of the ast attribute
	 * 
	 * @return File_inputContext representing the ast of the Submission
	 */
	public File_inputContext getAst() {
		return ast;
	}

	/**
	 * Setter method of the ast attribute
	 * 
	 * @param ast: the ast corresponding to the code content of the Submission
	 */
	public void setAst(File_inputContext ast) {
		this.ast = ast;
	}

	/**
	 * Overridden version of the toString() method
	 * 
	 * @return String representing the Submission object in terms of it's
	 *         attributes
	 */
	@Override
	public String toString() {
		return "Submission [name=" + name + ", code=" + code + ", ast=" + ast + "]";
	}

}
