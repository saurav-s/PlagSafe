package com.phasec.plagsafe.antlr;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.phasec.plagsafe.antlr.generated.Python3Lexer;
import com.phasec.plagsafe.antlr.generated.Python3Parser;
import com.phasec.plagsafe.antlr.generated.Python3Parser.File_inputContext;

/**
 * Represents a standalone java class which acts as a driver for converting a
 * python file to its AST with the help of Antlr.
 * Referred from: Federico Tomassetti's blog 
 * URL: https://tomassetti.me/parsing-any-language-in-java-in-5-minutes-using-antlr-
 * for-example-python/
 * 
 * @author Tridiv
 *
 */
public class AntlrDriver {

	/**
	 * Main method which drives the functionality of calling the required
	 * methods and printing the AST
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		AntlrDriver testAntlr = new AntlrDriver();
		try {
			File inputFile = new File("resources/simple.py");
			File_inputContext fileInputContext = testAntlr.parseFile(inputFile);
			ASTPrinter astPrinter = new ASTPrinter();
//			astPrinter.print(fileInputContext);
			StringBuilder sb = new StringBuilder();
			astPrinter.printNew(fileInputContext, sb);
			System.out.println("Output::");
			System.out.println(sb.toString());

		} catch (IOException e) {

		}

	}

	/**
	 * Responsible for file reading operations
	 * 
	 * @param file
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public String readFile(File file, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(file.toPath());
		return new String(encoded, encoding);
	}

	/**
	 * Responsible for parsing the input file
	 * 
	 * @param file
	 * @return File_inputContext object corresponding to the input python file
	 * @throws IOException
	 */
	public File_inputContext parseFile(File file) throws IOException {
		String code = readFile(file, Charset.forName("UTF-8"));
		Python3Lexer l = new Python3Lexer(new ANTLRInputStream(code));
		CommonTokenStream tokens = new CommonTokenStream(l);
		Python3Parser parser = new Python3Parser(tokens);
		return parser.file_input();

	}
}
