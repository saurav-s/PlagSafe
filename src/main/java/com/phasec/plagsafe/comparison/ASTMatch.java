package com.phasec.plagsafe.comparison;

import com.phasec.plagsafe.antlr.ASTPrinter;
import com.phasec.plagsafe.antlr.AntlrDriver;
import com.phasec.plagsafe.antlr.generated.Python3Parser.File_inputContext;

import java.io.File;
import java.io.IOException;

public class ASTMatch {
    /**
     *
     * @param ast1
     * @param ast2
     * @return
     * @throws IOException
     */
    public boolean compare(File_inputContext ast1, File_inputContext ast2) throws IOException {

        ASTPrinter astPrinter = new ASTPrinter();

        String ast1String = astPrinter.ASTString(ast1);
        String ast2String = astPrinter.ASTString(ast2);

        int stepsCount = editDistance(ast1String, ast2String);
        if(stepsCount < 5)
            return true;
        return false;
    }

    /**
     *
     * @param ast1String
     * @param ast2String
     * @return
     */

    private int editDistance(String ast1String, String ast2String) {
        int [][]dp = new int [ast1String.length()+1][ast2String.length()+1];

        dp[0][0]=0;
        for(int i=1; i<dp.length; i++){
            dp[i][0]=i;
        }

        for(int j=1; j<dp[0].length; j++){
            dp[0][j]=j;
        }

        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                if(ast1String.charAt(i-1)==ast2String.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];

    }
}
