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
	
	/**
	 * Test case for read file operation
	 * @throws IOException
	 */
	@Test
	public void testReadFile() throws IOException{
		
		File inputFile = new File("resources/simple.py");
		String obtainedResult = antlrDriver.readFile(inputFile, Charset.forName("UTF-8"));
		String expectedResult = "def sum(a, b):\n    return a + b\n\nprint(\"The sum of %i and %i is %i\" % (5, 3, sum(5, 3)))\n";
		
		assertEquals(expectedResult, obtainedResult);	
	}
	
	/**
	 * Test case for parse file operation
	 * @throws IOException
	 */
	@Test
	public void testparseFile() throws IOException{
		
		File inputFile = new File("resources/simple.py");
		File_inputContext obtainedResult = antlrDriver.parseFile(inputFile);
		
		String code = antlrDriver.readFile(inputFile, Charset.forName("UTF-8"));
		Python3Parser parser = new Python3Parser(new CommonTokenStream(new Python3Lexer(new ANTLRInputStream(code))));
		File_inputContext expectedResult = parser.file_input();
	
		assertEquals(expectedResult.toString(), obtainedResult.toString());
		
		
		
	}

}
