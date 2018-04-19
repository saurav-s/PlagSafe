package com.phasec.plagsafe.models;

import org.junit.Test;

public class SnippetFactoryTests {
	
	@Test
	public void testGetMatchSnippet(){
		SnippetFactory instance = new SnippetFactory();
		instance.getMatchSnippet("File1", "Code1", "File2", "Code2");
	}

}
