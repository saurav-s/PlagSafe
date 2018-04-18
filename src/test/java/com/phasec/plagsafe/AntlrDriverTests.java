package com.phasec.plagsafe;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;

import com.phasec.plagsafe.antlr.AntlrDriver;
import com.phasec.plagsafe.antlr.generated.Python3Lexer;
import com.phasec.plagsafe.antlr.generated.Python3Parser;
import com.phasec.plagsafe.antlr.generated.Python3Parser.File_inputContext;

/**
 * Represents the test cases for AntlrDriver class and all related functionalities
 * @author Tridiv
 *
 */
public class AntlrDriverTests {
	
	// member variable
	private AntlrDriver antlrDriver;
	
	/**
	 * SetUp method
	 */
	@Before
	public void test(){
		antlrDriver = new AntlrDriver();
	}
	
	

}
