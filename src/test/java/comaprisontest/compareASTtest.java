package comaprisontest;

import com.phasec.plagsafe.antlr.AntlrDriver;
import com.phasec.plagsafe.antlr.generated.Python3Parser.File_inputContext;
import com.phasec.plagsafe.comparison.ASTMatch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class compareASTtest {
    // member variable

    private AntlrDriver antlrDriver;

    @Before
    public void newAntlrDriver() {
        antlrDriver = new AntlrDriver();
    }

    /**
     *
     * @throws IOException
     */
   @Test
    public void testCompareSameFile() throws IOException {
       File inputFile1 = new File("resources/simple.py");
       File_inputContext ast1 = antlrDriver.parseFile(inputFile1);

       File inputFile2 = new File("resources/simple.py");
       File_inputContext ast2 = antlrDriver.parseFile(inputFile2);

       ASTMatch matcher = new ASTMatch();
       boolean isMatch = matcher.compare(ast1, ast2);
       String message = "Test for similar ASTs should return true";
       Assert.assertTrue(message, isMatch);
   }

    @Test
    public void testComparePlagarisedFile() throws IOException {
        File inputFile1 = new File("resources/simple.py");
        File_inputContext ast1 = antlrDriver.parseFile(inputFile1);

        File inputFile2 = new File("resources/simple_variant.py");
        File_inputContext ast2 = antlrDriver.parseFile(inputFile2);

        ASTMatch matcher = new ASTMatch();
        boolean isMatch = matcher.compare(ast1, ast2);
        String message = "Test for similar ASTs should return true";
        Assert.assertTrue(message, isMatch);
    }
}
